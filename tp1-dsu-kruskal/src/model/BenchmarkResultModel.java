package model;

public class BenchmarkResultModel {
    private long memoryAccesses;
    private long executionTime;
    private double graphDensity;

    public BenchmarkResultModel(long memoryAccesses, long executionTime, double graphDensity) {
        this.memoryAccesses = memoryAccesses;
        this.executionTime = executionTime;
        this.graphDensity = graphDensity;
    }

    public long getMemoryAccesses() {
        return this.memoryAccesses;
    }

    public long getExecutionTime() {
        return this.executionTime;
    }

    public double getGraphDensity() {
        return this.graphDensity;
    }
}