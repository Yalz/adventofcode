package day_2;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class CubeGameTest {

    @Test
    public void areGamesValid() {
        CubeGame cb = new CubeGame();

        assertTrue(cb.isGameValid("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"));
        assertTrue(cb.isGameValid("Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue"));
        assertFalse(cb.isGameValid("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red"));
        assertFalse(cb.isGameValid("Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red"));
        assertTrue(cb.isGameValid("Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"));
    }

    @Test
    public void sumOfValidGames() throws IOException {
        CubeGame cb = new CubeGame();

        int gameIdCount = getFileInput("src/test/resources/day_2/ex1.txt")
                .map(cb::parseGamev1)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .reduce(Integer::sum)
                .get();

        assertEquals(8, gameIdCount);
    }

    @Test
    public void sumOfMinCubes() throws IOException {
        CubeGame cb = new CubeGame();

        int gameIdCount = getFileInput("src/test/resources/day_2/ex1.txt")
                .map(cb::parseGamev2)
                .reduce(Integer::sum)
                .get();

        assertEquals(2286, gameIdCount);

    }

    @Test
    public void testInputv1() throws IOException {
        CubeGame cb = new CubeGame();

        var gameIdSum = getFileInput("src/test/resources/day_2/input.txt")
                .map(cb::parseGamev1)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .reduce(0, Integer::sum);

        System.out.println("Game Id Sum: "+ gameIdSum);
    }

    @Test
    public void testInputv2() throws IOException {
        CubeGame cb = new CubeGame();

        var gameIdSum = getFileInput("src/test/resources/day_2/input.txt")
                .map(cb::parseGamev2)
                .reduce(0, Integer::sum);

        System.out.println("Game Id Sum: "+ gameIdSum);
    }

    private Stream<String> getFileInput(String path) throws IOException {
        return Files.readAllLines(Paths.get(path)).stream();
    }
}
