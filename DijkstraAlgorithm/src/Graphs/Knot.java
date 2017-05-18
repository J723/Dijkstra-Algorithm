
package Graphs;

/*
 * @param <T>
 */
public class Knot<T> {
    public T value;
    private Integer weight;   
    
    //public Knot(){}
    
    public Knot(T value, Integer weight){
        this.value = value;
        this.weight = weight;
    }
    
    public Knot(T value){
        this.value = value;
        weight = 0;
    }
    
    public boolean setWeight(int val){
        
        weight = val;           
        if (val < 0) return false;
        else return true;
    }
    
    public int getWeight(){
        return weight;
    }
}
