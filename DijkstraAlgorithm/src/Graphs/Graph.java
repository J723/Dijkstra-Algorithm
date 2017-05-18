
package Graphs;

import java.util.*;

public interface Graph<T> {
    
    //Gestione Vertici
    Boolean addSummit(T summit);
    Boolean removeSummit(T summit);
    T getSummit(int pos);
    int getSummit(T summit); 
    List<T> getSummits();
    List<T> getAdjSummits(T summit);
    
    
    //Gestione Archi
    Boolean addArch(T s1, T s2, int weight);
    Boolean removeArch(T s1, T s2);
    Integer getArchWeight(T s1, T s2);

    //Gestione Grafo
    int[][] getAdjMat();
    int countSummits();
    int countArch(T summit);
}
