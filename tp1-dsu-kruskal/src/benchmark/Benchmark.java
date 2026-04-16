package benchmark;

import algorithms.Kruskal;
import interfaces.DisjointSet;
import model.BenchmarkResultModel;
import model.DsTypeEnum;
import model.Graph;
import utils.DSUFactory;
import utils.GraphGenerator;

public class Benchmark {
    public static void runBenchmarks(BenchmarkResult result, DsTypeEnum ds, int[] graphSizes, double density) {
        int repetitions = 3;

        for(int graphSize : graphSizes) {
            Graph<Integer, Integer> graph = GraphGenerator.generateGraph(graphSize, density);
            
            long totalExecutionTimeNs = 0;
            long totalMemoryAccesses = 0;

            for (int i = 0; i < repetitions; i++) {
                DisjointSet dsu = DSUFactory.getDSU(ds, graphSize);
                
                long startTime = System.nanoTime();
                Kruskal.executar(graph, dsu);
                long endTime = System.nanoTime();
                
                totalExecutionTimeNs += (endTime - startTime);
                totalMemoryAccesses += dsu.getMemoryAccesses();
            }

            double avgExecutionTimeMs = (double) totalExecutionTimeNs / repetitions / 1_000_000.0;
            long avgMemoryAccesses = totalMemoryAccesses / repetitions;

            BenchmarkResultModel bmResult = new BenchmarkResultModel(
                avgMemoryAccesses,
                avgExecutionTimeMs,
                density
            );
            
            result.addResult(ds, graphSize, bmResult);
        }

    }
}
