package benchmark;

import algorithms.Kruskal;
import interfaces.DisjointSet;
import model.BenchmarkResultModel;
import model.DsTypeEnum;
import model.Graph;
import utils.DSUFactory;
import utils.GraphGenerator;

/** Executa benchmarks do algoritmo de Kruskal para diferentes tamanhos de grafo e tipos de DSU. */
public class Benchmark {
    /**
     * Roda o algoritmo de Kruskal {@code 3} vezes para cada tamanho de grafo e registra
     * a média de tempo de execução (em ms) e de acessos à memória no {@code result}.
     *
     * @param result     contêiner onde os resultados serão armazenados
     * @param ds         tipo de DSU a ser usado
     * @param graphSizes array com os tamanhos de grafo a serem testados
     */
    public static void runBenchmarks(BenchmarkResult result, DsTypeEnum ds, int[] graphSizes) {
        int repetitions = 3;

        for(int graphSize : graphSizes) {
            Graph<Integer, Integer> graph = GraphGenerator.generateGraph(graphSize);
            
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
                avgExecutionTimeMs
            );
            
            result.addResult(ds, graphSize, bmResult);
        }

    }
}
