package utils;

import benchmark.BenchmarkResult;
import model.BenchmarkResultModel;
import model.DsTypeEnum;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class Metrics {

    public static void exportToCSV(BenchmarkResult benchmarkResult, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.append("DSU_Type,Graph_Size,Avg_Time_ms,Avg_Memory_Accesses\n");
            
            Map<DsTypeEnum, LinkedHashMap<Integer, BenchmarkResultModel>> resultsMap = benchmarkResult.getResult();

            for (Map.Entry<DsTypeEnum, LinkedHashMap<Integer, BenchmarkResultModel>> entry : resultsMap.entrySet()) {
                DsTypeEnum type = entry.getKey();
                
                for (Map.Entry<Integer, BenchmarkResultModel> subEntry : entry.getValue().entrySet()) {
                    int graphSize = subEntry.getKey();
                    BenchmarkResultModel resultModel = subEntry.getValue();
                      String avgExecutionTimeFormatted = String.format(Locale.US, "%.5f", resultModel.getExecutionTime());

                    writer.append(type.name()).append(",")
                          .append(String.valueOf(graphSize)).append(",")
                          .append(avgExecutionTimeFormatted).append(",")
                          .append(String.valueOf(resultModel.getMemoryAccesses())).append("\n");
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao exportar arquivo CSV: " + e.getMessage());
        }
    }

    public static void printResultsToConsole(BenchmarkResult benchmarkResult) {
        System.out.println("\n=================================================================================");
        System.out.printf("%-15s | %-12s | %-15s | %-20s\n",
            "Tipo de DSU", "N (Nós)", "Tempo Médio (ms)", "Acessos Médios Mem.");
        System.out.println("---------------------------------------------------------------------------------");

        Map<DsTypeEnum, LinkedHashMap<Integer, BenchmarkResultModel>> resultsMap = benchmarkResult.getResult();

        for (Map.Entry<DsTypeEnum, LinkedHashMap<Integer, BenchmarkResultModel>> entry : resultsMap.entrySet()) {
            DsTypeEnum type = entry.getKey();
            
            for (Map.Entry<Integer, BenchmarkResultModel> subEntry : entry.getValue().entrySet()) {
                int graphSize = subEntry.getKey();
                BenchmarkResultModel resultModel = subEntry.getValue();
                String avgExecutionTimeFormatted = String.format(Locale.US, "%.5f", resultModel.getExecutionTime());

                System.out.printf("%-15s | %-12d | %-15s | %-20d\n",
                    type.name(),
                    graphSize,
                    avgExecutionTimeFormatted,
                    resultModel.getMemoryAccesses());
            }
        }
        System.out.println("=================================================================================\n");
    }
}