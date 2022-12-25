package day_3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Rucksack implements PriorityRetriever {
	private final String content;
	private final String[] compartiments;
	private static final String priorityValues = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public Rucksack(String content) {
		this.content = content;
		final int mid = content.length() / 2; //get the middle of the String
		compartiments = new String[] { content.substring(0, mid), content.substring(mid) };
	}

	public String[] getCompartiments() {
		return compartiments;
	}

	public String getContent() {
		return content;
	}

	@Override
	public Set<String> getPriorities(){
		Set<String> priorities = new HashSet<>();
		for (char ch: compartiments[0].toCharArray()) {
			if (compartiments[1].contains(String.valueOf(ch))) {
				priorities.add(String.valueOf(ch));
				break;
			}
		}
		return priorities;
	}


}
