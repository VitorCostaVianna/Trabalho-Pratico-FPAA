package benchmark;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import model.BenchmarkResultModel;
import model.DsTypeEnum;

public class BenchmarkResult {

    private Map<DsTypeEnum, LinkedHashMap<Integer, BenchmarkResultModel>> result;

    public BenchmarkResult() {
        this.result = new HashMap<>();
    }

    public void addResult(DsTypeEnum key, int graphSize, BenchmarkResultModel value) {
        if (key == null) throw new IllegalArgumentException("DsTypeEnum type cannot be null");
        if (graphSize <= 0) throw new IllegalArgumentException("Graph size must be positive");
        if (value == null) throw new IllegalArgumentException("BenchmarkResultModel cannot be null");

        if (resultExists(key, graphSize)) {
            throw new IllegalArgumentException(
                "Result already exists for type: " + key + ", graphSize: " + graphSize
            );
        }

        this.result.computeIfAbsent(key, k -> new LinkedHashMap<>());
        this.result.get(key).put(graphSize, value);
    }

    public boolean resultExists(DsTypeEnum type, int graphSize) {
        return this.result.containsKey(type) && result.get(type).containsKey(graphSize);
    }

    public Map<DsTypeEnum, LinkedHashMap<Integer, BenchmarkResultModel>> getResult() {
        return this.result;
    }

    public Map<Integer, BenchmarkResultModel> getResultsByType(DsTypeEnum type) {
        return this.result.getOrDefault(type, new LinkedHashMap<>());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BenchmarkResult {\n");

        for(HashMap.Entry<DsTypeEnum, LinkedHashMap<Integer, BenchmarkResultModel>> entry : result.entrySet()) {
            for(HashMap.Entry<Integer, BenchmarkResultModel> subEntry : entry.getValue().entrySet()) {
                sb.append("Tamanho do grafo: ").append(subEntry.getKey()).append("\n");
                sb.append("Tipo: ").append(entry.getKey()).append("\n");
                sb.append("Acessos à memória: ").append(subEntry.getValue().getMemoryAccesses()).append("\n");
                sb.append("Tempo de execução: ").append(subEntry.getValue().getExecutionTime()).append("\n");
                sb.append("Grau Medio: ").append(subEntry.getValue().getGraphAvgDegree()).append("\n");
            }
        }

        sb.append("}");
        return sb.toString();
    }
}