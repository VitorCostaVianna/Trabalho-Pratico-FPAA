package model;

import java.util.Arrays;

/** Representa um grafo não-direcionado com nós e arestas genéricos. */
public class Graph<T, K> {
    private Node<T>[] nodes;
    private Edge<K>[] edges;

    /**
     * Cria um grafo com os arrays de nós e arestas fornecidos.
     *
     * @param nodes array de nós
     * @param edges array de arestas
     */
    public Graph(Node<T>[] nodes, Edge<K>[] edges){
        this.nodes = nodes;
        this.edges = edges;
    }

    public Node<T>[] getNodes(){
        return this.nodes;
    }

    public Edge<K>[] getEdges(){
        return this.edges;
    }

    /**
     * Retorna uma cópia ordenada das arestas do grafo em ordem crescente de peso.
     *
     * @return array de arestas ordenadas por peso
     */
    public Edge<K>[] getSortedEdges(){
        Edge<K>[] clonedEdge = this.edges.clone();
        Arrays.sort(clonedEdge, (a, b) -> Integer.compare(a.getWeight(), b.getWeight()));
        return clonedEdge;
    }

    /**
     * Adiciona um nó ao grafo, expandindo o array de nós.
     *
     * @param node nó a ser adicionado
     */
    public void addNode(Node<T> node){
        this.nodes = Arrays.copyOf(this.nodes, this.nodes.length + 1);
        this.nodes[this.nodes.length - 1] = node;
    }

}