package model;

public class BenchmarkResultModel {
    private long memoryAccesses;
    private double executionTime;
    private double graphDensity;

    public BenchmarkResultModel(long memoryAccesses, double executionTime, double graphDensity) {
        this.memoryAccesses = memoryAccesses;
        this.executionTime = executionTime;
        this.graphDensity = graphDensity;
    }

    public long getMemoryAccesses() {
        return this.memoryAccesses;
    }

    public double getExecutionTime() {
        return this.executionTime;
    }

    public double getGraphDensity() {
        return this.graphDensity;
    }
}