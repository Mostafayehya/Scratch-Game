package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

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
}
