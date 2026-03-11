package ds;

public interface DisjointSet {
    int find(int i);
    void union(int i, int j);
    long getMemoryAccesses();
}