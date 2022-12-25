package day_3;

public class RucksackPriority {
	public static void main(String[] args) {
		RucksackBundle rucksackBundle = new RucksackBundle("advent-of-code-2022/src/main/resources/day_3/input.txt");

		System.out.println("Total sum of priorities is " + rucksackBundle.getBundleValue());
		System.out.println("Total sum of priorities for three-Elf group "+ rucksackBundle.getGroupBundleValue(3));
	}
}
