package day_2;

import java.util.*;
import java.util.stream.Collectors;

import static common.RegEx.getRegexGroups;

public class CubeGame {

    Map<String, Integer> limits = Map.of("red", 12, "green", 13, "blue", 14);

    public boolean isGameValid(String gameLine) {
        return parseGamev1(gameLine).isPresent();
    }

    public Optional<Integer> parseGamev1(String gameLine) {
        int gameNumber = Integer.parseInt(getRegexGroups("Game (\\d+):", gameLine).group(1));
        String game = gameLine.split(":")[1];

        var roundsValid = Arrays.stream(game.split(";"))
                .map(take -> Arrays.stream(take.split(","))
                        .map(String::trim)
                        .map(s -> getRegexGroups("(\\d+) (\\w+)", s))
                        .filter(Objects::nonNull)
                        .map(matcher -> Map.of(matcher.group(2), Integer.parseInt(matcher.group(1))))
                        .flatMap(m -> m.entrySet().stream())
                        .collect(Collectors.toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                Integer::sum)))
                .allMatch(this::isValidGame);

        if (roundsValid) return Optional.of(gameNumber);
        else return Optional.empty();
    }

    public Integer parseGamev2(String gameLine) {
        String game = gameLine.split(":")[1];

        return Arrays.stream(game.split(";"))
                .map(take -> Arrays.stream(take.split(","))
                        .map(String::trim)
                        .map(s -> getRegexGroups("(\\d+) (\\w+)", s))
                        .filter(Objects::nonNull)
                        .map(matcher -> Map.of(matcher.group(2), Integer.parseInt(matcher.group(1))))
                        .flatMap(m -> m.entrySet().stream())
                        .collect(Collectors.toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                Integer::sum)))
                .map(Map::entrySet)
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        Math::max))
                .values()
                .stream()
                .reduce(1, (i1, i2) -> i1 * i2);
    }

    private boolean isValidGame(Map<String, Integer> x) {
        return limits.entrySet()
                .stream()
                .allMatch(limit -> !x.containsKey(limit.getKey()) || x.get(limit.getKey()) <= limit.getValue());
    }
}
