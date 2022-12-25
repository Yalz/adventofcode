package day_3;

import java.util.List;
import java.util.Set;

public interface PriorityRetriever {
	Set<String> getPriorities();
	default int getPriorityValues() {
		Set<String> priorities = getPriorities();
		int value = 0;

		for (String priority: priorities) {
			value += " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(priority);
		}

		return value;
	}

}
