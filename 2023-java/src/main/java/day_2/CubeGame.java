package day_2;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
                        .map(matcher -> Map.of(matcher.group(2), Integer.parseInt(matcher.group(1))))
                        .flatMap(m -> m.entrySet().stream())
                        .collect(Collectors.toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                Integer::sum)))
                .allMatch(this::isValidGame);

        if (roundsValid) return Optional.of(gameNumber);
        else return Optional.empty();
    }

    private boolean isValidGame(Map<String, Integer> x) {
        return limits.entrySet()
                .stream()
                .allMatch(limit -> {
                    if (x.containsKey(limit.getKey())){
                        if (x.get(limit.getKey()) > limit.getValue()) {
//                            System.err.println("!! " + limit.getKey() + " is over " + limit.getValue());
                            return false;
                        } else {
                            return true;
                        }
                    }
                    else {
                        return true;
                    }

                });
    }

    public static Matcher getRegexGroups(String regex, String text) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher;
        }
        return null;
    }
}
