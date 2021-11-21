package com.rutkouski.infohandling.parser.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rutkouski.infohandling.composite.SymbolLeaf;
import com.rutkouski.infohandling.composite.TextComponent;
import com.rutkouski.infohandling.composite.TextComposite;
import com.rutkouski.infohandling.composite.TypeEnum;
import com.rutkouski.infohandling.exception.InfoHandlingException;
import com.rutkouski.infohandling.parser.InformationParser;

public class WordParser implements InformationParser {
	
	static Logger logger = LogManager.getLogger();
	
	@Override
	public TextComponent parse(String word) throws InfoHandlingException {
		
		TextComponent component = new TextComposite(TypeEnum.WORD);
		char[] characters = word.toCharArray();
		
		for (char character : characters) {
			TextComponent symbolComponent = new  SymbolLeaf(TypeEnum.SYMBOL, character);
			component.add(symbolComponent);
		}
		logger.info("Word parsing was successful: " + word);
		return component;
	}
}
