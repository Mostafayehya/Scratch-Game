package org.example;

    import org.apache.commons.cli.*;
    import org.example.Domain.GameResult;

    public class Main {
        public static void main(String[] args) {
            Options options = new Options();
            options.addOption("c", "config", true, "Config file path");
            options.addOption("b", "betting-amount", true, "Betting amount");

            try {
                CommandLine cmd = new DefaultParser().parse(options, args);
                String configPath = cmd.getOptionValue("config");
                double betAmount = Double.parseDouble(cmd.getOptionValue("betting-amount"));

                ScratchGame game = new ScratchGame(configPath);
                printGameResult(game.play(betAmount));

            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
                printUsage();
                System.exit(1);
            }
        }

        private static void printGameResult(GameResult result) {
            System.out.println(result.toString());
        }

        private static void printUsage() {
            System.out.println("Usage: java -jar slot-game.jar --config <config-path> --betting-amount <amount>");
            System.out.println("Example: java -jar target/ScratchGame-1.0-jar-with-dependencies.jar --config src/main/resources/config.json --betting-amount 200");
        }
    }