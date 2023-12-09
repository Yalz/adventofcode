package day_3;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class EgineDecipherTest {
    @Test
    public void testPartCount() throws IOException {
        Engine engine = new Engine(getFileInput("src/test/resources/day_3/ex1.txt").toList());

        int sum = EngineDecipher.getPartCount(engine);
        assertEquals(4361, sum);
    }

    @Test
    public void testInputPartCount() throws IOException {
        Engine engine = new Engine(getFileInput("src/test/resources/day_3/input.txt").toList());

        int sum = EngineDecipher.getPartCount(engine);

        assertEquals(528799, sum);
    }

    @Test
    public void testGearProduct() throws IOException {
        Engine engine = new Engine(getFileInput("src/test/resources/day_3/ex1.txt").toList());

        int product = EngineDecipher.getGearProduct(engine);

        assertEquals(467835, product);
    }

    @Test
    public void testInputGearProduct() throws IOException {
        Engine engine = new Engine(getFileInput("src/test/resources/day_3/input.txt").toList());

        int product = EngineDecipher.getGearProduct(engine);

        assertEquals(84907174, product);
    }

    private Stream<String> getFileInput(String path) throws IOException {
        return Files.readAllLines(Paths.get(path)).stream();
    }
}
