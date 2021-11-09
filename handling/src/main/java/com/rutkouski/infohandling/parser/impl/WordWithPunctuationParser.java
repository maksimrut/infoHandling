package com.rutkouski.infohandling.parser.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rutkouski.infohandling.composite.SymbolLeaf;
import com.rutkouski.infohandling.composite.TextComponent;
import com.rutkouski.infohandling.composite.TextComposite;
import com.rutkouski.infohandling.composite.TypeEnum;
import com.rutkouski.infohandling.parser.TextParser;

public class WordWithPunctuationParser implements TextParser {
	
	static Logger logger = LogManager.getLogger();
	private static final String WORD_OR_PUNCTUATION_REGEX = "([\\wÀ-ÿ¸¨]+)|(\\p{Punct})";
	private static final String WORD_REGEX = "[\\p{Alpha}À-ÿ¸¨]+";
	
	@Override
	public void parse(String wordLexeme, TextComponent component) {
		
		Pattern pattern = Pattern.compile(WORD_OR_PUNCTUATION_REGEX);
		Matcher matcher = pattern.matcher(wordLexeme);
		
		while (matcher.find()) {
			String subsequence = matcher.group();
			
			Pattern wordPattern = Pattern.compile(WORD_REGEX);
			Matcher wordMatcher = wordPattern.matcher(wordLexeme);
			
			if (wordMatcher.matches()) {
				TextComposite wordComposite = new TextComposite(TypeEnum.WORD);
				component.add(wordComposite);
				LetterParser letterParser = new LetterParser();
				nextChainLink(subsequence, wordComposite, letterParser);
				
			} else {
				SymbolLeaf punctuation = new SymbolLeaf(subsequence.charAt(0));
				component.add(punctuation);
			}
		}
		logger.info("Word with punctuation parsing was successful");
	}
}
