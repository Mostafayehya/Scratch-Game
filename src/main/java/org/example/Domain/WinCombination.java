package org.example.Domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public record WinCombination(
        @JsonProperty("reward_multiplier")
        int rewardMultiplier,
        String when,
        int count,
        String group,
        @JsonProperty("covered_areas")
        List<List<String>> coveredAreas
) {
    public WinCombination {
        coveredAreas = coveredAreas != null ? List.copyOf(coveredAreas) : List.of();
    }
}
