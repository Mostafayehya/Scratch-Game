package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GameConfig {
    private int rows;
    private int columns;
    private Map<String, SymbolConfig> symbols;
    private ProbabilityConfig probabilities;

    public GameConfig() {
    }

    public GameConfig(int rows, int columns, Map<String, SymbolConfig> symbols, ProbabilityConfig probabilities) {
        this.rows = rows;
        this.columns = columns;
        this.symbols = symbols;
        this.probabilities = probabilities;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public ProbabilityConfig getProbabilities() {
        return probabilities;
    }

    public void setProbabilities(ProbabilityConfig probabilities) {
        this.probabilities = probabilities;
    }

    public Map<String, SymbolConfig> getSymbols() {
        return symbols;
    }

    public void setSymbols(Map<String, SymbolConfig> symbols) {
        this.symbols = symbols;
    }
}
