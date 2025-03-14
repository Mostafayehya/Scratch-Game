package org.example;

import org.example.Domain.ProbabilityConfig;

import java.util.*;

public class MatrixGenerator {
    public String[][] generate(int rows, int cols, ProbabilityConfig probabilityConfig) {
        String[][] matrix = new String[rows][cols];

        // First fill the matrix with standard symbols
        int cellIndex = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Map<String, Integer> standardProbabilities =
                        probabilityConfig.standardSymbols().get(cellIndex++).symbols();
                matrix[row][col] = getRandomSymbol(standardProbabilities);
            }
        }

        // Then potentially add bonus symbols (with a controlled amount)
        int maxBonusSymbols = 1; // Or any other reasonable limit
        int bonusSymbolsAdded = 0;
        Map<String, Integer> bonusProbs = probabilityConfig.bonusSymbols().symbols();

        while (bonusSymbolsAdded < maxBonusSymbols) {
            int randomRow = new Random().nextInt(rows);
            int randomCol = new Random().nextInt(cols);

            // Small probability to replace standard symbol with bonus
            if (new Random().nextInt(100) < 15) { // 15% chance
                String bonusSymbol = getRandomSymbol(bonusProbs);
                matrix[randomRow][randomCol] = bonusSymbol;
                bonusSymbolsAdded++;
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
}
