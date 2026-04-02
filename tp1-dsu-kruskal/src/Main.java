import benchmark.Benchmark;
import benchmark.BenchmarkResult;
import model.DsTypeEnum;
import utils.Metrics;

public class Main {
    public static void main(String[] args) {
        System.out.println("===== Projeto FPAA =====");

        int[] graphSizes = new int[]{100, 500, 1000, 5000, 10000};
        double density = 0.2;

        BenchmarkResult benchmarkResult = new BenchmarkResult(); 

        for(DsTypeEnum dsType : DsTypeEnum.values()) {
            Benchmark.runBenchmarks(benchmarkResult, dsType, graphSizes, density);
        }

        Metrics.exportToCSV(benchmarkResult, "benchmark_results.csv");
    }
}