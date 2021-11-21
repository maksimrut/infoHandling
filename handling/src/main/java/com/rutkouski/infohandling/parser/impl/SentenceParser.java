package com.rutkouski.infohandling.parser.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rutkouski.infohandling.composite.TextComponent;
import com.rutkouski.infohandling.composite.TextComposite;
import com.rutkouski.infohandling.composite.TypeEnum;
import com.rutkouski.infohandling.exception.InfoHandlingException;
import com.rutkouski.infohandling.parser.InformationParser;

public class SentenceParser implements InformationParser {
	
	static Logger logger = LogManager.getLogger();
	private static final String LEXEME_DELIMETER_REGEX = "\\s+";
	private InformationParser nextParser;
	
	public SentenceParser(InformationParser nextParser) {
		this.nextParser = nextParser;
	}
	
	@Override
	public TextComponent parse(String sentence) throws InfoHandlingException {
		
		if (nextParser == null) {
			logger.error("Sentence parser is not specified");
			throw new InfoHandlingException("Lexeme parser is not specified");
		}
		String[] lexemes = sentence.split(LEXEME_DELIMETER_REGEX);
		TextComponent component = new TextComposite(TypeEnum.SENTENCE);
		
		for (String lexeme : lexemes) {
			TextComponent lexemeComposite = nextParser.parse(lexeme);
			component.add(lexemeComposite);
		}
		logger.info("Sentence parsing was successful: " + sentence);
		return component;
	}
}
