package day_4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SectionCheckDemo {

	public static List<SectionCheck> getFullyOverlappingSectionChecks(String inputFile) throws IOException {
		return Files.lines(Paths.get(inputFile))
				.map(SectionCheck::new)
				.filter(SectionCheck::fullyIntersects)
				.toList();
	}

	public static List<SectionCheck> getPartialOverlappingSectionChecks(String inputFile) throws IOException {
		return Files.lines(Paths.get(inputFile))
				.map(SectionCheck::new)
				.filter(SectionCheck::partiallyIntersect)
				.toList();
	}

	public static void main(String[] args) throws IOException {
		long intersectCount = getFullyOverlappingSectionChecks("advent-of-code-2022/src/main/resources/day_4/input.txt")
				.size();
		System.out.println(intersectCount + " section checks fully overlap");

		long partialIntersectCount = getPartialOverlappingSectionChecks("advent-of-code-2022/src/main/resources/day_4/input.txt")
				.size();

		System.out.println(partialIntersectCount + " section checks partially overlap");

	}
}
