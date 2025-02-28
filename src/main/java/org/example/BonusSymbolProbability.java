package org.example;

import java.util.Map;

public class BonusSymbolProbability {
    private Map<String, Integer> symbols;

    public BonusSymbolProbability() {
    }

    public BonusSymbolProbability(Map<String, Integer> symbols) {
        this.symbols = symbols;
    }

    public Map<String, Integer> getSymbols() {
        return symbols;
    }

    public void setSymbols(Map<String, Integer> symbols) {
        this.symbols = symbols;
    }


}
