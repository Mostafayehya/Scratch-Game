package org.example.Domain;

import java.util.Map;

public record BonusSymbolProbability(
        Map<String, Integer> symbols
) {
    public BonusSymbolProbability {
        symbols = Map.copyOf(symbols);
    }
}
