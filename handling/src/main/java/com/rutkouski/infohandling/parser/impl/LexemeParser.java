package com.rutkouski.infohandling.parser.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rutkouski.infohandling.composite.TextComponent;
import com.rutkouski.infohandling.composite.TextComposite;
import com.rutkouski.infohandling.composite.TypeEnum;
import com.rutkouski.infohandling.parser.TextParser;

public class LexemeParser implements TextParser {
	
	static Logger logger = LogManager.getLogger();
	private static final String LEXEME_DELIMETER_REGEX = "\\s+";
	private static final String WORD_WITH_PUNCTUATION_REGEX = "[\\wÀ-ÿ¸¨]+\\p{Punct}?";
	
	@Override
	public void parse(String sentence, TextComponent component) {
		String[] lexems = sentence.split(LEXEME_DELIMETER_REGEX);
		
		for (String lexeme : lexems) {
			
			TextComposite lexemeComposite = new TextComposite(TypeEnum.LEXEME);
			component.add(lexemeComposite);
			
			if (lexeme.matches(WORD_WITH_PUNCTUATION_REGEX)) {
				WordWithPunctuationParser wordParser = new WordWithPunctuationParser();
				nextChainLink(lexeme, lexemeComposite, wordParser);
			} else {
				MathSignParser mathSignParser = new MathSignParser();
				nextChainLink(lexeme, lexemeComposite, mathSignParser);
			}
		}
		logger.info("Lexeme parsing was successful");
	}
}
