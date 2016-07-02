package com.ivanov.text_analyzer.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FrequencyStrategy extends AbstractTaskStrategy {

	public FrequencyStrategy(String fileData) {
		super(fileData);
	}

	public void execute() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		String[] lines = getFileData().split("\n");
		
		Pattern pattern = Pattern
				.compile(REGEX_SELECT_WORDS);
		
		for (int i = 0; i < lines.length; i++) {
			Matcher matcher = pattern.matcher(lines[i]);
			while (matcher.find()) {
				String word = matcher.group(1).toLowerCase();
				Integer integer = 1;
				if (map.containsKey(word)) {
					integer = map.get(word).intValue() + 1;
				}
				map.put(word, integer);
			}
		}
		
		System.out.println(map.size());
		
		for (Entry<String, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " повторяется " + entry.getValue());
		}
		
		int max = map.values().stream().max(Comparator.naturalOrder()).get();

		ArrayList<String> maxFrequentWords = new ArrayList<String>();
		
		int minElementsCount = 2;
		int actualElementsCount = 0;
				
		for (int i = max; i > 0; i--) {
			for (Entry<String, Integer> entry : map.entrySet()) {
				if (entry.getValue() == i) {
					maxFrequentWords.add(entry.getKey());
					actualElementsCount++;
					if (actualElementsCount >= minElementsCount) {
						i = 0;
						break;
					}
				}
			}
			
		}
		Collections.sort(maxFrequentWords, Comparator.<String>reverseOrder());
		
		for (int i = 0; i < minElementsCount; i++) {
			String word = maxFrequentWords.get(i);
			System.out.println(word + " -> " + map.get(word));
		}

	}

}