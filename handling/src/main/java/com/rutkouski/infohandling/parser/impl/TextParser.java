package com.rutkouski.infohandling.parser.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rutkouski.infohandling.composite.TextComponent;
import com.rutkouski.infohandling.composite.TextComposite;
import com.rutkouski.infohandling.composite.TypeEnum;
import com.rutkouski.infohandling.exception.InfoHandlingException;
import com.rutkouski.infohandling.parser.InformationParser;

public class TextParser implements InformationParser {
	
	static Logger logger = LogManager.getLogger();
	private static final String PARAGRAPH_REGEX = "\\n+[\\s]*";
	private InformationParser nextParser;
	
	public TextParser(InformationParser nextParser) {
		this.nextParser = nextParser;
	}
	
	@Override
	public TextComponent parse(String text) throws InfoHandlingException {
		
		if (nextParser == null) {
			logger.error("Paragraph parser is not specified");
			throw new InfoHandlingException("Paragraph parser is not specified");
		}
		
		String[] paragraphs = text.strip().split(PARAGRAPH_REGEX);
		TextComponent component = new TextComposite(TypeEnum.TEXT);
		
		for (String paragraph : paragraphs) {
			TextComponent paragraphComposite = nextParser.parse(paragraph);
			component.add(paragraphComposite);
		}
		logger.info("Text parsing was successful: " + text);
		return component;
	}
}
