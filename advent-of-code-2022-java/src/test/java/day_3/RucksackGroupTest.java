package day_3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RucksackGroupTest {
	@Test
	public void test() {
		Rucksack x1 = new Rucksack("vJrwpWtwJgWrhcsFMMfFFhFp");
		Rucksack x2 = new Rucksack("jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL");
		Rucksack x3 = new Rucksack("PmmdzqPrVvPwwTWBwg");

		RucksackGroup rucksackGroup = new RucksackGroup();
		rucksackGroup.add(x1);
		rucksackGroup.add(x2);
		rucksackGroup.add(x3);

		assertEquals(1,rucksackGroup.getPriorities().size());
	}
}
