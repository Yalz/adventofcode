package day_4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ScratchCardDeck {
    private final Map<Integer, ScratchCard> scratchCards;

    public ScratchCardDeck(Stream<String> scratchCards) {
        this.scratchCards = scratchCards.map(ScratchCard::new)
                .map(scratchCard -> Map.of(scratchCard.getCardIndex(), scratchCard))
                .flatMap(m -> m.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Long getTotalScore() {
        return this.scratchCards.values()
                .stream()
                .map(ScratchCard::getScore)
                .reduce(0L, Long::sum);
    }

    public void getScratchCardTrophy() {
        Map<Integer, Integer> cardsWon = this.scratchCards
                .values()
                .stream()
                .map(scratchCard -> {
                    System.out.println("-- " + scratchCard.getCardIndex());
                    return winCards(scratchCard, new HashMap<>());
                })
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Integer::sum));


        System.out.println(cardsWon);
    }

    private Map<Integer, Integer> winCards(ScratchCard scratchCard, Map<Integer, Integer> cardsWon) {
        long winningNumbersCount = scratchCard.getWinningNumbersCount();
        int cardIndex = scratchCard.getCardIndex();
        if (winningNumbersCount == 0) {
            return cardsWon;
        }
        System.out.println("Processing card " + scratchCard + ". Will now process " + Arrays.toString(IntStream.range(cardIndex + 1, (int) (cardIndex + winningNumbersCount + 1)).toArray()));
        return IntStream.range(cardIndex + 1, (int) (cardIndex + winningNumbersCount + 1))
                .filter(scratchCards::containsKey)
                .peek(cardId -> cardsWon.merge(cardId, 1, Integer::sum))
                .mapToObj(scratchCards::get)
                .peek(System.out::println)
                .peek(scratchCard1 -> System.out.println(scratchCard1.getCardIndex() + " " + cardsWon))
                .map(sc -> winCards(sc, cardsWon))
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Integer::sum));
    }

    public int getCards(ScratchCard card) {
        int winningNumbersCount = card.getWinningNumbersCount().intValue();
        int cardIndex = card.getCardIndex();
        if (winningNumbersCount == 0) {
            return winningNumbersCount;
        }

        var x = IntStream.range(cardIndex + 1, cardIndex + winningNumbersCount + 1)
                .filter(scratchCards::containsKey)
                .mapToObj(scratchCards::get)
                .peek(value -> {
                    System.out.println("Card " + cardIndex + " contains card " + value.getCardIndex() + " worth " + value.getWinningNumbersCount() + " cards");
                })
                .map(this::getCards)
                .peek(integer -> System.out.println("(Before reduce) :" + integer))
                .reduce(winningNumbersCount, Integer::sum);

        return x;
    }

}
