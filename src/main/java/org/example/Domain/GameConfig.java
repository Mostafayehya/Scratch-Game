package org.example.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GameConfig {
    private int rows;
    private int columns;
    private Map<String, SymbolConfig> symbols;
    private ProbabilityConfig probabilities;
    @JsonProperty("win_combinations")
    private Map<String, WinCombination> winCombinations;

    public GameConfig() {
    }

    public GameConfig(int rows, int columns, Map<String, SymbolConfig> symbols, ProbabilityConfig probabilities, Map<String, WinCombination> winCombinations) {
        this.rows = rows;
        this.columns = columns;
        this.symbols = symbols;
        this.probabilities = probabilities;
        this.winCombinations = winCombinations;
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

    public Map<String, WinCombination> getWinCombinations() {
        return winCombinations;
    }

    public void setWinCombinations(Map<String, WinCombination> winCombinations) {
        this.winCombinations = winCombinations;
    }
}
