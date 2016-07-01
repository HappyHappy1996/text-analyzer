package com.ivanov.text_analyzer.strategy;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LengthStrategy implements TaskStrategy {

	private String fileData;

	public LengthStrategy(String fileData) {
		this.fileData = fileData;
	}
	
	public void execute() {
		
		Set<String> set = new HashSet<String>();
		String[] lines = fileData.split("\n");

		Pattern pattern = Pattern
				.compile("([a-zA-Zа-яА-Я]+)(, | \\(|\\) |\\. |[\\.,;:\\-' ])?");
		
		for (int i = 0; i < lines.length; i++) {
			Matcher matcher = pattern.matcher(lines[i]);
			while (matcher.find()) {
				String word = matcher.group(1).toLowerCase();
				set.add(word);
			}
		}
		
		/*
		 * reverse order comparator by length
		 */
		Comparator<String> comparator = new Comparator<String>() {
			@Override
			public int compare(String str1, String str2) {
				return str2.length() - str1.length();
			}
		};
		
		List<String> longetsWords = set.stream().sorted(comparator).limit(3).collect(Collectors.toList());
		
		for (String string : longetsWords) {
			System.out.println(string + " -> " + string.length());
		}
		
	}

}
