package org.example.Domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ProbabilityConfig(
        @JsonProperty("standard_symbols")
        List<StandardSymbolProbability> standardSymbols,
        @JsonProperty("bonus_symbols")
        BonusSymbolProbability bonusSymbols
) {
    public ProbabilityConfig {
        standardSymbols = List.copyOf(standardSymbols);
    }
}
