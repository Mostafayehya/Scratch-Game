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
    MatrixGenerator matrixGenerator;
    List<Map<String, Integer>> standardSymbolsPerCell = TestFixtures.getStandardSymbolsPerCell();
    Map<String, Integer> bonusSymbols = TestFixtures.getBonusSymbols();
    String configPath = Paths.get("src", "main", "resources", "config.json").toString();
    GameConfig config = ConfigLoader.loadConfig(configPath);

    @BeforeEach
    void setUp() {
        matrixGenerator = new MatrixGenerator();
        symbols = TestFixtures.getSymbols();
    }

    @Test
    public void generate() {
        String[][] matrix = matrixGenerator.generate(3, 3, config.getProbabilities());
        assert matrix.length == 3;
        assert matrix[0].length == 3;
    }

    @Test
    public void generateWithSpecificSymbol() {
        String[][] matrix = matrixGenerator.generate(3, 3, config.getProbabilities());
        assert symbols.containsKey(matrix[0][0]);
    }

    @Test
    public void generateWithDifferentSymbols() {
        String[][] matrix = matrixGenerator.generate(3, 3, config.getProbabilities());
        assert symbols.containsKey(matrix[0][0]);
    }

    @Test
    public void testValidConfig() {


        assertNotNull(config, "Config Should not be null");
        assertEquals(3, config.getRows());

        assertEquals(3, config.getColumns());
        assertEquals(11, config.getSymbols().size());
        assertEquals(9, config.getProbabilities().getStandardSymbols().size());
        assertEquals(2, config.getProbabilities().getBonusSymbols().getSymbols().get("5x"));

        assertEquals(11, config.getWinCombinations().size());

    }

    @Test
    public void generateWithPossibilities() {
        // Given

        MatrixGenerator generator = new MatrixGenerator();
        int rows = 3;
        int cols = 3;

        // When
        String[][] matrix = generator.generate(rows, cols, config.getProbabilities());

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
