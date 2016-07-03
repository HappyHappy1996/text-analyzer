package com.ivanov.text_analyzer.strategy;

import com.beust.jcommander.JCommander;

/*
 * Strategy that prints detailed information of how to use application
 */
public class HelpStrategy implements TaskStrategy {
	
	private JCommander jc;
	
	public HelpStrategy(JCommander jc) {
		this.jc = jc;
	}

	public void execute() {
		jc.usage();
	}

}
