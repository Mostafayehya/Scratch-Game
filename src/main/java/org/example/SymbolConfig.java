package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SymbolConfig {
    private double reward_multiplier;
    private String type;
    private String impact;

    public SymbolConfig() {
    }

    public SymbolConfig(double reward_multiplier, String type) {
        this.reward_multiplier = reward_multiplier;
        this.type = type;
    }

    public double getReward_multiplier() {
        return reward_multiplier;
    }

    public void setReward_multiplier(double reward_multiplier) {
        this.reward_multiplier = reward_multiplier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImpact() {
        return impact;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }
}
