package org.example;

import org.example.Domain.GameConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MatrixGeneratorTest {

    Map<String, Integer> symbols;
    MatrixGenerator game;
    List<Map<String, Integer>> standardSymbolsPerCell = TestFixtures.getStandardSymbolsPerCell();
    Map<String, Integer> bonusSymbols = TestFixtures.getBonusSymbols();

    @BeforeEach
    void setUp() {
        game = new MatrixGenerator();
        symbols = TestFixtures.getSymbols();
    }

    @Test
    public void generateMatrix() {
        String[][] matrix = game.generateMatrix(3, 3, standardSymbolsPerCell, symbols);
        assert matrix.length == 3;
        assert matrix[0].length == 3;
    }

    @Test
    public void generateMatrixWithSpecificSymbol() {
        String[][] matrix = game.generateMatrix(3, 3, standardSymbolsPerCell, symbols);
        assert symbols.containsKey(matrix[0][0]);
    }

    @Test
    public void generateMatrixWithDifferentSymbols() {
        String[][] matrix = game.generateMatrix(3, 3, standardSymbolsPerCell, symbols);
        assert symbols.containsKey(matrix[0][0]);
    }

    @Test
    public void testValidConfig() {
        String configPath = Paths.get("src", "main", "resources", "config.json").toString();
        GameConfig config = ConfigLoader.loadConfig(configPath);

        assertNotNull(config, "Config Should not be null");
        assertEquals(3, config.getRows());

        assertEquals(3, config.getColumns());
        assertEquals(11, config.getSymbols().size());
        assertEquals(9, config.getProbabilities().getStandardSymbols().size());
        assertEquals(2, config.getProbabilities().getBonusSymbols().getSymbols().get("5x"));

        assertEquals(11, config.getWinCombinations().size());

    }

    @Test
    public void generateMatrixWithPossibilities() {
        // Given

        MatrixGenerator generator = new MatrixGenerator();
        int rows = 3;
        int cols = 3;

        // When
        String[][] matrix = generator.generateMatrix(rows, cols, standardSymbolsPerCell, bonusSymbols);

        // Print the generated matrix

        // assert the matrix contains any of the bounus symbols

        System.out.println("Generated Matrix:");
        for (String[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }

        // Then: Assert the matrix contains at least one bonus symbol
        boolean containsBonusSymbol = false;
        for (String[] row : matrix) {
            for (String cell : row) {
                if (bonusSymbols.containsKey(cell)) {
                    containsBonusSymbol = true;
                    break;
                }
            }
            if (containsBonusSymbol) break;
        }

        assert containsBonusSymbol : "The generated matrix does not contain any bonus symbols.";


    }
}
