package day_4;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class SectionCheckTest {
	@Test
	public void test() {
		SectionCheck sectionCheck = new SectionCheck("2-4,6-8");
		assertFalse(sectionCheck.fullyIntersects());
	}
	@Test
	public void testDemo() throws IOException {
		List<SectionCheck> fullyOverlapping = SectionCheckDemo.getFullyOverlappingSectionChecks("src/test/resources/day_4/demo.txt");
		assertEquals(2, fullyOverlapping.size());

		List<SectionCheck> partialOverlapping = SectionCheckDemo.getPartialOverlappingSectionChecks("src/test/resources/day_4/demo.txt");
		assertEquals(4, partialOverlapping.size());
	}
}
