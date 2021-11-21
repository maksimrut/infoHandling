package com.rutkouski.infohandling.action.impl;

import java.util.Comparator;
import java.util.HashMap;
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
	public void sortParagraphsBySentenceQuantity(TextComponent text) throws InfoHandlingException {
		if (text.getType() != TypeEnum.TEXT) {
			logger.error("Illegal component type: {}. Expected TEXT", text.getType());
			throw new InfoHandlingException("Illegal component type: " + text.getType() + " Expected TEXT");
		}
		Comparator<TextComponent> sentenceQuantityComparator = (ob1, ob2) -> {
			int quantity1 = ob1.getComponents().size();
			int quantity2 = ob2.getComponents().size();
			return Integer.compare(quantity1, quantity2);
		};

		List<TextComponent> paragraphs = text.getComponents();
		paragraphs.sort(sentenceQuantityComparator);
	}

	@Override
	public List<TextComponent> findLongestWordSentences(TextComponent text) throws InfoHandlingException {
		if (text.getType() != TypeEnum.TEXT) {
			logger.error("Illegal component type: {}. Expected TEXT", text.getType());
			throw new InfoHandlingException("Illegal component type: " + text.getType() + " Expected TEXT");
		}
		int longestWordLength = text.getComponents().stream()
		.flatMap(parafraph -> parafraph.getComponents().stream())
		.map(this::defineLongestWordLength)
		.max(Integer::compareTo).orElse(-1);
	
		return text.getComponents().stream()
				.flatMap(parafraph -> parafraph.getComponents().stream())
				.filter(sentence -> defineLongestWordLength(sentence) == longestWordLength)
				.toList();
	}

	@Override
	public void removeSentencesWordsLessThan(TextComponent text, int minWordsQuantity) throws InfoHandlingException {
		if (text.getType() != TypeEnum.TEXT) {
			logger.error("Illegal component type: {}. Expected TEXT", text.getType());
			throw new InfoHandlingException("Illegal component type: " + text.getType() + " Expected TEXT");
		}
		List<TextComponent> paragrphs = text.getComponents();
		
		for (TextComponent paragrph : paragrphs) {
			List<TextComponent> sentences = paragrph.getComponents();
			logger.info(sentences.toString());
			sentences.removeIf(sentence -> countWords(sentence) < minWordsQuantity);
			logger.info(sentences.toString());
		}
	}

	@Override
	public Long findCountIdenticalWords(TextComponent text) throws InfoHandlingException {
		if (text.getType() != TypeEnum.TEXT) {
			logger.error("Illegal component type: {}. Expected TEXT", text.getType());
			throw new InfoHandlingException("Illegal component type: " + text.getType() + " Expected TEXT");
		}
		List<String> wordList = text.getComponents().stream()
		.flatMap(parafraph -> parafraph.getComponents().stream())
		.flatMap(sentence -> sentence.getComponents().stream())
		.flatMap(lexeme -> lexeme.getComponents().stream())
		.filter(component -> component.getType() == TypeEnum.WORD)
		.map(Object::toString).toList(); 

		HashMap<String, Integer> wordsMap = new HashMap<>();
		for (String word : wordList) {
			if (wordsMap.containsKey(word)) {
				wordsMap.put(word, wordsMap.get(word) + 1);
			} else {
				wordsMap.put(word, 1);
			}
		}
		return wordsMap.values().stream()
				.filter(value -> value > 1).count();
	}

	@Override
	public long countVowels(TextComponent sentence) throws InfoHandlingException {
		if (sentence.getType() != TypeEnum.SENTENCE) {
			logger.error("Illegal component type: {}. Expected SENTENCE", sentence.getType());
			throw new InfoHandlingException("Illegal component type: " + sentence.getType() + " Expected SENTENCE");
		}
		return countLetters(sentence, VOWEL_REGEX);
	}

	@Override
	public long countConsonants(TextComponent sentence) throws InfoHandlingException {
		if (sentence.getType() != TypeEnum.SENTENCE) {
			logger.error("Illegal component type: {}. Expected SENTENCE", sentence.getType());
			throw new InfoHandlingException("Illegal component type: " + sentence.getType() + " Expected SENTENCE");
		}
		return countLetters(sentence, CONSONANT_REGEX);
	}
	
	private long countLetters(TextComponent sentence, String regex) {
		return sentence.getComponents().stream()
		.flatMap(lexeme -> lexeme.getComponents().stream())
		.filter(component -> component.getType() == TypeEnum.WORD)
		.flatMap(word -> word.getComponents().stream())
		.filter(symbol -> symbol.toString().matches(regex))
		.count();
	}
	
	private int countWords(TextComponent sentence) {
		long quantity = sentence.getComponents().stream()
		.flatMap(lexeme -> lexeme.getComponents().stream())
		.filter(component -> component.getType() == TypeEnum.WORD).count();
		return (int) quantity;
	}	

	private int defineLongestWordLength(TextComponent sentence) {
		return sentence.getComponents().stream()
		.flatMap(lexeme -> lexeme.getComponents().stream())
		.filter(component -> component.getType() == TypeEnum.WORD)
		.map(word -> word.getComponents().size())
		.max(Integer::compareTo).orElse(-1);
	}
}
