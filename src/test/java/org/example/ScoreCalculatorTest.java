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

    @Test
    public void testThirdExampleOfLostGame(){

        // Given
        String[][] matrix = {
                {"A", "B", "C"},
                {"E", "B", "5x"},
                {"F", "D", "C"}
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
        assertEquals(0, result);
    }

    @Test
    public void testFourthExample(){

        // Given
        String[][] matrix = {
                {"A", "B", "C"},
                {"E", "B", "10x"},
                {"F", "D", "B"}
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
        assertEquals(3000, result);
    }
}