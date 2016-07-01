package com.ivanov.text_analyzer.strategy;

import com.beust.jcommander.JCommander;

public class HelpStrategy implements TaskStrategy {
	
	JCommander jc;
	
	public HelpStrategy(JCommander jc) {
		this.jc = jc;
	}

	public void execute() {
		jc.usage();
	}
	
	

}
