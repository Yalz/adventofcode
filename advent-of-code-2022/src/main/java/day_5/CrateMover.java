package day_5;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public interface CrateMover {
	String operationPattern = "move\\s(\\d+)\\sfrom\\s(\\d)\\sto\\s(\\d)";

	default void processInstructions(List<String> input) {
		input.stream().filter(s -> s.matches(operationPattern))
				.forEach(operation -> {
					Matcher m = Pattern.compile(operationPattern).matcher(operation);

					if (m.find()) {
						processOperation(parseInt(m.group(1)), m.group(2), m.group(3));
					} else {
						System.out.println("NO MATCH");
					}
				});
	}

	void processOperation(int crateCount, String from, String to);
}
