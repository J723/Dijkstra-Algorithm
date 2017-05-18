
package dijkstraalgorithm;
import Graphs.*;
import java.util.*;

public class Dijkstra{
    
    public Graph<String> g;
    
    public Dijkstra(Graph g){
        this.g = g;
    }
    
    public Dijkstra (int size, boolean duplex){  
        //TODO IMPLEMENTARE ALFABETO ESTESO -> NUMERAZIONE IN BASE 26 -> PER SIZE > 26        
        //if (size > 26) size = 26;
        
        if (size < 1) size = 1;        
        
        Random r = new Random();
        Graph<String> g = new MatrixGraph<>();
        //per implementazione grafica:
        //String[] abc = new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        
        //crea i nodi 
        for (int i = 0; i < size; i++) g.addSummit(i+"");// oppure: abc[i]
        
        //crea gli archi con pesi generici [archi bidirezionali]
        int nk = size * size - size;//numero di archi
        for (int i = 0; i < nk; i++){
            //estrae casualmente due nodi diversi
            String p1 = g.getSummit(r.nextInt(size));
            String p2 = g.getSummit(r.nextInt(size));
            while (p1.equals(p2)) p2 = g.getSummit(r.nextInt(size));
            
            //crea l'arco bidirezionale            
            int w = r.nextInt(size * size);
            while (w == 0) w = r.nextInt(size);            
            g.addArch(p1, p2, w);
            if (duplex) g.addArch(p2, p1, w);
        }        
        this.g = g;
    }    
    
    public Stack<String> runTest(){
        Random r = new Random();
        int size = g.countSummits();
        
        int r1 = r.nextInt(size/2);
        int r2 = 0;
        
        do{
            while(r2 < size/2) r2 = r.nextInt(size);
        }while(r1 == r2);        
        
        String start = g.getSummit(r1);
        String end = g.getSummit(r2);
        
        return findPath(start, end);
    }
    
    public Stack<String> findPath(String start, String end){
        
        //controlla l'esistenza di {start} ed {end}
        if (g.getSummit(start) == -1 || g.getSummit(end) == -1 ) return null;
             
        boolean[] visit = new boolean[g.countSummits()];    //visitati
        String[] prov = new String[g.countSummits()];       //provenienze
        int[] dist = new int[g.countSummits()];             //distanze
        
        //inizializzazione       
        for (int i = 0; i < dist.length; i++) dist[i] = -1;
        
        //setta lo start
        visit[g.getSummit(start)] = true;
        prov[g.getSummit(start)] = start;
        dist[g.getSummit(start)] = 0;
        
        //inizia la routine     
        String rStart = start;
        while(!visit[g.getSummit(end)]){
            //estrae i nodi adiacenti
            List<String> adj = g.getAdjSummits(rStart);
            
            //setta le distanze ai nodi adiacenti e le provenienze in base al vantaggio maggiore
            for (String s : adj) {
                int i = g.getSummit(s);
                int j = g.getSummit(rStart);
                if ((dist[i] == -1 || dist[i] > dist[j] + g.getArchWeight(rStart, s)) && !visit[i]){
                    dist[i] = dist[j] + g.getArchWeight(rStart, s);
                    prov[i] = rStart;
                }
            }
            
            //sceglie il prossimo nodo più conveniente da visitare tra quelli noti
            int min = 0;
            while (visit[min] == true) min++;
            for (int i = 0; i < dist.length; i++)                
                if (dist[i] != -1 && visit[i] == false && dist[min] > dist[i]) min = i;
                        
            visit[min] = true;
            rStart = g.getSummit(min); 
        }
        
        //formula la strada di ritorno a partire da {end}
        Stack<String> path = new Stack<>(); 
        for (String cur = end; !cur.equals(start); cur = prov[g.getSummit(cur)]){
            path.push(cur);
            if (prov[g.getSummit(cur)] == null) return null;
        }
        path.push(start);
        return path;
    }
    
}
