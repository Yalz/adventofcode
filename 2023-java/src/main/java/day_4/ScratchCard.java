package day_4;

import common.RegEx;

import java.util.Arrays;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class ScratchCard {
    private final String CARD_INDEX = "Card\\s*(\\d+)";
    private final Integer cardIndex;
    private final List<String> winningNumbers;
    private final List<String> ownNumbers;

    public ScratchCard(String card) {
        this.cardIndex = Integer.parseInt(requireNonNull(RegEx.getRegexGroups(CARD_INDEX, card)).group(1));
        List<String> numbers = Arrays.stream(card.split(":")[1].split("\\|")).toList();
        this.winningNumbers = Arrays.stream(numbers.get(0).trim().split(" ")).filter(s -> !s.isEmpty()).toList();
        this.ownNumbers = Arrays.stream(numbers.get(1).trim().split(" ")).filter(s -> !s.isEmpty()).toList();
    }

    public Long getWinningNumbersCount() {
        return winningNumbers.stream().filter(ownNumbers::contains).count();
    }

    public long getScore() {
        long winningCount = getWinningNumbersCount();

        if (winningCount == 0) {
            return 0;
        } else {
            return Math.round(Math.pow(2, getWinningNumbersCount() - 1));
        }
    }

    public Integer getCardIndex() {
        return cardIndex;
    }

    @Override
    public String toString() {
        return "ScratchCard{" +
                "cardIndex=" + cardIndex +
                ", winningNumbers=" + winningNumbers +
                ", ownNumbers=" + ownNumbers +
                ", wonNumber=" + getWinningNumbersCount() +
                '}';
    }
}
