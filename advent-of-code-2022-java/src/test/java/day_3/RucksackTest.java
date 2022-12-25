package day_3;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class RucksackTest {
	@Test
	public void rucksack_splitter() {
		String input = "abcd";

		Rucksack rucksack = new Rucksack(input);

		assertArrayEquals(new String[] { "ab", "cd" }, rucksack.getCompartiments());
	}

	@Test
	public void rucksack_priorities() {
		String input = "abad";

		Rucksack rucksack = new Rucksack(input);

		Set<String> priorities = rucksack.getPriorities();

		assertEquals(1, priorities.size());
		assertEquals(Set.of("a"), priorities);
	}

	@Test
	public void rucksack_priority_value() {
		//		assertEquals(1, Rucksack.getPriorityValue("a"));
		//		assertEquals(26, Rucksack.getPriorityValue("z"));
		//		assertEquals(27, Rucksack.getPriorityValue("A"));
		//		assertEquals(52, Rucksack.getPriorityValue("Z"));
	}

	@Test
	public void rucksack_demo() {
		Rucksack x1 = new Rucksack("vJrwpWtwJgWrhcsFMMfFFhFp");
		assertEquals(16, x1.getPriorityValues());

		Rucksack x2 = new Rucksack("jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL");
		Set<String> priorities = x2.getPriorities();
		assertEquals(1, priorities.size());
		assertEquals(Set.of("L"), priorities);
		assertEquals(38, x2.getPriorityValues());

		Rucksack x3 = new Rucksack("PmmdzqPrVvPwwTWBwg");
		priorities = x3.getPriorities();
		assertEquals(1, priorities.size());
		assertEquals(Set.of("P"), priorities);
		assertEquals(42, x3.getPriorityValues());

		Rucksack x4 = new Rucksack("wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn");
		priorities = x4.getPriorities();
		assertEquals(1, priorities.size());
		assertEquals(Set.of("v"), priorities);
		assertEquals(22, x4.getPriorityValues());

		Rucksack x5 = new Rucksack("ttgJtRGJQctTZtZT");
		priorities = x5.getPriorities();
		assertEquals(1, priorities.size());
		assertEquals(Set.of("t"), priorities);
		assertEquals(20, x5.getPriorityValues());

		Rucksack x6 = new Rucksack("CrZsJsPPZsGzwwsLwLmpwMDw");
		priorities = x6.getPriorities();
		assertEquals(1, priorities.size());
		assertEquals(Set.of("s"), priorities);
		assertEquals(19, x6.getPriorityValues());

	}
}
