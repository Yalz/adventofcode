package day_2;

import org.junit.Test;

import java.util.Map;

import static day_2.GameSelection.*;
import static day_2.GameSelection.SCISSORS;
import static org.junit.Assert.assertEquals;

public class GamesCalculatorTest {
    @Test
    public void calculateGame_Cypher1() {
        Map<String, GameSelection> inputSelectionMapping = Map.of("A", ROCK, "B", PAPER, "C", SCISSORS);

        GamesCalculator gamesCalculator = new GamesCalculator(inputSelectionMapping,1, 6, 3 , 0);

        assertEquals(8, gamesCalculator.calculateGameCypher("A Y"));
        assertEquals(1, gamesCalculator.calculateGameCypher("B X"));
        assertEquals(6, gamesCalculator.calculateGameCypher("C Z"));
    }

    @Test
    public void calculateGame_Cypher2() {
        Map<String, GameSelection> inputSelectionMapping = Map.of("A", ROCK, "B", PAPER, "C", SCISSORS);

        GamesCalculator gamesCalculator = new GamesCalculator(inputSelectionMapping,2, 6, 3 , 0);

        assertEquals(4, gamesCalculator.calculateGameCypher("A Y"));
        assertEquals(1, gamesCalculator.calculateGameCypher("B X"));
        assertEquals(7, gamesCalculator.calculateGameCypher("C Z"));
    }
}
