package day_2;

public enum GameSelection implements Comparable<GameSelection> {
    ROCK, PAPER, SCISSORS;

    int value() {
        if (this.equals(ROCK)) {
            return 1;
        } else if (this.equals(PAPER)) {
            return 2;
        } else {
            return 3;
        }
    }

    int computeMatch(GameSelection opponent, int winValue, int drawValue, int loseValue) {
        if (this.equals(opponent)) {
            return drawValue;
        }
        if (this.equals(ROCK)) {
            return opponent.equals(SCISSORS) ? winValue : loseValue;
        } else if (this.equals(PAPER)) {
            return opponent.equals(ROCK) ? winValue : loseValue;
        } else {
            return opponent.equals(PAPER) ? winValue : loseValue;
        }
    }

    GameSelection getCorrectOpponent(int expectedOutcome, int winValue, int drawValue) {
        if (expectedOutcome == drawValue) {
            return this;
        }
        if (this.equals(ROCK)) {
            return expectedOutcome == winValue ? PAPER: SCISSORS;
        } else if (this.equals(PAPER)) {
            return expectedOutcome == winValue ? SCISSORS: ROCK;
        } else {
            return expectedOutcome == winValue ? ROCK: PAPER;
        }
    }
}
