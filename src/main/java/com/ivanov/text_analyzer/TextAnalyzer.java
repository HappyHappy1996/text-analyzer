package com.ivanov.text_analyzer;

import java.io.FileNotFoundException;
import java.util.Scanner;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.ivanov.text_analyzer.file.FileUtil;
import com.ivanov.text_analyzer.strategy.DuplicateStrategy;
import com.ivanov.text_analyzer.strategy.FrequencyStrategy;
import com.ivanov.text_analyzer.strategy.HelpStrategy;
import com.ivanov.text_analyzer.strategy.LengthStrategy;
import com.ivanov.text_analyzer.strategy.TaskStrategy;

public class TextAnalyzer {

	private TaskStrategy strategy;
	private String fileData;
	private JCommander jCommander;

	@Parameter(names = { "-i", "--input" }, description = "path to the input file (e.g. -i C:\\input.txt)")
	private String filePath;
	@Parameter(names = { "-t", "--task" }, description = "permitted values: frequency, length, duplicates (e.g. -t frequency)")
	private String task;
	@Parameter(names = "--help", help = true)
	private boolean help;
	@Parameter(names = "-exit", description = "exit from the application (i.e. -exit)")
	private boolean exit;

	public void launch() {
		Scanner scanner = new Scanner(System.in);
		String nextLine = null;
		String[] args = null;

		do {
			dropState();
			nextLine = scanner.nextLine();
			args = nextLine.split(" ");

			try {
				jCommander = new JCommander(this, args);

				if (exit) {
					break;
				}

				chooseStrategy();
			} catch (IllegalArgumentException | ParameterException e) {
				System.err.println("Invalid commands");
				continue;
			}
			strategy.execute();

		} while (true);
		
		scanner.close();
	}

	private void dropState() {
		strategy = null;
		fileData = null;
		help = false;
	}

	public void chooseStrategy() {

		if (help) {
			strategy = new HelpStrategy(jCommander);
			return;
		}

		if (filePath != null) {
			try {
				fileData = FileUtil.readFile(filePath);
			} catch (FileNotFoundException e) {
				System.err.println("There are no file that you specified!");
			}
		}

		if ("frequency".equals(task)) {
			strategy = new FrequencyStrategy(fileData);
			return;
		}
		
		if ("length".equals(task)) {
			strategy = new LengthStrategy(fileData);
			return;
		}
		if ("duplicates".equals(task)) {
			strategy = new DuplicateStrategy(fileData);
			return;
		}

		if (strategy == null) {
			throw new IllegalArgumentException();
		}

	}

}
