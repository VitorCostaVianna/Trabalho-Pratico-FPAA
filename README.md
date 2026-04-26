# Trabalho Prático — FPAA

Implementação e análise de desempenho do algoritmo de Kruskal para Árvore Geradora Mínima (MST), comparando três variantes da estrutura Disjoint Set Union (DSU):

- **Naive** — sem otimizações
- **Rank** — union by rank
- **Tarjan** — union by rank + compressão de caminho

## Pré-requisitos

| Ferramenta | Versão mínima |
|------------|---------------|
| Java (JDK) | 25            |
| Maven      | 3.8+          |

## Como rodar

### 1. Clone o repositório

```bash
git clone https://github.com/VitorCostaVianna/Trabalho-Pratico-FPAA.git
cd Trabalho-Pratico-FPAA
```

### 2. Compile o projeto

```bash
cd tp1-dsu-kruskal
mvn compile
```

### 3. Execute o benchmark

```bash
java -Xms512M -Xmx8G -cp target/classes src/Main.java
```

O programa roda o algoritmo de Kruskal com cada variante de DSU sobre grafos de tamanhos variados (100, 500, 1000, 5000, 10000, 50000, 100000, 1000000 vértices) e exibe os resultados no console.

### 4. Resultados

Ao final da execução, um arquivo `benchmark_results.csv` é gerado na raiz do projeto com os tempos de execução de cada variante por tamanho de grafo.

## Estrutura do projeto

```
tp1-dsu-kruskal/
├── src/
│   ├── Main.java
│   ├── algorithms/       # Implementação do Kruskal
│   ├── benchmark/        # Lógica de benchmark e coleta de resultados
│   ├── ds/               # Implementações de DSU (Naive, Rank, Tarjan)
│   ├── interfaces/       # Interfaces DisjointSet e Algorithms
│   ├── model/            # Modelos (Graph, Edge, Node, etc.)
│   └── utils/            # Utilitários (gerador de grafos, métricas, exportação CSV)
├── pom.xml
```