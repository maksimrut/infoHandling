package com.rutkouski.infohandling.action;

import java.util.List;

import com.rutkouski.infohandling.composite.TextComponent;
import com.rutkouski.infohandling.exception.InfoHandlingException;

public interface TextCompositeAction {
	
	TextComponent sortBySentenceQuantity(TextComponent component);
	
	List<TextComponent> findLongestWordSentences(TextComponent component);
	
	void deleteSentencesWordsLessThan(TextComponent text, int minWordsQuantity) throws InfoHandlingException;
	
	List<TextComponent> findCountIdenticalWords(TextComponent component);
	
	long countVowels(TextComponent sentence) throws InfoHandlingException;
	
	long countConsonants(TextComponent sentence) throws InfoHandlingException;
}
