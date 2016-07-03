package com.ivanov.text_analyzer.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Strategy that provides search the most two frequent words
 * and prints them out sorted alphabetically in a reversed order
 */
public class FrequencyStrategy extends AbstractTaskStrategy {

	public FrequencyStrategy(String fileData) {
		super(fileData);
	}

	public void execute() {
		String[] lines = getFileData().split("\n");
		Map<String, Integer> wordsToRepetitionsCount = new HashMap<String, Integer>();
		
		Pattern pattern = Pattern.compile(REGEX_SELECT_WORDS);
		
		for (int i = 0; i < lines.length; i++) {
			Matcher matcher = pattern.matcher(lines[i]);
			while (matcher.find()) {
				String word = matcher.group(1).toLowerCase();
				Integer integer = 1;
				if (wordsToRepetitionsCount.containsKey(word)) {
					integer = wordsToRepetitionsCount.get(word).intValue() + 1;
				}
				wordsToRepetitionsCount.put(word, integer);
			}
		}
		
		int max = wordsToRepetitionsCount.values().stream().max(Comparator.naturalOrder()).get();

		ArrayList<String> maxFrequentWords = new ArrayList<String>();
		
		int minElementsCount = 2;
		int actualElementsCount = 0;
				
		for (int i = max; i > 0; i--) {
			for (Entry<String, Integer> entry : wordsToRepetitionsCount.entrySet()) {
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
			System.out.println(word + " -> " + wordsToRepetitionsCount.get(word));
		}

	}

}