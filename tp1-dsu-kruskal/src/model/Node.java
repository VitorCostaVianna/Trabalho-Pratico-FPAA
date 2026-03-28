package model;

public class Node<T> {
    private int id;
    private T value;

    public Node(int id, T value){
        this.id = id;
        this.value = value;
     }

     public int getId(){
        return this.id;
    }

     public T getValue(){
        return this.value;
    }
}
