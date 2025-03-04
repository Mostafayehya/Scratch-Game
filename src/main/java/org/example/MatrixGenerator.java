package org.example;

import java.util.*;

public class MatrixGenerator {
    public String[][] generateMatrix(int rows, int cols, List<Map<String, Integer>> standardSymbolsPerCell, Map<String, Integer> bonusSymbols) {
        // Combine standard and bonus probabilities for each cell
        List<Map<String, Integer>> combinedProbabilities = combineCellProbabilities(standardSymbolsPerCell, bonusSymbols);

        String[][] matrix = new String[rows][cols];
        int cellIndex = 0;

        // Generate matrix based on combined probabilities
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // Use the combined probabilities for this specific cell
                Map<String, Integer> cellProbabilities = combinedProbabilities.get(cellIndex++);
                matrix[row][col] = getRandomSymbol(cellProbabilities);
            }
        }

        return matrix;
    }

    private String getRandomSymbol(Map<String, Integer> symbols) {
        // Create a weighted random selection
        int totalWeight = symbols.values().stream().mapToInt(Integer::intValue).sum();
        int randomValue = new Random().nextInt(totalWeight);

        int currentWeight = 0;
        for (Map.Entry<String, Integer> entry : symbols.entrySet()) {
            currentWeight += entry.getValue();
            if (randomValue < currentWeight) {
                return entry.getKey();
            }
        }
        return null; // Should never reach here if probabilities are valid
    }

    private List<Map<String, Integer>> combineCellProbabilities(List<Map<String, Integer>> standardSymbolsPerCell, Map<String, Integer> bonusSymbols) {
        List<Map<String, Integer>> combinedProbabilities = new ArrayList<>();

        // Combine for each standard symbol map (per cell)
        for (Map<String, Integer> cellProbabilities : standardSymbolsPerCell) {
            Map<String, Integer> combinedMap = new HashMap<>(cellProbabilities);

            // Add global bonus probabilities to each cell
            bonusSymbols.forEach((key, value) ->
                    combinedMap.merge(key, value, Integer::sum)
            );

            combinedProbabilities.add(combinedMap);
        }

        return combinedProbabilities;
    }

}
