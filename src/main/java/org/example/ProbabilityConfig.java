package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ProbabilityConfig {
    @JsonProperty("standard_symbols")
    private List<StandardSymbolProbability> standardSymbols;
    @JsonProperty("bonus_symbols")
    private BonusSymbolProbability bonusSymbols;

    public ProbabilityConfig() {
    }

    public ProbabilityConfig(List<StandardSymbolProbability> standardSymbols, BonusSymbolProbability bonusSymbols) {
        this.standardSymbols = standardSymbols;
        this.bonusSymbols = bonusSymbols;
    }

    public List<StandardSymbolProbability> getStandardSymbols() {
        return standardSymbols;
    }

    public void setStandardSymbols(List<StandardSymbolProbability> standardSymbols) {
        this.standardSymbols = standardSymbols;
    }

    public BonusSymbolProbability getBonusSymbols() {
        return bonusSymbols;
    }

    public void setBonusSymbols(BonusSymbolProbability bonusSymbols) {
        this.bonusSymbols = bonusSymbols;
    }
}
