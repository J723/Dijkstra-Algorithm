
package Graphs;

import java.util.*;

public class MatrixGraph<T> implements Graph<T>{
    
    ArrayList<ArrayList<Integer>> adjMatrix;
    ArrayList<T> summits;
    
    public MatrixGraph(){
        adjMatrix = new ArrayList<>();
        summits = new ArrayList<>();
    }
    
    //GESTIONE NODI:
    
    @Override
    public Boolean addSummit(T summit) {        
        try{
            //controlla l'esistenza del vertice
            if (summits.contains(summit)) return false;
            
            //Aggiunge alla lista degli oggetti
            summits.add(summit);

            //Aggiunge una colonna a tutti
            for (ArrayList<Integer> l : adjMatrix)
                l.add(-1);

            //aggiunge una nuova riga di lunghezza [colonne] e starta le adiacenze a false
            adjMatrix.add(new ArrayList<>(Collections.nCopies(summits.toArray().length, -1)));

            return true;
        
        }catch (Exception e){
            return false;
        }        
    }

    @Override
    public Boolean removeSummit(T summit) {
        try{
            //controlla l'esistenza del vertice
            if (!summits.contains(summit)) return false;
            
            // memorizza l'indice e rimuove dalla lista degli oggetti
            int s = summits.indexOf(summit);
            summits.remove(s);

            //rimuove la riga di adiacenza del summit
            adjMatrix.remove(s);

            //rimuove la colonna a tutti
            for (ArrayList<Integer> l : adjMatrix) l.remove(s);

            return true;
        
        }catch (Exception e){
            return false;
        }    
    }
    
    @Override
    public T getSummit(int pos) {
        return summits.get(pos);
    }
    
    @Override 
    public int getSummit(T summit){
        for (int i = 0; i < summits.size(); i++)
            if (summits.get(i) == summit) return i;
        return -1;            
    }

    @Override
    public List<T> getSummits() {
        return summits;
    }
    
    @Override
    public int countSummits() {
        return summits.size();
    }

    @Override
    public List<T> getAdjSummits(T summit) {
        //controllo di esistenza
        int i = summits.indexOf(summit);
        if(i == -1) return null;
        
        //preleva data la matrice di adiacenza
        LinkedList<T> ka = new LinkedList<>();
        for (int n = 0; n < adjMatrix.get(i).size(); n++)
            if (adjMatrix.get(i).get(n) > -1) 
                ka.add(summits.get(n));        
        return ka;
    }
    
    //GESTIONE ARCHI:

    @Override
    public Boolean addArch(T s1, T s2, int weight) {
        try{
            //preleva gli indici            
            int[] indexes = new int[2];
            
            indexes[0] = summits.indexOf(s1);
            indexes[1] = summits.indexOf(s2);
            
            //verifica l'esistenza dei vertici
            if (indexes[0] == -1 || indexes[1] == -1) return false;
            
            //verifica la preesistenza dell'arco
            //if (getArchWeight(s1, s2) >= 0) return false;
            
            //setta a true la cella di adiacenza nella riga del primo vertice relativa al secondo (unidirezionale) ed il peso
            if (weight < -1) weight = -1;
            adjMatrix.get(indexes[0]).set(indexes[1], weight);
            
            return true;
            
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean removeArch(T s1, T s2) {
        try{
            //preleva gli indici            
            int[] indexes = new int[2];
            
            indexes[0] = summits.indexOf(s1);
            indexes[1] = summits.indexOf(s2);
            
            //verifica l'esistenza dei vertici
            if (indexes[0] == -1 || indexes[1] == -1) return false;
            
            //verifica la NON preesistenza dell'arco
            if (getArchWeight(s1, s2) < 0) return false;
            
            //setta a false la cella di adiacenza nella riga del primo vertice relativa al secondo (unidirezionale)
            adjMatrix.get(indexes[0]).set(indexes[1], -1);
            
            return true;
            
        }catch (Exception e){
            return false;
        }
    }

    /**
     * @return -2 per qualsiasi errore o vertici inesistenti
     */
    @Override
    public Integer getArchWeight(T s1, T s2) {
        try{
            //preleva gli indici            
            int[] indexes = new int[2];
            
            indexes[0] = summits.indexOf(s1);
            indexes[1] = summits.indexOf(s2);
            
            //verifica l'esistenza dei vertici
            if (indexes[0] == -1 || indexes[1] == -1) return -2;
            
            //ritorna l'esistenza dell'arco (la cella di adiacenza)
            return adjMatrix.get(indexes[0]).get(indexes[1]);
            
        }catch (Exception e){
            return -2;
        }
    }    

    @Override
    public int[][] getAdjMat() {
        try{
            //converte la matrice in array bidimensionale e la ritorna
            int[][] matrix = new int[adjMatrix.size()][adjMatrix.size()];            
            for (int i = 0; i < matrix.length; i++)
                for (int j = 0; j < matrix[i].length; j++) 
                    matrix[i][j] = adjMatrix.get(i).get(j);
            
            return matrix;
            
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public int countArch(T summit) {
        //ottiene l'indice del vertice
        int i = summits.indexOf(summit);

        //controlla l'0esistenza del vertice
        if (i == -1) return i;        
        
        //conta ogni true nella riga di adiacenza
        int l = 0;
        for (Integer b : adjMatrix.get(i)) if (b >= 0) l++;        
        return l;
    }

}
