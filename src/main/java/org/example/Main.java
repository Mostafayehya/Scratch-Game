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
            System.out.println("\nGame Matrix:");
            for (String[] row : result.matrix()) {
                System.out.println(String.join(" ", row));
            }

            System.out.printf("\nReward: %.2f%n", result.reward());

            if (!result.appliedWinningCombinations().isEmpty()) {
                System.out.println("\nWinning Combinations:");
                result.appliedWinningCombinations().forEach((symbol, combinations) ->
                    System.out.printf("Symbol %s: %s%n", symbol, String.join(", ", combinations)));
            }

            if (result.appliedBonusSymbol() != null) {
                System.out.printf("\nBonus Symbol Applied: %s%n", result.appliedBonusSymbol());
            }
        }

        private static void printUsage() {
            System.out.println("Usage: java -jar slot-game.jar --config <config-path> --betting-amount <amount>");
            System.out.println("Example: java -jar slot-game.jar --config config.json --betting-amount 100");
        }
    }