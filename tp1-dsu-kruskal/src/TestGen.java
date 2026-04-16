import java.util.Random;
import java.util.HashSet;
import model.Node;
import model.Edge;
import utils.GraphGenerator;

public class TestGen {
    public static void main(String[] args) {
        System.out.println("Testing graph generation...");
        long start = System.currentTimeMillis();
        // Testando 1 milhão de vértices, mas com grau médio = 5
        // Total de arestas ficará em (1.000.000 * 5) / 2 = 2.500.000 arestas.
        GraphGenerator.generateGraph(1_000_000, 5);
        long end = System.currentTimeMillis();
        System.out.println("Done! Time: " + (end - start) + "ms");
    }
}
