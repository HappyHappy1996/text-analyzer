package com.ivanov.text_analyzer.strategy;

public class FrequencyStrategy implements TaskStrategy {
	
	private String fileData;
	
	public FrequencyStrategy(String fileData) {
		this.fileData = fileData;
	}

	public void execute() {
		System.out.println(fileData);
	}

}
