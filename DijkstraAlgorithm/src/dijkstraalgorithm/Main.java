
package dijkstraalgorithm;

import java.util.*;
import Graphs.*;

public class Main {

    public static void main(String[] args) {
        
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
        
        Dijkstra d = new Dijkstra(g);
        //END_DEBUG
        */
        
        /*START interazioni utente*/
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
        /*END interazioni utente*/
        
        
        //START COMMENT IN DEBUG
        System.out.println("\nMatrice di adiacenza del grafo ( che può essere anche con liste di adiacenza )");
        Dijkstra d = new Dijkstra(resp1, resp2);
        for (int[] r : d.g.getAdjMat()){
            String row = "";
            for (int i : r) row += i + ";";
            System.out.println(row);
        }   
                
        System.out.println("\nStrada più corta");
        Stack<String> st = d.runTest();
        if (st != null && st.size() > 0)
            for (int k = st.size(); k > 0; k--) System.out.println(st.pop());
        else 
            System.out.println("\nStrada non trovata");
        
        main(args);    
        //END COMMENT IN DEBUG
                
        /*
        //DEBUG
        Stack<String> s = d.findPath("A", "E");
        for (int k = s.size(); k > 0; k--) System.out.println(s.pop());
        //END DEBUG
        */
    }    
}
