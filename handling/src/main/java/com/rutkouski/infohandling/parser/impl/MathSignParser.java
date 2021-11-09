package com.rutkouski.infohandling.parser.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rutkouski.infohandling.composite.SymbolLeaf;
import com.rutkouski.infohandling.composite.TextComponent;
import com.rutkouski.infohandling.parser.TextParser;

public class MathSignParser implements TextParser {
	
	static Logger logger = LogManager.getLogger();
	
	@Override
	public void parse(String equationLexeme, TextComponent component) {
		
		char[] symbols = equationLexeme.toCharArray();
		
		for (char symbol : symbols) {
			component.add(new SymbolLeaf(symbol));
		}
		logger.info("Equation parsing was successful");
	}
}
