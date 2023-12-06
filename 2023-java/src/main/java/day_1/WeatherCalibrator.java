package day_1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WeatherCalibrator {

	int calibrate(String path) throws IOException {
		return Files.readAllLines(Paths.get(path)).stream()
				.map(this::parseLine)
				.reduce(0, Integer::sum);
	}

	public int parseLine(String line) {
		String[] allNumbers = convertLine(line).replaceAll("[^\\d.]", "").split("");

		System.out.println(line + " -- " + convertLine(line) + " -- " + allNumbers[0] + allNumbers[allNumbers.length - 1]);
		return Integer.parseInt(allNumbers[0] + allNumbers[allNumbers.length - 1]);
	}

	public String convertLine(String line) {
		String out = line;
		for (int i = 0; i < 100; i++) {
			out = convertNumbers(out);
		}
		return out;
	}

	String convertNumbers(String line) {
		Map<String, String> dictionary = Map.of("one", "1", "two", "2", "three", "3", "four", "4",
				"five", "5", "six", "6", "seven", "7", "eight", "8",
				"nine", "9");

		return Arrays.stream(line.replaceAll("[\\d.]", " ").split(" ")).toList()
				.stream()
				.map(s -> (Function<String, String>) function -> {
					TreeMap<Integer, String> resemblingNumbers = new TreeMap<>();
					dictionary.forEach((key, value) -> {
						if (s.contains(key)) {
							resemblingNumbers.put(s.indexOf(key), key);
						}
					});

					String toReplace = resemblingNumbers.entrySet().stream()
							.sorted(Map.Entry.comparingByKey())
							.map(Map.Entry::getValue)
							.map(dictionary::get)
							.collect(Collectors.joining(""));

					return function.replace(s, toReplace);
				})
				.reduce(Function.identity(), Function::andThen)
				.apply(line);
	}

	record ToReplace(String toReplace, String replaceWith) {
	}
}