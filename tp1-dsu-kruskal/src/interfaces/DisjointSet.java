package interfaces;

public interface DisjointSet {
    int find(int i);
    boolean union(int i, int j);
    long getMemoryAccesses();
}