package model;

public class Edge<T> {
    private Node<T> source;
    private Node<T> destination;
    private int weight;

    public Edge(Node<T> source, Node<T> destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Node<T> getSource(){
        return this.source;
    }

    public Node<T> getDestination(){
        return this.destination;
    }

    public int getWeight(){
        return this.weight;
    }

}
