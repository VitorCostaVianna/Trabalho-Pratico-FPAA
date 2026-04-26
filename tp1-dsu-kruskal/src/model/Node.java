package model;

/** Representa um nó do grafo com identificador inteiro e valor genérico. */
public class Node<T> {
    private int id;
    private T value;

    /**
     * Cria um nó com o id e valor fornecidos.
     *
     * @param id    identificador único do nó
     * @param value valor associado ao nó
     */
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
