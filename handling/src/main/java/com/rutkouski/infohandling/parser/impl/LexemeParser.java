package com.rutkouski.infohandling.parser.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rutkouski.infohandling.composite.SymbolLeaf;
import com.rutkouski.infohandling.composite.TextComponent;
import com.rutkouski.infohandling.composite.TextComposite;
import com.rutkouski.infohandling.composite.TypeEnum;
import com.rutkouski.infohandling.exception.InfoHandlingException;
import com.rutkouski.infohandling.parser.InformationParser;

public class LexemeParser implements InformationParser {
	
	static Logger logger = LogManager.getLogger();
	private static final String LEXEME_REGEX = "(?=([?,!-]|((?<!\\.)\\.(?!\\.)))$)|((?<=^\\()(?![0-9~]))|((?<=[a-zA-Z])(?=\\)))";
	private static final String WORD_REGEX = "[а-яА-Я\\w'-]+";
	private static final String PUNCTUATION_REGEX = "[?.,!)(-]";
	private InformationParser wordParser;
	private InformationParser expressionParser;
	
	public LexemeParser(InformationParser wordParser, InformationParser equationParser) {
		this.wordParser = wordParser;
		this.expressionParser = equationParser;
	}
	
	@Override
	public TextComponent parse(String text) throws InfoHandlingException {
		
		if (wordParser == null || expressionParser == null) {
			logger.error("At least one of parsers is not specified");
			throw new InfoHandlingException("At least one of parsers is not specified");
		}
		
		String[] lexemes = text.split(LEXEME_REGEX);
		TextComponent component = new TextComposite(TypeEnum.LEXEME);
		
		for (String lexeme : lexemes) {
			TextComponent currentComponent;
			if (lexeme.matches(PUNCTUATION_REGEX)) {
				currentComponent = new SymbolLeaf(TypeEnum.PUNCTUATION, lexeme.charAt(0));
			} else if (lexeme.matches(WORD_REGEX) && lexeme.length() == 1) {
				currentComponent = new SymbolLeaf(TypeEnum.SYMBOL, lexeme.charAt(0));
			} else if (lexeme.matches(WORD_REGEX)) {
				currentComponent = wordParser.parse(lexeme);
			} else {
				currentComponent = expressionParser.parse(lexeme);
			}
			component.add(currentComponent);
		}
		logger.info("Lexeme parsing was successful: " + text);
		return component;
	}
}
