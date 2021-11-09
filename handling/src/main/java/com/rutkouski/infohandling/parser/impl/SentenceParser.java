package com.rutkouski.infohandling.parser.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rutkouski.infohandling.composite.TextComponent;
import com.rutkouski.infohandling.composite.TextComposite;
import com.rutkouski.infohandling.composite.TypeEnum;
import com.rutkouski.infohandling.parser.TextParser;

public class SentenceParser implements TextParser {
	
	static Logger logger = LogManager.getLogger();
	private static final String SENTENCE_DELIMETER_REGEX = "[\\!\\?\\.]+\\s+";
	private TextParser wordParser = new LexemeParser();	
	
	@Override
	public void parse(String paragraph, TextComponent component) {
		
		String[] sentences = paragraph.split(SENTENCE_DELIMETER_REGEX);
		TextComposite sentenceComposite = new TextComposite(TypeEnum.SENTENCE);
		
		for (String sentence : sentences) {
			component.add(sentenceComposite);
			nextChainLink(sentence, sentenceComposite, wordParser);
		}
		logger.info("Sentence parsing was successful");
	}
}
