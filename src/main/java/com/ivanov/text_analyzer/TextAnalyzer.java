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

/*
 * A shell application which performs tasks with text from files one by another.
 * It accepts parameters from command line. The signature of input parameters:
 * -i (--input) - path to the input file (e.g. C:\Program Files\Java\input.txt). Required: true
 * -t (--task) – task to execute. Required: true, Permitted values: frequency, length, duplicates
 * -- help – a detailed information of how to use application
 * -exit - close application
 * 
 * Each output contains ‘elapsed time’ information in milliseconds e.g. elapsed time: 400 millis
 * 
 * e.g. -i text.txt -t length
 */
public class TextAnalyzer {

	private TaskStrategy strategy;
	private String fileData;
	private JCommander jCommander;

	@Parameter(names = { "-i", "--input" }, description = "path to the input file (e.g. -i C:\\input.txt)")
	private String filePath;
	@Parameter(names = { "-t", "--task" }, description = "permitted values: frequency (find the most two frequent words "
			+ "and print them out sorted alphabetically in a reversed order), length (find first three longest words and "
			+ "print this words along with the their length sorted them in a descend order by the total number of "
			+ "letters each word contains), duplicates (find first three words which have duplicates and print them "
			+ "inversely (e.g. map -> pam) in the upper case sorted by length in ascending order) e.g. -t frequency")
	private String task;
	@Parameter(names = "--help", description = "shows brief information about how to use this applicaiton", help = true)
	private boolean help;
	@Parameter(names = "-exit", description = "exit from the application (i.e. -exit)")
	private boolean exit;

	public void launch() {
		
		printWelcomeInfo();
		Scanner scanner = new Scanner(System.in);
		String nextLine = null;
		String[] args = null;

		do {
			dropState();
			nextLine = scanner.nextLine();
			
			long timeBefore = System.currentTimeMillis();
			
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
			long timeAfter = System.currentTimeMillis();
			System.out.println("elapsed time: " + (timeAfter - timeBefore) + " millis");
		} while (true);
		
		scanner.close();
	}

	private void printWelcomeInfo() {
		System.out.println("Weclome to text-analyzer application");
		System.out.println("It provides some analysis action with text files. Commands:");
		System.out.println("--help - a detailed information of how to use this app");
		System.out.println("-i (--input) - path to the input file (e.g. C:\\Program Files\\Java\\input.txt)");
		System.out.println("-t (--task) – task to execute. Permitted values: frequency, length, duplicates");
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
				throw new IllegalArgumentException();
			}
			switch (task) {
			case "frequency":
				strategy = new FrequencyStrategy(fileData);
				return;
			case "length":
				strategy = new LengthStrategy(fileData);
				return;
			case "duplicates":
				strategy = new DuplicateStrategy(fileData);
				return;
			default:
				throw new IllegalArgumentException();
			}
		}

	}

}
