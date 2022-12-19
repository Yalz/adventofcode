package day_6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SignalInterpreterDemo {
    public static void main(String[] args) throws IOException {
        int index = Files.lines(Paths.get("advent-of-code-2022/src/main/resources/day_6/input.txt"))
                .findFirst()
                .map((String line) -> SignalInterpreter.interpret(line, 4))
                .orElse(0);
        System.out.println("Index for marker lenght 4 is at " + index);

        index = Files.lines(Paths.get("advent-of-code-2022/src/main/resources/day_6/input.txt"))
                .findFirst()
                .map((String line) -> SignalInterpreter.interpret(line, 14))
                .orElse(0);
        System.out.println("Index for marker lenght 14 is at " + index);
    }
}
