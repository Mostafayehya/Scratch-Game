package org.example.Domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public record GameResult(
        String[][] matrix,
        double reward,
        Map<String, List<String>> appliedWinningCombinations,
        String appliedBonusSymbol
) {
    public GameResult {
        matrix = Arrays.stream(matrix).map(String[]::clone).toArray(String[][]::new);
        appliedWinningCombinations = Map.copyOf(appliedWinningCombinations);
    }
}