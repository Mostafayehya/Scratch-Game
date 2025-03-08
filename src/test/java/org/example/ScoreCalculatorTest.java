package org.example;

import org.example.Domain.GameConfig;
import org.example.Domain.GameResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    private GameResult calculateScore(String[][] matrix, double betAmount) {
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

        GameResult result = calculateScore(matrix, SMALL_BET);
        assertEquals(21, result.reward(), "Should calculate correct reward for vertical lines");
    }

    @Test
    void testLTRDiagonalLinesCombination() {
        String[][] matrix = {
                {"A", "B", "C"},
                {"E", "A", "C"},
                {"F", "B", "A"}
        };

        GameResult result = calculateScore(matrix, SMALL_BET);
        assertEquals(25, result.reward(), "Should calculate correct reward for LTR diagonal lines");
    }

    @Test
    void testRTLDiagonalLinesCombination() {
        String[][] matrix = {
                {"C", "B", "A"},
                {"E", "A", "C"},
                {"A", "B", "F"}
        };

        GameResult result = calculateScore(matrix, SMALL_BET);
        assertEquals(25, result.reward(), "Should calculate correct reward for RTL diagonal lines");
    }

    @Test
    void testSameSymbolsWithBonus() {
        String[][] matrix = {
                {"A", "A", "B"},
                {"A", "+1000", "B"},
                {"A", "A", "B"}
        };

        GameResult result = calculateScore(matrix, STANDARD_BET);
        assertEquals(3600, result.reward(), "Should calculate correct reward with bonus symbol");
    }

    @Test
    void testLostGame() {
        String[][] matrix = {
                {"A", "B", "C"},
                {"E", "B", "5x"},
                {"F", "D", "C"}
        };

        GameResult result = calculateScore(matrix, STANDARD_BET);
        assertEquals(0, result.reward(), "Should return 0 for lost game");
    }

    @Test
    void testThreeSameSymbolsWithMultiplier() {
        String[][] matrix = {
                {"A", "B", "C"},
                {"E", "B", "10x"},
                {"F", "D", "B"}
        };

        GameResult result = calculateScore(matrix, STANDARD_BET);
        assertEquals(3000, result.reward(), "Should calculate correct reward for three same symbols with multiplier");
    }

    @Test
    void testGameResultFormat() {
        String[][] matrix = {
                {"A", "B", "C"},
                {"E", "B", "10x"},
                {"F", "D", "B"}
        };

        GameResult result = calculateScore(matrix, STANDARD_BET);
        assertEquals(3000, result.reward(), "Should calculate correct reward for three same symbols with multiplier");
        assertEquals(List.of("same_symbol_3_times"), result.appliedWinningCombinations().get("B"), "Should have one winning combination");
        assertEquals("10x", result.appliedBonusSymbol(), "Should have correct bonus symbol");
        assertEquals(3, result.matrix().length, "Should have correct number of rows");
        assertEquals(3, result.matrix()[0].length, "Should have correct number of columns");
    }

    @Test
    void testWhenLostGameThereShouldBeNoWinningCombinations() {
        String[][] lostMatrix = {
                {"A", "B", "C"},
                {"D", "E", "F"},
                {"G", "H", "I"}
        };

        GameResult result = calculateScore(lostMatrix, STANDARD_BET);
        String expectedResultAsJson = """
                {
                  "matrix" : [ [ "A", "B", "C" ], [ "D", "E", "F" ], [ "G", "H", "I" ] ],
                  "reward" : 0.0
                }""";
        assertEquals(expectedResultAsJson, result.toString());
    }
}