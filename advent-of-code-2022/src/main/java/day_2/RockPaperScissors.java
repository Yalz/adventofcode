package day_2;

import java.util.Map;

import static day_2.GameSelection.*;

public class RockPaperScissors {
    public static void main(String[] args) {
        Map<String, GameSelection> inputSelectionMapping = Map.of("A", ROCK, "B", PAPER, "C", SCISSORS);

        GamesCalculator gamesCalculator = new GamesCalculator(inputSelectionMapping, 1, 6, 3 , 0);
        int score = gamesCalculator.calculateGameScore("./day_2/input.txt");

        System.out.println("Total score of the game with cypher 1  is " + score);

        gamesCalculator = new GamesCalculator(inputSelectionMapping, 2, 6, 3 , 0);
        score = gamesCalculator.calculateGameScore("./day_2/input.txt");

        System.out.println("Total score of the game with cypher 2  is " + score);
    }
}
