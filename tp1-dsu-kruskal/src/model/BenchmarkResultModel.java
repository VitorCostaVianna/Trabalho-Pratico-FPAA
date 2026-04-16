package model;

public class BenchmarkResultModel {
    private long memoryAccesses;
    private double executionTime;
    private int graphAvgDegree;

    public BenchmarkResultModel(long memoryAccesses, double executionTime, int graphAvgDegree) {
        this.memoryAccesses = memoryAccesses;
        this.executionTime = executionTime;
        this.graphAvgDegree = graphAvgDegree;
    }

    public long getMemoryAccesses() {
        return this.memoryAccesses;
    }

    public double getExecutionTime() {
        return this.executionTime;
    }

    public int getGraphAvgDegree() {
        return this.graphAvgDegree;
    }
}