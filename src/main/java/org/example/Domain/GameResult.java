package org.example.Domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public record GameResult(
        String[][] matrix,
        double reward,
        Map<String, List<String>> appliedWinningCombinations,
        String appliedBonusSymbol
) {
    public GameResult {
        matrix = Arrays.stream(matrix).map(String[]::clone).toArray(String[][]::new);
        appliedWinningCombinations = Map.copyOf(appliedWinningCombinations);
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, Object> resultMap = new LinkedHashMap<>();
            resultMap.put("matrix", matrix);
            resultMap.put("reward", reward);
            if (reward != 0) {
                resultMap.put("appliedWinningCombinations", appliedWinningCombinations);
                resultMap.put("appliedBonusSymbol", appliedBonusSymbol);
            }
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert GameResult to JSON string", e);
        }
    }
}
