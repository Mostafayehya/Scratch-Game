package org.example.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SymbolConfig(
        @JsonProperty("reward_multiplier")
        double rewardMultiplier,
        String type,
        String impact,
        double extra

) {

}
