
package dijkstraalgorithm;

import java.util.*;
import Graphs.*;

public class Main {

    public static void main(String[] args) {   
        
        //!DEBUG
        //START interazioni utente
        Scanner s = new Scanner(System.in);
        Integer resp1;
        Boolean resp2;
        
        //Dimensioni
        System.out.println("inserisci il numero di nodi presenti nel grafo");
        resp1 = Integer.parseInt(s.nextLine());
        if (resp1 > 26) System.out.println("numero massimo: 26");
        if (resp1 < 1) System.out.println("numero minimo: 1");
        
        //Tipo di canale
        System.out.println("premi:\n'b' per bidirezionale\n'm' per monodirezionale (casualmente può generarne bidirezionali)");
        String t = s.nextLine();
        while (!"m".equals(t) && !"b".equals(t)) t = s.nextLine(); 
        resp2 = t.equals("b");           
        //END interazioni utente
        
        System.out.println("\nMatrice di adiacenza del grafo");
        Graph<String> g = Dijkstra.randomGraph(resp1, resp2);
        for (int[] r : g.getAdjMat()){
            String row = "";
            for (int i : r) row += i + "; ";
            System.out.println(row);
        }   
                
        System.out.println("\nStrada più corta:");
        Stack<String> st = Dijkstra.findPath(g, g.getSummit(0), g.getSummit(g.countSummits() - 1));
        if (st != null && st.size() > 0)
            for (int k = st.size(); k > 0; k--) System.out.println(st.pop());
        else 
            System.out.println("Strada non trovata");
        
        main(args);            
        //END !DEBUG
        
                
        /*
        //DEBUG
        MatrixGraph<String> g = new MatrixGraph<>();        
        ArrayList<String> l = new ArrayList<>();        
        l.add("A");
        l.add("B");        
        l.add("C");
        l.add("D");
        l.add("E");
        l.add("F");
        
        for (String s: l) g.addSummit(s);
        
        //A-B
        g.addArch(l.get(0), l.get(1), 7);
        g.addArch(l.get(1), l.get(0), 7);
        //A-C
        g.addArch(l.get(0), l.get(2), 9);
        g.addArch(l.get(2), l.get(0), 9);
        //A-F
        g.addArch(l.get(0), l.get(5), 14);
        g.addArch(l.get(5), l.get(0), 14);
        //B-C
        g.addArch(l.get(1), l.get(2), 10);
        g.addArch(l.get(2), l.get(1), 10);
        //B-D
        g.addArch(l.get(1), l.get(3), 15);
        g.addArch(l.get(3), l.get(1), 15);
        //C-D
        g.addArch(l.get(2), l.get(3), 11);
        g.addArch(l.get(3), l.get(2), 11);
        //C-F
        g.addArch(l.get(2), l.get(5), 2);
        g.addArch(l.get(5), l.get(2), 2);
        //D-E
        g.addArch(l.get(3), l.get(4), 6);
        g.addArch(l.get(4), l.get(3), 6);
        //E-F
        g.addArch(l.get(4), l.get(5), 9);
        g.addArch(l.get(5), l.get(4), 9); 
        
        System.out.println("\nStrada più corta:");
        Stack<String> st = Dijkstra.findPath(g, "A", "E");
        if (st != null && st.size() > 0)
            for (int k = st.size(); k > 0; k--) System.out.println(st.pop());
        else 
            System.out.println("Strada non trovata");        
        //END DEBUG
        */
        
    }    
}
