package day_3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RucksackBundle {
	private final List<String> lines;

	public RucksackBundle(String path) {
		try {
			lines = Files.lines(Paths.get(path)).toList();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	public int getBundleValue() {
		int value = 0;

		for (String line: lines) {
			value += new Rucksack(line).getPriorityValues();
		}
		return value;
	}

	public int getGroupBundleValue(int groupCount) {
		int value = 0;
		int rucksackCount = 0;

		RucksackGroup rucksackGroup = new RucksackGroup();
		for (String line: lines) {
			Rucksack rucksack = new Rucksack(line);
			System.out.println("rucksackCount++ % groupCount " + (rucksackCount++ % groupCount));
			if(rucksackCount % groupCount == 0 && rucksackCount != 0) {
				rucksackGroup.add(rucksack);
			} else {
				rucksackGroup.add(rucksack);
				value += rucksackGroup.getPriorityValues();
				rucksackGroup.clear();
			}

		}
		return value;
	}
}
