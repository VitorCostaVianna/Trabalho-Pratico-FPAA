package model;

/** Representa uma aresta ponderada não-direcionada entre dois nós do grafo. */
public class Edge<T> {
    private Node<T> source;
    private Node<T> destination;
    private int weight;

    /**
     * Cria uma aresta entre {@code source} e {@code destination} com o peso fornecido.
     *
     * @param source      nó de origem
     * @param destination nó de destino
     * @param weight      peso da aresta
     */
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
