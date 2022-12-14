package day_5.crateMover;

import java.util.Map;
import java.util.Stack;

public class CrateMover9000 implements CrateMover {

	private final Map<String, Stack<String>> craneSetup;

	public CrateMover9000(Map<String, Stack<String>> craneSetup) {
		this.craneSetup = craneSetup;
	}

	@Override
	public void processOperation(int crateCount, String from, String to) {
		for (int i = 0; i < crateCount; i++) {
			if (craneSetup.get(from).isEmpty()) {
				return;
			}
			String crate = craneSetup.get(from).pop();
			craneSetup.get(to).push(crate);
		}
	}
}
