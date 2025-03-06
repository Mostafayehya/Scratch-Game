package org.example;

import org.example.Domain.GameConfig;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScoreCalculatorTest {

    String configPath = Paths.get("src", "main", "resources", "config.json").toString();
    GameConfig config = ConfigLoader.loadConfig(configPath);

    @Test
    public void TestScoreCalculator() {
        // Given
        String[][] matrix = {
                {"A", "B", "C"},
                {"A", "B", "C"},
                {"A", "B", "C"}
        };
        double betAmount = 1.0;
        ScoreCalculator calculator = new ScoreCalculator(
                config.getWinCombinations(),
                config.getSymbols(),
                betAmount
        );

        // When
        Double result = calculator.calculateScore(matrix);

        System.out.println(result);

        // Then
        assertEquals(21, result);
    }

    @Test
    public void testScoreCalculatorWithBonus() {
        // Given
        String[][] matrix = {
                {"A", "A", "B"},
                {"A", "+1000", "B"},
                {"A", "A", "B"}
        };
        double betAmount = 100.0;
        ScoreCalculator calculator = new ScoreCalculator(
                config.getWinCombinations(),
                config.getSymbols(),
                betAmount
        );

        // When
        Double result = calculator.calculateScore(matrix);

        System.out.println(result);

        // Then
        assertEquals(3600, result);
    }

}