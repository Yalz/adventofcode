package day_3;

import java.util.*;

public class RucksackGroup implements PriorityRetriever {
	private final List<Rucksack> rucksacks;

	public RucksackGroup() {
		rucksacks = new ArrayList<>();
	}

	public void add(Rucksack rucksack) {
		rucksacks.add(rucksack);
	}

	public void clear() {
		rucksacks.clear();
	}

	@Override
	public Set<String> getPriorities() {
		Set<String> commonPriorities = new HashSet<>(Arrays.asList(rucksacks.get(0).getContent().split("")));
		Set<String> toDelete = new HashSet<>();

		for (int i = 1; i< rucksacks.size(); i++) {
			for (String prio : commonPriorities) {
				if (!rucksacks.get(i).getContent().contains(prio))
					toDelete.add(prio);
			}
		}
		commonPriorities.removeAll(toDelete);
		return commonPriorities;
	}
}
