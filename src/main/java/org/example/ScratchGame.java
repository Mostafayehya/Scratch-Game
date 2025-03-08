package org.example;

import org.example.Domain.GameConfig;
import org.example.Domain.GameResult;

public class ScratchGame {

    private final GameConfig config;
    private final MatrixGenerator matrixGenerator;

    public ScratchGame(String configPath) {
        this.matrixGenerator = new MatrixGenerator();
        this.config = ConfigLoader.loadConfig(configPath);
    }

    public GameResult play(double betAmount) {
        String[][] matrix = matrixGenerator.generate(config.rows(), config.columns(), config.probabilities());
        ScoreCalculator calculator = new ScoreCalculator(
                config.winCombinations(),
                config.symbols(),
                betAmount
        );

        return calculator.calculateScore(matrix);
    }
}
