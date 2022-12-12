package day_3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RucksackBundleTest {
	@Test
	public void testTotal() {
		RucksackBundle rucksackBundle = new RucksackBundle("src/test/resources/day_3/demo.txt");

		assertEquals(157, rucksackBundle.getBundleValue());
	}
}
