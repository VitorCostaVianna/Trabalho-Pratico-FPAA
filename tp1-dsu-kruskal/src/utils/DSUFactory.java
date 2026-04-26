package utils;

import ds.NaiveDSU;
import ds.RankDSU;
import ds.TarjanDSU;
import interfaces.DisjointSet;
import model.DsTypeEnum;

/** Fábrica para instanciar implementações de {@link DisjointSet} pelo tipo enumerado. */
public class DSUFactory {
    /**
     * Cria e retorna uma instância de DSU correspondente ao tipo informado.
     *
     * @param type tipo de DSU desejado
     * @param n    número de elementos da estrutura
     * @return instância de {@link DisjointSet} inicializada para {@code n} elementos
     * @throws IllegalArgumentException se o tipo não for suportado
     */
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