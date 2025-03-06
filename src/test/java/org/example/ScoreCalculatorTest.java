package org.example;

import org.example.Domain.GameConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScoreCalculatorTest {
    private GameConfig config;
    private ScoreCalculator calculator;
    private static final String CONFIG_PATH = Paths.get("src", "main", "resources", "config.json").toString();
    private static final double STANDARD_BET = 100.0;
    private static final double SMALL_BET = 1.0;

    @BeforeEach
    void setUp() {
        config = ConfigLoader.loadConfig(CONFIG_PATH);
    }

    private ScoreCalculator createCalculator(double betAmount) {
        return new ScoreCalculator(config.winCombinations(), config.symbols(), betAmount);
    }

    private Double calculateScore(String[][] matrix, double betAmount) {
        calculator = createCalculator(betAmount);
        return calculator.calculateScore(matrix);
    }

    @Test
    void testVerticalLinesCombination() {
        String[][] matrix = {
                {"A", "B", "C"},
                {"A", "B", "C"},
                {"A", "B", "C"}
        };

        Double result = calculateScore(matrix, SMALL_BET);
        assertEquals(21, result, "Should calculate correct reward for vertical lines");
    }

    @Test
    void testSameSymbolsWithBonus() {
        String[][] matrix = {
                {"A", "A", "B"},
                {"A", "+1000", "B"},
                {"A", "A", "B"}
        };

        Double result = calculateScore(matrix, STANDARD_BET);
        assertEquals(3600, result, "Should calculate correct reward with bonus symbol");
    }

    @Test
    void testLostGame() {
        String[][] matrix = {
                {"A", "B", "C"},
                {"E", "B", "5x"},
                {"F", "D", "C"}
        };

        Double result = calculateScore(matrix, STANDARD_BET);
        assertEquals(0, result, "Should return 0 for lost game");
    }

    @Test
    void testThreeSameSymbolsWithMultiplier() {
        String[][] matrix = {
                {"A", "B", "C"},
                {"E", "B", "10x"},
                {"F", "D", "B"}
        };

        Double result = calculateScore(matrix, STANDARD_BET);
        assertEquals(3000, result, "Should calculate correct reward for three same symbols with multiplier");
    }}