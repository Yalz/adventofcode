package day_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ElfPack {
    List<Integer> caloryCountPerElf = new ArrayList<>();

    public ElfPack(String path) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(path);

        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);

        int caloryCount = 0;
        int elfCount = 1;
        try {
            for (String line; (line = reader.readLine()) != null; ) {
                if (!line.equals("")) {
                    int individualCaloryValue = Integer.parseInt(line);
                    caloryCount += individualCaloryValue;
                } else {
                    System.out.println("Elf " + elfCount++ + " had " + caloryCount + " calories");
                    caloryCountPerElf.add(caloryCount);
                    caloryCount = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int computeSumTopCaloryCount(Integer topValue) {
        return caloryCountPerElf.stream()
                .sorted(Comparator.reverseOrder())
                .limit(topValue)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
