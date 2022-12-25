package day_7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StorageOverviewParserDemo {
    public static void main(String[] args) throws IOException {
        StorageOverviewParser storageOverviewParser = new StorageOverviewParser(70_000_000);

        storageOverviewParser.interpretTerminalOutput(Files.lines(Paths.get("advent-of-code-2022/src/main/resources/day_7/input.txt")).toList());

        int SumOfFoldersBelow100_000 = storageOverviewParser.getSumOfFoldersBelowSize(100_000);
        System.out.println("SumOfFoldersBelow100_000: " + SumOfFoldersBelow100_000);

        int getSizeOfMinimalFolderForUpdateOfSize = storageOverviewParser.getSizeOfMinimalFolderForUpdateOfSize(30_000_000);
        System.out.println("getSizeOfMinimalFolderForUpdateOf30Mil: " + getSizeOfMinimalFolderForUpdateOfSize);
    }
}
