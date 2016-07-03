package com.ivanov.text_analyzer.strategy;

/*
 * Abstract class that has basic field for reading words using regular expressions
 */
public abstract class AbstractTaskStrategy implements TaskStrategy {

	public static final String REGEX_SELECT_WORDS = "([a-zA-Zа-яА-Я]+)(, | \\(|\\) |\\. |\\p{Punct})?";
	
	private String fileData;

	public AbstractTaskStrategy(String fileData) {
		this.fileData = fileData;
	}

	public String getFileData() {
		return fileData;
	}

	public void setFileData(String fileData) {
		this.fileData = fileData;
	}
	
}
