package org.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestFixtures {

    public static Map<String, Integer> getSymbols() {
        return new HashMap<>(Map.ofEntries(
                Map.entry("A", 1),
                Map.entry("B", 2),
                Map.entry("C", 3),
                Map.entry("D", 4),
                Map.entry("E", 5),
                Map.entry("F", 6),
                Map.entry("10x", 10),
                Map.entry("5x", 5),
                Map.entry("+1000", 1000),
                Map.entry("+500", 500),
                Map.entry("MISS", 0)
        ));
    }

    public static List<Map<String, Integer>> getStandardSymbolsPerCell() {
        return Arrays.asList(
                Map.of("A", 10, "B", 20, "C", 30),
                Map.of("A", 5, "B", 25, "C", 10),
                Map.of("A", 15, "B", 5, "C", 10),
                Map.of("A", 10, "B", 20, "C", 5),
                Map.of("A", 10, "B", 10, "C", 10),
                Map.of("A", 20, "B", 10, "C", 5),
                Map.of("A", 5, "B", 10, "C", 25),
                Map.of("A", 10, "B", 15, "C", 15),
                Map.of("A", 15, "B", 20, "C", 5)
        );
    }

    // Global bonus probabilities
    public static Map<String, Integer> getBonusSymbols() {
        return Map.of(
                "10x", 2,
                "5x", 3,
                "+1000", 1,
                "+500", 1,
                "MISS", 4
        );
    }

}
