package com.ivanov.text_analyzer.strategy;

public abstract class AbstractTaskStrategy implements TaskStrategy {

	public static final String REGEX_SELECT_WORDS = "([a-zA-Zа-яА-Я]+)(, | \\(|\\) |\\. |[\\.,;:\\-' ])?";
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
