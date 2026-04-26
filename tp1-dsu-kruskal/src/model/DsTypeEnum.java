package model;

/**
 * Enum com os tipos de estrutura Disjoint Set Union disponíveis.
 * <ul>
 *   <li>{@code NAIVE}  – sem otimizações</li>
 *   <li>{@code RANK}   – union by rank</li>
 *   <li>{@code TARJAN} – union by rank + compressão de caminho</li>
 * </ul>
 */
public enum DsTypeEnum {
    NAIVE,
    RANK,
    TARJAN
}
