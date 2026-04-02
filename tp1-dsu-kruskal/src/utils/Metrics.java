package utils;

import benchmark.BenchmarkResult;
import model.BenchmarkResultModel;
import model.DsTypeEnum;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Metrics {

    public static void exportToCSV(BenchmarkResult benchmarkResult, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.append("DSU_Type,Graph_Size,Graph_Density,Time_ms,Memory_Accesses\n");
            
            Map<DsTypeEnum, LinkedHashMap<Integer, BenchmarkResultModel>> resultsMap = benchmarkResult.getResult();

            for (Map.Entry<DsTypeEnum, LinkedHashMap<Integer, BenchmarkResultModel>> entry : resultsMap.entrySet()) {
                DsTypeEnum type = entry.getKey();
                
                for (Map.Entry<Integer, BenchmarkResultModel> subEntry : entry.getValue().entrySet()) {
                    int graphSize = subEntry.getKey();
                    BenchmarkResultModel resultModel = subEntry.getValue();

                    writer.append(type.name()).append(",")
                          .append(String.valueOf(graphSize)).append(",")
                          .append(String.valueOf(resultModel.getGraphDensity())).append(",")
                          .append(String.valueOf(resultModel.getExecutionTime())).append(",")
                          .append(String.valueOf(resultModel.getMemoryAccesses())).append("\n");
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao exportar arquivo CSV: " + e.getMessage());
        }
    }

    public static void printResultsToConsole(BenchmarkResult benchmarkResult) {
        System.out.println("\n=============================================================================================");
        System.out.printf("%-15s | %-12s | %-15s | %-15s | %-20s\n", 
            "Tipo de DSU", "N (Nós)", "Densidade", "Tempo (ms)", "Acessos à Memória");
        System.out.println("---------------------------------------------------------------------------------------------");

        Map<DsTypeEnum, LinkedHashMap<Integer, BenchmarkResultModel>> resultsMap = benchmarkResult.getResult();

        for (Map.Entry<DsTypeEnum, LinkedHashMap<Integer, BenchmarkResultModel>> entry : resultsMap.entrySet()) {
            DsTypeEnum type = entry.getKey();
            
            for (Map.Entry<Integer, BenchmarkResultModel> subEntry : entry.getValue().entrySet()) {
                int graphSize = subEntry.getKey();
                BenchmarkResultModel resultModel = subEntry.getValue();

                System.out.printf("%-15s | %-12d | %-15.2f | %-15d | %-20d\n", 
                    type.name(), 
                    graphSize, 
                    resultModel.getGraphDensity(), 
                    resultModel.getExecutionTime(), 
                    resultModel.getMemoryAccesses());
            }
        }
        System.out.println("=============================================================================================\n");
    }
}