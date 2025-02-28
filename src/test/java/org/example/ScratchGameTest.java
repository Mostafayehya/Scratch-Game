package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ScratchGameTest {

    Map<String, Integer> symbols = Map.of(
            "A", 1,
            "B", 2,
            "C", 3,
            "D", 4,
            "E", 5,
            "F", 6,
            "10x", 10,
            "5x", 5,
            "+1000", 1000,
            "+500", 500
    );
    ScratchGame game;

    @BeforeEach
    void setUp() {
        game = new ScratchGame();
        //symbols.put("MISS", 0);
    }

    @Test
    public void generateMatrix() {
        String[][] matrix = game.generateMatrix(3, 3, symbols);
        assert matrix.length == 3;
        assert matrix[0].length == 3;
    }

    @Test
    public void generateMatrixWithSpecificSymbol() {
        String[][] matrix = game.generateMatrix(3, 3, symbols);
        assert symbols.containsKey(matrix[0][0]);
    }

    @Test
    public void generateMatrixWithDifferentSymbols() {
        String[][] matrix = game.generateMatrix(3, 3, symbols);
        assert symbols.containsKey(matrix[0][0]);
    }

    @Test
    public void testValidConfig(){
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
}
