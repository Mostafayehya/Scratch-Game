# Slot Game Engine

A Java-based slot game engine that generates random game matrices and calculates winnings based on symbol combinations and bonus multipliers.

## Requirements

- JDK \>= 1.8
- Maven

## Description

The engine generates a matrix (typically 3x3) from symbols based on defined probabilities. Based on winning combinations and bonus symbols, the engine calculates the final reward. Users place a bet (referred to as the *betting amount*) and the game applies multipliers or extra amounts according to matching winning combinations.

There are two types of symbols:

### Standard Symbols

Standard symbols multiply the betting amount using predefined reward multipliers.

| Symbol Name | Reward Multiplier |
|-------------|-------------------|
| A           | 50                |
| B           | 25                |
| C           | 10                |
| D           | 5                 |
| E           | 3                 |
| F           | 1.5               |

### Bonus Symbols

Bonus symbols apply only when a winning combination occurs and affect the final reward with either multipliers or extra amounts.

| Symbol Name | Action                       |
|-------------|------------------------------|
| 10x         | Multiply final reward by 10  |
| 5x          | Multiply final reward by 5   |
| +1000       | Add 1000 to the final reward |
| +500        | Add 500 to the final reward  |
| MISS        | No effect                    |

## Configuration File

The configuration file (`config.json`) defines game settings such as matrix dimensions, symbols, probabilities, and winning combinations.

Example configuration:

```json
{
  "columns": 3,
  "rows": 3,
  "symbols": {
    "A": { "reward_multiplier": 5, "type": "standard" },
    "B": { "reward_multiplier": 3, "type": "standard" },
    "C": { "reward_multiplier": 2.5, "type": "standard" },
    "D": { "reward_multiplier": 2, "type": "standard" },
    "E": { "reward_multiplier": 1.2, "type": "standard" },
    "F": { "reward_multiplier": 1, "type": "standard" },
    "10x": { "reward_multiplier": 10, "type": "bonus", "impact": "multiply_reward" },
    "5x": { "reward_multiplier": 5, "type": "bonus", "impact": "multiply_reward" },
    "+1000": { "extra": 1000, "type": "bonus", "impact": "extra_bonus" },
    "+500": { "extra": 500, "type": "bonus", "impact": "extra_bonus" },
    "MISS": { "type": "bonus", "impact": "miss" }
  },
  "probabilities": {
    "standard_symbols": [
      {
        "column": 0,
        "row": 0,
        "symbols": { "A": 1, "B": 2, "C": 3, "D": 4, "E": 5, "F": 6 }
      },
      {
        "column": 0,
        "row": 1,
        "symbols": { "A": 1, "B": 2, "C": 3, "D": 4, "E": 5, "F": 6 }
      }
    ],
    "bonus_symbols": {
      "symbols": { "10x": 1, "5x": 2, "+1000": 3, "+500": 4, "MISS": 5 }
    }
  },
  "win_combinations": {
    "same_symbol_3_times": { "reward_multiplier": 1, "when": "same_symbols", "count": 3, "group": "same_symbols" },
    "same_symbol_4_times": { "reward_multiplier": 1.5, "when": "same_symbols", "count": 4, "group": "same_symbols" },
    "same_symbol_5_times": { "reward_multiplier": 2, "when": "same_symbols", "count": 5, "group": "same_symbols" },
    "same_symbol_6_times": { "reward_multiplier": 3, "when": "same_symbols", "count": 6, "group": "same_symbols" },
    "same_symbol_7_times": { "reward_multiplier": 5, "when": "same_symbols", "count": 7, "group": "same_symbols" },
    "same_symbol_8_times": { "reward_multiplier": 10, "when": "same_symbols", "count": 8, "group": "same_symbols" },
    "same_symbol_9_times": { "reward_multiplier": 20, "when": "same_symbols", "count": 9, "group": "same_symbols" }
  }
}
```

## Input Format

```json
{
  "bet_amount": 100
}
```

## Output Format

```json
{
  "matrix": [
    ["A", "A", "B"],
    ["A", "+1000", "B"],
    ["A", "A", "B"]
  ],
  "reward": 6600,
  "applied_winning_combinations": {
    "A": ["same_symbol_5_times", "same_symbols_vertically"],
    "B": ["same_symbol_3_times", "same_symbols_vertically"]
  },
  "applied_bonus_symbol": "+1000"
}
```

## Calculations

The reward is calculated using the following logic:
- Multiply the `bet_amount` by the symbol's reward multiplier.
- Apply winning combination multipliers.
- If a bonus symbol is applied, add or multiply the bonus value accordingly.

Example formula:
```
final_reward = (bet_amount x symbol_multiplier x winning_combination_multiplier) [+ bonus]
```

## Running the Game

To run the game from the command line:
```
java -jar target/ScratchGame-1.0-jar-with-dependencies.jar --config config.json --betting-amount 100
```

## Building the Project

Build the project using Maven:
```
mvn clean package
```

