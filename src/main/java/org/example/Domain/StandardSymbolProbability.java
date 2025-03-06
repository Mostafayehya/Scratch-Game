package org.example.Domain;

import java.util.Map;

public record StandardSymbolProbability (
         int column,
         int row,
         Map<String, Integer> symbols
){
    public StandardSymbolProbability {
        symbols = Map.copyOf(symbols);
    }
}
