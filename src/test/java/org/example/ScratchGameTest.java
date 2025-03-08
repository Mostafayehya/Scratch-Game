package org.example;

import org.example.Domain.GameResult;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class ScratchGameTest {
    private static final String CONFIG_PATH = Paths.get("src", "main", "resources", "config.json").toString();

    @Test
    void testPlay() {
        ScratchGame game = new ScratchGame(CONFIG_PATH);
        GameResult gameResult = game.play(100);
        assertNotNull(gameResult, "Game result should not be null");
        assertNotNull(gameResult.matrix(), "Game result matrix should not be null");
        assertNotNull(gameResult.appliedWinningCombinations(), "Game result applied winning combinations should not be null");
        assertNotNull(gameResult.appliedBonusSymbol(), "Game result bonus symbol should not be null");
    }
}