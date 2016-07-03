package com.ivanov.text_analyzer.strategy;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/*
 * Strategy that provides search for first three longest words and prints
 * this words along with the their length sorted them
 * in a descend order by the total number of letters each word contains
 */
public class LengthStrategy extends AbstractTaskStrategy {

	public LengthStrategy(String fileData) {
		super(fileData);
	}

	public void execute() {

		String[] lines = getFileData().split("\n");
		Set<String> words = new HashSet<String>();

		Pattern pattern = Pattern.compile(REGEX_SELECT_WORDS);

		for (int i = 0; i < lines.length; i++) {
			Matcher matcher = pattern.matcher(lines[i]);
			while (matcher.find()) {
				String word = matcher.group(1).toLowerCase();
				words.add(word);
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

		List<String> longetsWords = words.stream().sorted(comparator).limit(3)
				.collect(Collectors.toList());

		for (String string : longetsWords) {
			System.out.println(string + " -> " + string.length());
		}

	}

}
