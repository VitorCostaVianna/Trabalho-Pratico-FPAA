package utils;

import ds.NaiveDSU;
import ds.RankDSU;
import ds.TarjanDSU;
import interfaces.DisjointSet;
import model.DsTypeEnum;

public class DSUFactory {
    public static DisjointSet getDSU(DsTypeEnum type, int n) {
        switch (type) {
            case NAIVE:
                return new NaiveDSU(n);
            case RANK:
                return new RankDSU(n);
            case TARJAN:
                return new TarjanDSU(n);
            default:
                throw new IllegalArgumentException("Tipo de DSU não suportado: " + type);
        }
    }
}