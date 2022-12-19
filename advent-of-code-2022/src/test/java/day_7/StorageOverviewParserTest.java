package day_7;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StorageOverviewParserTest {
    @Test
    public void storageOverview_directorySize() {
        StorageOverviewParser storageOverviewParser = new StorageOverviewParser(70_000_000);

        storageOverviewParser.addSizeToFolder("/", 10);

        assertEquals(10, storageOverviewParser.getFolderSize().get("/").get());

        storageOverviewParser.addSizeToFolder("/", 10);

        assertEquals(20, storageOverviewParser.getFolderSize().get("/").get());
    }

    @Test
    public void storageOverview_interpretTerminalOutput_changeDirectory() {
        StorageOverviewParser storageOverviewParser = new StorageOverviewParser(70_000_000);

        storageOverviewParser.interpretTerminalOutput(List.of("$ cd a"));
        assertEquals("/a", storageOverviewParser.getCurrentDirectory());
    }

    @Test
    public void storageOverview_interpretTerminalOutput_listDirectory() {
        StorageOverviewParser storageOverviewParser = new StorageOverviewParser(70_000_000);

        storageOverviewParser.interpretTerminalOutput(List.of("$ cd /", "14848514 b.txt"));
        assertEquals(14848514, storageOverviewParser.getFolderSize().get("/").get());
    }

    @Test
    public void test_example() throws IOException {
        StorageOverviewParser storageOverviewParser = new StorageOverviewParser(70_000_000);

        storageOverviewParser.interpretTerminalOutput(Files.lines(Paths.get("src/test/resources/day_7/example.txt"))
                .toList());

        assertEquals(94853, storageOverviewParser.getFolderSize().get("/a").get());
        assertEquals(584, storageOverviewParser.getFolderSize().get("/a/e").get());
        assertEquals(24933642, storageOverviewParser.getFolderSize().get("/d").get());
        assertEquals(48381165, storageOverviewParser.getFolderSize().get("/").get());

        int SumOfFoldersAbove100_000 = storageOverviewParser.getSumOfFoldersBelowSize(100_000);
        assertEquals(95437, SumOfFoldersAbove100_000);

        int sizeOfMinimalFolderToDelete = storageOverviewParser.getSizeOfMinimalFolderForUpdateOfSize(30_000_000);
        assertEquals(24933642, sizeOfMinimalFolderToDelete);
    }
}
