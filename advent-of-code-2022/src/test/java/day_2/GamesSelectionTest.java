package day_2;

import org.junit.Test;

import static day_2.GameSelection.*;
import static org.junit.Assert.assertEquals;

public class GamesSelectionTest {
    @Test
    public void testComputeMatch() {
        int winValue = 6;
        int matchValue = 3;
        int loseValue = 0;

        assertEquals(6, PAPER.computeMatch(ROCK, winValue, matchValue, loseValue));
        assertEquals(3, PAPER.computeMatch(PAPER, winValue, matchValue, loseValue));
        assertEquals(0, PAPER.computeMatch(SCISSORS, winValue, matchValue, loseValue));

        assertEquals(6, ROCK.computeMatch(SCISSORS, winValue, matchValue, loseValue));
        assertEquals(3, ROCK.computeMatch(ROCK, winValue, matchValue, loseValue));
        assertEquals(0, ROCK.computeMatch(PAPER, winValue, matchValue, loseValue));

        assertEquals(6, SCISSORS.computeMatch(PAPER, winValue, matchValue, loseValue));
        assertEquals(3, SCISSORS.computeMatch(SCISSORS, winValue, matchValue, loseValue));
        assertEquals(0, SCISSORS.computeMatch(ROCK, winValue, matchValue, loseValue));
    }
}
