package com.rutkouski.infohandling.parser.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rutkouski.infohandling.composite.TextComponent;
import com.rutkouski.infohandling.composite.TextComposite;
import com.rutkouski.infohandling.composite.TypeEnum;
import com.rutkouski.infohandling.exception.InfoHandlingException;
import com.rutkouski.infohandling.parser.InformationParser;

public class ParagraphParser implements InformationParser {
	
	static Logger logger = LogManager.getLogger();
	private static final String SENTENCE_DELIMETER_REGEX = "(?<=[\\?!\\.])\\s+";
	private InformationParser nextParser;
	
	public ParagraphParser(InformationParser nextParser) {
		this.nextParser = nextParser;
	}
	
	@Override
	public TextComponent parse(String paragraph) throws InfoHandlingException {
		
		if (nextParser == null) {
			logger.error("Sentence parser is not specified");
			throw new InfoHandlingException("Sentence parser is not specified");
		}
		
		String[] sentences = paragraph.split(SENTENCE_DELIMETER_REGEX);
		TextComponent component = new TextComposite(TypeEnum.PARAGRAPH);
		
		for (String sentence : sentences) {
			TextComponent sentenceComposite = nextParser.parse(sentence);
			component.add(sentenceComposite);
		}
		logger.info("Paragraph parsing was successful: " + paragraph);
		return component;
	}
}
