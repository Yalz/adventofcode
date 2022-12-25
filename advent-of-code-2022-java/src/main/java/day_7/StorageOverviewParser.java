package day_7;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StorageOverviewParser {
    private final static Pattern CD_PATTERN = Pattern.compile("\\$\\scd\\s([\\w\\/]+)");
    private final static Pattern CD_PATTERN_UP = Pattern.compile("\\$\\scd\\s\\.\\.");
    private final static Pattern FILE_PATTERN = Pattern.compile("(\\d+)\\s(\\w+.?\\w*)");

    private String currentDirectory;
    private final int maxSize;
    private final Map<String, AtomicInteger> folderSize;

    public StorageOverviewParser(int maxSize) {
        this.maxSize = maxSize;
        this.folderSize = new HashMap<>();
        this.currentDirectory = "/";
    }

    public void interpretTerminalOutput(List<String> lines) {
        Matcher matcher;
        for (String line : lines) {
            matcher = CD_PATTERN.matcher(line);
            if (matcher.matches()) {
                this.changeDirectory(matcher.group(1));
            }
            matcher = CD_PATTERN_UP.matcher(line);
            if (matcher.matches()) {
                leaveDirectory();
            }
            matcher = FILE_PATTERN.matcher(line);
            if (matcher.matches()) {
                this.addSizeToFolder(this.currentDirectory, Integer.parseInt(matcher.group(1)));
            }
        }
        this.consolidateFolders();
    }

    public Map<String, AtomicInteger> getFolderSize() {
        return folderSize;
    }

    public String getCurrentDirectory() {
        return currentDirectory;
    }

    public int getSumOfFoldersBelowSize(int folderSize) {
        return this.folderSize.values()
                .stream()
                .map(AtomicInteger::get)
                .filter(i -> i <= folderSize)
                .reduce(0, Integer::sum);
    }

    public int getSizeOfMinimalFolderForUpdateOfSize(int size) {
        int unusedRoom = this.maxSize - this.folderSize.get("/").get();
        int neededFreeSpace = size - unusedRoom;

        return this.folderSize.values()
                .stream()
                .map(AtomicInteger::get)
                .filter(i -> i >= neededFreeSpace)
                .min(Integer::compareTo)
                .orElse(0);
    }

    protected void changeDirectory(String dir) {
        if (dir.equals("/")) {
            currentDirectory = dir;
        } else if (currentDirectory.equals("/")) {
            currentDirectory = currentDirectory + dir;
        } else {
            currentDirectory = currentDirectory + "/" + dir;
        }
        folderSize.putIfAbsent(currentDirectory, new AtomicInteger());
    }

    protected void leaveDirectory() {
        currentDirectory = getParentDirectory(currentDirectory);
    }

    protected void addSizeToFolder(String folder, int size) {
        folderSize.computeIfAbsent(folder, s -> new AtomicInteger()).addAndGet(size);
    }

    private void consolidateFolders() {
        folderSize.entrySet()
                .stream()
                .sorted((o1, o2) -> o2.getKey().length() - o1.getKey().length())
                .forEach(entry -> {
                    String parentDirectory = this.getParentDirectory(entry.getKey());
                    if (!entry.getKey().equals(parentDirectory) && folderSize.containsKey(parentDirectory)) {
                        folderSize.get(parentDirectory).addAndGet(entry.getValue().get());
                    }
                });
    }

    private String getParentDirectory(String directory) {
        int iend = directory.lastIndexOf("/");
        String output;
        if (iend != -1) {
            output = directory.substring(0, iend);
        } else {
            output = directory;
        }
        return output.equals("") ? "/" : output;
    }
}
