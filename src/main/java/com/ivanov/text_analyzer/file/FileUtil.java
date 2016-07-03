package com.ivanov.text_analyzer.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * Class that provides functionality with files
 */
public class FileUtil {
	
	public static String readFile(String filePath) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(filePath), "UTF-8");
		StringBuilder sb = new StringBuilder();
		while (scanner.hasNextLine()) {
			sb.append(scanner.nextLine()).append("\n");
		}
		scanner.close();
		return sb.toString().trim();
	}

}
