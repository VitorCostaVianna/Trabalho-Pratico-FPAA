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

        for(int graphSize : graphSizes) {
            Graph<Integer, Integer> graph = GraphGenerator.generateGraph(graphSize, density);
            DisjointSet dsu = DSUFactory.getDSU(ds, graphSize);
            
            long startTime = System.nanoTime();
            Kruskal.executar(graph, dsu);
            long endTime = System.nanoTime();

            long executionTimeMs = (endTime - startTime) / 1000000;

            BenchmarkResultModel bmResult = new BenchmarkResultModel(
                dsu.getMemoryAccesses(),
                executionTimeMs,
                density
            );
            
            result.addResult(ds, graphSize, bmResult);
        }

    }
}
