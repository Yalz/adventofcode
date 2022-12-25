package day_5;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static day_5.crateMover.CrateMoverVersion.CRATE_MOVER_9000;
import static day_5.crateMover.CrateMoverVersion.CRATE_MOVER_9001;
import static org.junit.Assert.assertEquals;

public class CraneSetupTest {
	@Test
	public void test_crateMover9000Setup() throws IOException {
		List<String> input = Files.lines(Paths.get("src/test/resources/day_5/demo.txt"))
				.toList();

		CargoPort cargoPort = new CargoPort(CRATE_MOVER_9000, input);

		assertEquals(2, cargoPort.craneSetup.get("1").size());
		assertEquals(3, cargoPort.craneSetup.get("2").size());
		assertEquals(1, cargoPort.craneSetup.get("3").size());

		cargoPort.processInstructions(input);

		assertEquals(1, cargoPort.craneSetup.get("1").size());
		assertEquals(1, cargoPort.craneSetup.get("2").size());
		assertEquals(4, cargoPort.craneSetup.get("3").size());

		assertEquals("CMZ", cargoPort.getTopStack());
	}

	@Test
	public void test_crateMover9001() throws IOException {
		List<String> input = Files.lines(Paths.get("src/test/resources/day_5/demo.txt"))
				.toList();

		CargoPort cargoPort = new CargoPort(CRATE_MOVER_9001, input);

		assertEquals(2, cargoPort.craneSetup.get("1").size());
		assertEquals(3, cargoPort.craneSetup.get("2").size());
		assertEquals(1, cargoPort.craneSetup.get("3").size());

		cargoPort.processInstructions(input);

		assertEquals(1, cargoPort.craneSetup.get("1").size());
		assertEquals(1, cargoPort.craneSetup.get("2").size());
		assertEquals(4, cargoPort.craneSetup.get("3").size());

		assertEquals("MCD", cargoPort.getTopStack());
	}
}
