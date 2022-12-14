package day_5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static day_5.CrateMoverVersion.CRATE_MOVER_1000;
import static day_5.CrateMoverVersion.CRATE_MOVER_9001;

public class CraneDemo {
	public static void main(String[] args) throws IOException {
		List<String> input = Files.lines(Paths.get("advent-of-code-2022/src/main/resources/day_5/input.txt")).toList();

		CargoPort cargoPort = new CargoPort(CRATE_MOVER_1000, input);
		cargoPort.processInstructions(input);

		System.out.println(cargoPort.getTopStack());

		CargoPort cargoPort9001 = new CargoPort(CRATE_MOVER_9001, input);
		cargoPort9001.processInstructions(input);

		System.out.println(cargoPort9001.getTopStack());
	}
}
