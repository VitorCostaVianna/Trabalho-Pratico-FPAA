package model;

public class BenchmarkResultModel {
    private long memoryAccesses;
    private double executionTime;

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