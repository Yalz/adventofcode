package day_4;

import org.junit.Test;

import java.io.IOException;

import static common.FileReader.getFileInput;
import static org.junit.Assert.assertEquals;

public class ScratchCardScenarioTest {

    ScratchCard sc1 = new ScratchCard("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53");
    ScratchCard sc2 = new ScratchCard("Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19");
    ScratchCard sc3 = new ScratchCard("Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1");
    ScratchCard sc4 = new ScratchCard("Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83");
    ScratchCard sc5 = new ScratchCard("Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36");
    ScratchCard sc6 = new ScratchCard("Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11");

    @Test
    public void testWinningNumbers() {
        assertEquals(4, sc1.getWinningNumbersCount().intValue());
        assertEquals(2, sc2.getWinningNumbersCount().intValue());
        assertEquals(2, sc3.getWinningNumbersCount().intValue());
        assertEquals(1, sc4.getWinningNumbersCount().intValue());
        assertEquals(0, sc5.getWinningNumbersCount().intValue());
        assertEquals(0, sc6.getWinningNumbersCount().intValue());
    }

    @Test
    public void testScore() {
        assertEquals(8, sc1.getScore());
        assertEquals(2, sc2.getScore());
        assertEquals(2, sc3.getScore());
        assertEquals(1, sc4.getScore());
        assertEquals(0, sc5.getScore());
        assertEquals(0, sc6.getScore());
    }

    @Test
    public void testScoreExampleV1() throws IOException {
        var score = new ScratchCardDeck(getFileInput("src/test/resources/day_4/ex1.txt")).getTotalScore();
        assertEquals(13, score.intValue());
    }

    @Test
    public void testScoreInputV1() throws IOException {
        var score = new ScratchCardDeck(getFileInput("src/test/resources/day_4/input.txt")).getTotalScore();
        assertEquals(26346, score.intValue());
    }

    @Test
    public void testScratchCardTrophyExample() throws IOException {
        new ScratchCardDeck(getFileInput("src/test/resources/day_4/ex1.txt")).getScratchCardTrophy();
    }

    @Test
    public void fiddling() throws IOException {
        var scratchCardDeck = new ScratchCardDeck(getFileInput("src/test/resources/day_4/ex1.txt"));

        assertEquals(30, scratchCardDeck.getCards(sc1));
        assertEquals(6, scratchCardDeck.getCards(sc2));
        assertEquals(3, scratchCardDeck.getCards(sc3));
        assertEquals(1, scratchCardDeck.getCards(sc4));
        assertEquals(0, scratchCardDeck.getCards(sc5));
        assertEquals(0, scratchCardDeck.getCards(sc6));


    }
}
