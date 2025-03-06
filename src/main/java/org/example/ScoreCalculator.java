package org.example;

import org.example.Domain.SymbolConfig;
import org.example.Domain.WinCombination;

import java.util.*;

public class ScoreCalculator {
    private final Map<String, WinCombination> winCombinations;
    private final Map<String, SymbolConfig> symbols;
    private final double betAmount;

    public ScoreCalculator(Map<String, WinCombination> winCombinations, Map<String, SymbolConfig> symbols, double betAmount) {
        this.winCombinations = winCombinations;
        this.symbols = symbols;
        this.betAmount = betAmount;
    }

    public double calculateScore(String[][] matrix) {
        double totalReward = 0;
        // Calculate standard symbol combinations
        for (String symbol : findUniqueStandardSymbols(matrix)) {
            double symbolReward = calculateSymbolReward(matrix, symbol);
            if (symbolReward > 0) {
                totalReward += symbolReward;
            }
        }

        // Apply bonus symbol if any winning combinations were found
        if (totalReward > 0) {
            String bonusSymbol = findBonusSymbol(matrix);
            if (bonusSymbol != null) {
                SymbolConfig bonusConfig = symbols.get(bonusSymbol);
                if ("multiply_reward".equals(bonusConfig.getImpact()) && bonusConfig.getRewardMultiplier() != 0) {
                    totalReward *= bonusConfig.getRewardMultiplier();
                } else if ("extra_bonus".equals(bonusConfig.getImpact())) {
                    totalReward += bonusConfig.getExtra();
                }
            }
        }

        return totalReward;
    }

    private double calculateSymbolReward(String[][] matrix, String symbol) {
        double reward = 0;
        int symbolCount = countSymbolOccurrences(matrix, symbol);
        SymbolConfig symbolConfig = symbols.get(symbol);

        // Check same symbol counts
        for (Map.Entry<String, WinCombination> entry : winCombinations.entrySet()) {
            WinCombination combo = entry.getValue();
            if ("same_symbols".equals(combo.getWhen()) && symbolCount == combo.getCount()) {
                reward = betAmount * symbolConfig.getRewardMultiplier() * combo.getRewardMultiplier();
                break;
            }
        }

        // Check linear patterns
        for (Map.Entry<String, WinCombination> entry : winCombinations.entrySet()) {
            WinCombination combo = entry.getValue();
            if ("linear_symbols".equals(combo.getWhen()) && hasLinearPattern(matrix, symbol, combo)) {
                reward *=  combo.getRewardMultiplier();
            }
        }

        return reward;
    }

    private boolean hasLinearPattern(String[][] matrix, String symbol, WinCombination combo) {
        if (combo.getCoveredAreas() == null) return false;

        for (List<String> pattern : combo.getCoveredAreas()) {
            boolean matches = pattern.stream().allMatch(pos -> {
                String[] coordinates = pos.split(":");
                int row = Integer.parseInt(coordinates[0]);
                int col = Integer.parseInt(coordinates[1]);
                return matrix[row][col].equals(symbol);
            });
            if (matches) return true;
        }
        return false;
    }

    private String findBonusSymbol(String[][] matrix) {
        for (String[] row : matrix) {
            for (String cell : row) {
                if (symbols.containsKey(cell) && "bonus".equals(symbols.get(cell).getType())) {
                    return cell;
                }
            }
        }
        return null;
    }

    private Set<String> findUniqueStandardSymbols(String[][] matrix) {
        Set<String> standardSymbols = new HashSet<>();
        for (String[] row : matrix) {
            for (String cell : row) {
                if (symbols.containsKey(cell) && "standard".equals(symbols.get(cell).getType())) {
                    standardSymbols.add(cell);
                }
            }
        }
        return standardSymbols;
    }

    private int countSymbolOccurrences(String[][] matrix, String symbol) {
        int count = 0;
        for (String[] row : matrix) {
            for (String cell : row) {
                if (cell.equals(symbol)) count++;
            }
        }
        return count;
    }
}