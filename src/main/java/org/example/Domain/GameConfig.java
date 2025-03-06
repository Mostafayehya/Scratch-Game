package org.example.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GameConfig(
        int rows,
        int columns,
        Map<String, SymbolConfig> symbols,
        ProbabilityConfig probabilities,
        @JsonProperty("win_combinations") Map<String, WinCombination> winCombinations
) {
    public GameConfig {
        symbols = Map.copyOf(symbols);
        winCombinations = Map.copyOf(winCombinations);
    }
}