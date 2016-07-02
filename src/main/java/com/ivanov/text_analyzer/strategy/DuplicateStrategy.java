package com.ivanov.text_analyzer.strategy;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DuplicateStrategy extends AbstractTaskStrategy {

	public DuplicateStrategy(String fileData) {
		super(fileData);
	}

	public void execute() {
		Set<String> words = new HashSet<String>();
		Set<String> duplicateWords = new HashSet<String>();
		String[] lines = getFileData().split("\n");

		Pattern pattern = Pattern
				.compile(REGEX_SELECT_WORDS);
		
		int maxElementsCount = 3;
		int actualElementsCount = 0;
		
		for (int i = 0; i < lines.length; i++) {
			Matcher matcher = pattern.matcher(lines[i]);
			while (matcher.find()) {
				String word = matcher.group(1).toLowerCase();
				if (words.contains(word)) {
					String duplicateWord = new StringBuilder(word.toUpperCase()).
							reverse().toString();
					duplicateWords.add(duplicateWord);
					actualElementsCount++;
					if (actualElementsCount >= maxElementsCount) {
						i = lines.length;
						break;
					}
				} else {
					words.add(word);
				}
			}
		}
		
		/*
		 * natural order comparator by length
		 */
		Comparator<String> comparator = new Comparator<String>() {
			@Override
			public int compare(String str1, String str2) {
				return str1.length() - str1.length();
			}
		};
		
		List<String> sortedDuplicateWords = duplicateWords.stream()
				.sorted(comparator).collect(Collectors.toList());
		
		for (String string : sortedDuplicateWords) {
			System.out.println(string);
		}
		
	}

}
