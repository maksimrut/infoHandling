package com.rutkouski.infohandling.parser.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rutkouski.infohandling.composite.SymbolLeaf;
import com.rutkouski.infohandling.composite.TextComponent;
import com.rutkouski.infohandling.parser.TextParser;

public class LetterParser implements TextParser {
	
	static Logger logger = LogManager.getLogger();
	
	@Override
	public void parse(String data, TextComponent component) {
		
		char[] letters = data.toCharArray();
		
		for (char letter : letters) {
			component.add(new SymbolLeaf(letter));
		}
		logger.info("Letter parsing was successful");
	}
}
