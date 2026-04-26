package model;

/** Armazena os resultados médios de uma execução do benchmark para um par (DSU, tamanho de grafo). */
public class BenchmarkResultModel {
    private long memoryAccesses;
    private double executionTime;

    /**
     * Cria o modelo com os valores médios de acessos à memória e tempo de execução.
     *
     * @param memoryAccesses acessos médios à memória
     * @param executionTime  tempo médio de execução em milissegundos
     */
    public BenchmarkResultModel(long memoryAccesses, double executionTime) {
        this.memoryAccesses = memoryAccesses;
        this.executionTime = executionTime;
    }

    public long getMemoryAccesses() {
        return this.memoryAccesses;
    }

    public double getExecutionTime() {
        return this.executionTime;
    }

}