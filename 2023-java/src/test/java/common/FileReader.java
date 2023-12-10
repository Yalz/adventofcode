package common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReader {
    public static Stream<String> getFileInput(String path) throws IOException {
        return Files.readAllLines(Paths.get(path)).stream();
    }
}
