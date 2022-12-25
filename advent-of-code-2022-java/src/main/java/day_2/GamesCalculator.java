package day_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

import static day_2.GameSelection.*;

public class GamesCalculator {

    Map<String, GameSelection> inputSelectionMapping;
    int winValue, equalValue, loseValue;
    int cypher;

    public GamesCalculator(Map<String, GameSelection> inputSelectionMapping, int cypher,
                           int winValue, int equalValue, int loseValue) {
        this.inputSelectionMapping = inputSelectionMapping;
        this.winValue = winValue;
        this.equalValue = equalValue;
        this.loseValue = loseValue;
        this.cypher = cypher;
    }

    public int calculateGameScore(String path) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(path);

        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);

        int score = 0;
        try {
            for (String line; (line = reader.readLine()) != null; ) {
                score += calculateGameCypher(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return score;
    }

    protected int calculateGameCypher(String game) {
        int gameScore = 0;

        GameSelection inputSelection = inputSelectionMapping.getOrDefault(game.substring(0, 1), null);

        if (this.cypher == 1) {
            Map<String, GameSelection> responseSelectionMapping = Map.of("X", ROCK, "Y", PAPER, "Z", SCISSORS);

            GameSelection responseSelection = responseSelectionMapping.get(game.substring(2, 3));

            Objects.requireNonNull(inputSelection);
            Objects.requireNonNull(responseSelection);

            gameScore += responseSelection.value();
            gameScore += responseSelection.computeMatch(inputSelection, winValue, equalValue, loseValue);

            return gameScore;
        }
        else if (this.cypher == 2) {
            Map<String, Integer> expectedScoreMap = Map.of("X", 0, "Y", 3, "Z", 6);

            Integer expectedScore = expectedScoreMap.get(game.substring(2, 3));

            Objects.requireNonNull(inputSelection);
            Objects.requireNonNull(expectedScore);

            GameSelection responseSelection = inputSelection.getCorrectOpponent(expectedScore, winValue, equalValue);

            gameScore += responseSelection.value();
            gameScore += expectedScore;

            return gameScore;
        }

        throw new IllegalArgumentException("Not a valid cypher");
    }
}
