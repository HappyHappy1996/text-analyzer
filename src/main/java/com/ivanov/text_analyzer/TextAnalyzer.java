package com.ivanov.text_analyzer;

import java.io.File;
import java.io.FileNotFoundException;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.ivanov.text_analyzer.file.FileUtil;
import com.ivanov.text_analyzer.strategy.TaskStrategy;

public class TextAnalyzer {
	
	private TaskStrategy strategy;
	private File file;
	private String fileData;
	
	@Parameter(names = { "-i", "--input" }, description = "path to the input file (e.g. -i C:\\input.txt)")
	private String filePath;
	@Parameter(names = "--help", help = true)
	private boolean help;
	
	public void launch(String[] args) {
		JCommander jCommander = new JCommander(this, args);
		if (help) {
			jCommander.usage();
		}
		
		if (filePath != null) {
			try {
				fileData = FileUtil.readFile(filePath);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.err.println("There are no file that you specified!");
			}
			System.out.println(fileData);
		}
		
		
	}

}
