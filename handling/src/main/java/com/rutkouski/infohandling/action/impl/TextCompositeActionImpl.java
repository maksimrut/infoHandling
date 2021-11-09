package com.rutkouski.infohandling.action.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rutkouski.infohandling.action.TextCompositeAction;
import com.rutkouski.infohandling.composite.TextComponent;
import com.rutkouski.infohandling.composite.TypeEnum;
import com.rutkouski.infohandling.exception.InfoHandlingException;

public class TextCompositeActionImpl implements TextCompositeAction {
	
	static Logger logger = LogManager.getLogger();
	private static final String VOWEL_REGEX = "[AEIOUaeiou]";
	private static final String CONSONANT_REGEX = "[[^AEIOUaeiou]&&A-z]";
	
	@Override
	public TextComponent sortBySentenceQuantity(TextComponent component) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TextComponent> findLongestWordSentences(TextComponent component) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteSentencesWordsLessThan(TextComponent text, int minWordsQuantity) throws InfoHandlingException {
		if (text.getType() != TypeEnum.TEXT) {
			logger.error("Illegal component type: {}. Expected TEXT", text.getType());
			throw new InfoHandlingException("Illegal component type: " + text.getType() + " Expected TEXT");
		}
		List<TextComponent> paragrphs = text.getComponents();
		
		for (TextComponent paragrph : paragrphs) {
			List<TextComponent> sentences = paragrph.getComponents();
			sentences.removeIf(sentence -> countWords(sentence) < minWordsQuantity);
		}
	}



	@Override
	public List<TextComponent> findCountIdenticalWords(TextComponent component) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long countVowels(TextComponent sentence) throws InfoHandlingException {
		if (sentence.getType() != TypeEnum.SENTENCE) {
			logger.error("Illegal component type: {}. Expected SENTENCE", sentence.getType());
			throw new InfoHandlingException("Illegal component type: " + sentence.getType() + " Expected SENTENCE");
		}
		
		return sentence.getComponents().stream()
				.flatMap(lexeme -> lexeme.getComponents().stream())
				.filter(component -> component.getType() == TypeEnum.WORD)
				.flatMap(word -> word.getComponents().stream())
				.filter(symbol -> symbol.toString().matches(VOWEL_REGEX))
				.count();
	}

	@Override
	public long countConsonants(TextComponent sentence) throws InfoHandlingException {
		if (sentence.getType() != TypeEnum.SENTENCE) {
			logger.error("Illegal component type: {}. Expected SENTENCE", sentence.getType());
			throw new InfoHandlingException("Illegal component type: " + sentence.getType() + " Expected SENTENCE");
		}
		
		return sentence.getComponents().stream()
				.flatMap(lexeme -> lexeme.getComponents().stream())
				.filter(component -> component.getType() == TypeEnum.WORD)
				.flatMap(word -> word.getComponents().stream())
				.filter(symbol -> symbol.toString().matches(CONSONANT_REGEX))
				.count();
	}
	
	private int countWords(TextComponent sentence) {
		long quantity = sentence.getComponents().stream()
		.flatMap(lexeme -> lexeme.getComponents().stream())
		.filter(component -> component.getType() == TypeEnum.WORD).count();
		return (int) quantity;
	}
}
