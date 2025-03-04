package org.example;

import java.util.Map;
import java.util.Random;

public class MatrixGenerator {
    public String[][] generateMatrix(int rows, int cols, Map<String, Integer> symbols) {
        String[][] matrix = new String[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = getRandomSymbol(row, col, symbols);
            }
        }
        return matrix;
    }

    private static String getRandomSymbol(int row, int col, Map<String, Integer> symbols) {
        return symbols.keySet().toArray(new String[0])[(int) (Math.random() * symbols.size())];
    }
}
