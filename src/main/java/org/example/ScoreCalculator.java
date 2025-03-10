package org.example;

import org.example.Domain.GameResult;
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

    public GameResult calculateScore(String[][] matrix) {
        double totalReward = 0;
        // Calculate standard symbol combinations
        Map<String, List<String>> appliedWinningCombinations = new HashMap<>();
        for (String symbol : findUniqueStandardSymbols(matrix)) {
            double symbolReward = calculateSymbolReward(matrix, symbol, appliedWinningCombinations);
            if (symbolReward > 0) {
                totalReward += symbolReward;
            }
        }

        // Apply bonus symbol if any winning combinations were found
        String bonusSymbol = findBonusSymbol(matrix);
        if (totalReward > 0) {
            if (bonusSymbol != null) {
                SymbolConfig bonusConfig = symbols.get(bonusSymbol);
                if ("multiply_reward".equals(bonusConfig.impact()) && bonusConfig.rewardMultiplier() != 0) {
                    totalReward *= bonusConfig.rewardMultiplier();
                } else if ("extra_bonus".equals(bonusConfig.impact())) {
                    totalReward += bonusConfig.extra();
                }
            }
        }

        return new GameResult(matrix, totalReward, appliedWinningCombinations, bonusSymbol);
    }

    private double calculateSymbolReward(String[][] matrix, String symbol, Map<String, List<String>> appliedWinningCombinations) {
        double reward = 0;
        int symbolCount = countSymbolOccurrences(matrix, symbol);
        SymbolConfig symbolConfig = symbols.get(symbol);

        // Check same symbol counts
        for (Map.Entry<String, WinCombination> entry : winCombinations.entrySet()) {
            WinCombination winCombination = entry.getValue();
            if ("same_symbols".equals(winCombination.when()) && symbolCount == winCombination.count()) {
                appliedWinningCombinations.put(symbol, List.of(entry.getKey()));
                reward = betAmount * symbolConfig.rewardMultiplier() * winCombination.rewardMultiplier();
                break;
            }
        }

        // Check linear and diagonal patterns
        for (Map.Entry<String, WinCombination> entry : winCombinations.entrySet()) {
            WinCombination winCombination = entry.getValue();
            if ("linear_symbols".equals(winCombination.when()) && hasLinearOrDiagonalPattern(matrix, symbol, winCombination)) {
                appliedWinningCombinations.put(symbol, List.of(entry.getKey()));
                reward *= winCombination.rewardMultiplier();
            }
        }

        return reward;
    }

    private boolean hasLinearOrDiagonalPattern(String[][] matrix, String symbol, WinCombination winCombination) {
        if (winCombination.coveredAreas() == null) return false;

        for (List<String> pattern : winCombination.coveredAreas()) {
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
                if (symbols.containsKey(cell) && "bonus".equals(symbols.get(cell).type())) {
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
                if (symbols.containsKey(cell) && "standard".equals(symbols.get(cell).type())) {
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