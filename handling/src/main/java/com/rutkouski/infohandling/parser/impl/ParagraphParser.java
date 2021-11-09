package com.rutkouski.infohandling.parser.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rutkouski.infohandling.composite.TextComponent;
import com.rutkouski.infohandling.composite.TextComposite;
import com.rutkouski.infohandling.composite.TypeEnum;
import com.rutkouski.infohandling.parser.TextParser;

public class ParagraphParser implements TextParser {
	
	static Logger logger = LogManager.getLogger();
	private static final String PARAGRAPH_REGEX = "\\n+[\\s]*";
	private TextParser sentenceParser = new SentenceParser();
	
	@Override
	public void parse(String text, TextComponent component) {
		
		String[] paragraphs = text.strip().split(PARAGRAPH_REGEX);
		TextComposite paragraphComposite = new TextComposite(TypeEnum.PARAGRAPH);
		
		for (String paragraph : paragraphs) {
			component.add(paragraphComposite);
			nextChainLink(paragraph, paragraphComposite, sentenceParser);
		}
		logger.info("Paragraph parsing was successful");
	}
}
