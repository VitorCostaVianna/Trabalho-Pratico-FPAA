import benchmark.Benchmark;
import benchmark.BenchmarkResult;
import model.DsTypeEnum;

public class Main {
    public static void main(String[] args) {
        System.out.println("===== Projeto FPAA =====");

        BenchmarkResult[] benchmarkResults = new BenchmarkResult[DsTypeEnum.values().length];

        int[] graphSizes = new int[]{100, 500, 1000};
        double density = 0.5;

        BenchmarkResult benchmarkResult = new BenchmarkResult(); 

        for(DsTypeEnum dsType : DsTypeEnum.values()) {
            Benchmark.runBenchmarks(benchmarkResult, dsType, graphSizes, density);
        }
    }
}