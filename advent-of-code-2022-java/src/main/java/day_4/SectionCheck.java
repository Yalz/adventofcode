package day_4;

import java.util.stream.IntStream;

public class SectionCheck {
	int min1, max1, min2, max2;

	public SectionCheck(String sectionCheck) {
		String[] sections = sectionCheck.split(",");
		min1 = Integer.parseInt(sections[0].split("-")[0]);
		min2 = Integer.parseInt(sections[1].split("-")[0]);
		max1 = Integer.parseInt(sections[0].split("-")[1]);
		max2 = Integer.parseInt(sections[1].split("-")[1]);
	}

	public boolean fullyIntersects() {
		return (min1 <= min2 && max2 <= max1) || (min2 <= min1 && max1 <= max2);
	}

	public boolean partiallyIntersect() {
		return (min1 <= min2 && min2 <= max1) || (min1 <= max2 && max2 <= max1)
				|| (min2 <= min1 && min1 <= max2) || (min2 <= max1 && max1 <= max2) || fullyIntersects() ;
	}
}
