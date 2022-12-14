package day_5;

import day_5.crateMover.CrateMover;
import day_5.crateMover.CrateMover9000;
import day_5.crateMover.CrateMover9001;
import day_5.crateMover.CrateMoverVersion;

import java.util.*;

import static java.lang.Integer.parseInt;

public class CargoPort {
	CrateMover crateMover;
	String craneSetupLine;
	Map<String, Stack<String>> craneSetup = new HashMap<>();
	public CargoPort(CrateMoverVersion crateMoverVersion, List<String> input) {
		Stack<String> inputStack = new Stack<>();

		for(String line: input) {
			if(line.equals("")) {
				break;
			}
			inputStack.add(line);
		}

		craneSetupLine = inputStack.pop();
		this.setupCrates(inputStack, setupCranes());
		switch (crateMoverVersion) {
			case CRATE_MOVER_9000 -> this.crateMover = new CrateMover9000(craneSetup);
			case CRATE_MOVER_9001 -> this.crateMover = new CrateMover9001(craneSetup);
		}
	}

	private List<String> setupCranes() {
		List<String> cranes = Arrays.stream(craneSetupLine.split(" ")).filter(s -> !s.isBlank()).toList();
		for (String crane: cranes) {
			craneSetup.put(crane, new Stack<>());
		}
		return cranes;
	}

	private void setupCrates(Stack<String> inputStack, List<String> cranes) {
		int count = inputStack.size();
		for (int i = 0; i < count; i++) {
			String inputLine = inputStack.pop();
			for (String crane: cranes) {
				int indexOfCrane = getIndexOfCrane(crane);
				try {
					String crateValue = inputLine.substring(indexOfCrane, indexOfCrane+1);
					if(!crateValue.equals(" ")) {
						craneSetup.get(crane).push(crateValue);
					}
				} catch (StringIndexOutOfBoundsException e) {
					//					e.printStackTrace();
				}
			}
		}
	}

	public String getTopStack() {
		StringBuilder stringBuilder = new StringBuilder();
		craneSetup.forEach((key, value) -> {
			if (value.isEmpty()){
				stringBuilder.append(" ");
			}else {
				stringBuilder.append(value.peek());
			}
		});
		return stringBuilder.toString();
	}

	private int getIndexOfCrane(String crane) {
		return craneSetupLine.indexOf(crane);
	}

	public void processInstructions(List<String> input) {
		this.crateMover.processInstructions(input);
	}
}
