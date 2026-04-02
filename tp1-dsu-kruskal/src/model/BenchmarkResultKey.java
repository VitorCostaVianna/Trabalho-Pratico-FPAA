package model;

public class BenchmarkResultKey {
    private DsTypeEnum type;
    private int graphSize;

    public BenchmarkResultKey(DsTypeEnum type, int graphSize) {
        this.type = type;
        this.graphSize = graphSize;
    }

    public DsTypeEnum getType() {
        return this.type;
    }

    public int getGraphSize() {
        return this.graphSize;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BenchmarkResultKey that = (BenchmarkResultKey) obj;
        return this.graphSize == that.graphSize && this.type == that.type;
    }

}