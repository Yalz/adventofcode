package day_5;

import java.util.List;
import java.util.Map;
import java.util.Stack;

public class CrateMover9001 implements CrateMover {
	private final Map<String, Stack<String>> craneSetup;

	public CrateMover9001(Map<String, Stack<String>> craneSetup) {
		this.craneSetup = craneSetup;
	}

	@Override
	public void processOperation(int crateCount, String from, String to) {
		Stack<String> processStack = new Stack<>();
		for (int i = 0; i < crateCount; i++) {
			if (craneSetup.get(from).isEmpty()) {
				return;
			}
			String crate = craneSetup.get(from).pop();
			processStack.push(crate);
		}
		int processCount = processStack.size();
		for (int i = 0; i < processCount; i++) {
			craneSetup.get(to).push(processStack.pop());
		}

	}
}
