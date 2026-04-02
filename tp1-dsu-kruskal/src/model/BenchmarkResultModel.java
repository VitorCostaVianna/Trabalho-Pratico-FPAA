package model;

public class BenchmarkResultModel {
    private int memoryAccesses;
    private double executionTime;
    private double graphDensity;

    public BenchmarkResultModel(DsTypeEnum type, int memoryAccesses, double executionTime, double graphDensity) {
        this.memoryAccesses = memoryAccesses;
        this.executionTime = executionTime;
        this.graphDensity = graphDensity;
    }

    public int getMemoryAccesses() {
        return this.memoryAccesses;
    }

    public double getExecutionTime() {
        return this.executionTime;
    }

    public double getGraphDensity() {
        return this.graphDensity;
    }
}