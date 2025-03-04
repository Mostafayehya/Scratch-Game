package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Domain.GameConfig;

import java.io.File;
import java.io.IOException;

public class ConfigLoader {
    public static GameConfig loadConfig(String configPath) {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(configPath);
        try {
           return objectMapper.readValue(file, GameConfig.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
