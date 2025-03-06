package org.example;

import org.example.Domain.GameConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixGeneratorTest {
    private static final int EXPECTED_ROWS = 3;
    private static final int EXPECTED_COLS = 3;
    private static final int EXPECTED_SYMBOLS_COUNT = 11;
    private static final int EXPECTED_STANDARD_SYMBOLS_COUNT = 9;
    private static final int EXPECTED_BONUS_5X_VALUE = 2;
    private static final String CONFIG_PATH = Paths.get("src", "main", "resources", "config.json").toString();

    private MatrixGenerator matrixGenerator;
    private GameConfig config;
    private Map<String, Integer> standardSymbols;
    private Map<String, Integer> bonusSymbols;

    @BeforeEach
    void setUp() {
        matrixGenerator = new MatrixGenerator();
        config = ConfigLoader.loadConfig(CONFIG_PATH);
        standardSymbols = TestFixtures.getSymbols();
        bonusSymbols = TestFixtures.getBonusSymbols();
    }

    @Test
    void shouldGenerateMatrixWithCorrectDimensions() {
        String[][] matrix = generateMatrix();

        assertEquals(EXPECTED_ROWS, matrix.length, "Matrix should have correct number of rows");
        assertEquals(EXPECTED_COLS, matrix[0].length, "Matrix should have correct number of columns");
    }

    @Test
    void shouldGenerateMatrixWithValidSymbols() {
        String[][] matrix = generateMatrix();
        String firstSymbol = matrix[0][0];

        assertTrue(standardSymbols.containsKey(firstSymbol), "Matrix should contain valid symbols");
    }

    @Test
    void shouldGenerateMatrixWithAtLeastOneBonusSymbol() {
        String[][] matrix = generateMatrix();

        assertTrue(containsBonusSymbol(matrix), "Matrix should contain at least one bonus symbol");
    }

    @Test
    void shouldLoadValidConfiguration() {
        assertNotNull(config, "Config should not be null");
        assertEquals(EXPECTED_ROWS, config.rows(), "Config should have correct number of rows");
        assertEquals(EXPECTED_COLS, config.columns(), "Config should have correct number of columns");
        assertEquals(EXPECTED_SYMBOLS_COUNT, config.symbols().size(), "Config should have correct number of symbols");
        assertEquals(EXPECTED_STANDARD_SYMBOLS_COUNT, config.probabilities().standardSymbols().size(),
                "Config should have correct number of standard symbols");
        assertEquals(EXPECTED_BONUS_5X_VALUE, config.probabilities().bonusSymbols().symbols().get("5x"),
                "Config should have correct 5x bonus value");
        assertEquals(EXPECTED_SYMBOLS_COUNT, config.winCombinations().size(),
                "Config should have correct number of win combinations");
    }

    private String[][] generateMatrix() {
        return matrixGenerator.generate(EXPECTED_ROWS, EXPECTED_COLS, config.probabilities());
    }

    private boolean containsBonusSymbol(String[][] matrix) {
        for (String[] row : matrix) {
            for (String cell : row) {
                if (bonusSymbols.containsKey(cell)) {
                    return true;
                }
            }
        }
        return false;
    }
}
