package com.rutkouski.infohandling.action;

import java.util.List;

import com.rutkouski.infohandling.composite.TextComponent;
import com.rutkouski.infohandling.exception.InfoHandlingException;

public interface TextCompositeAction {
	
	void sortParagraphsBySentenceQuantity(TextComponent text) throws InfoHandlingException;
	
	List<TextComponent> findLongestWordSentences(TextComponent text) throws InfoHandlingException;
	
	void removeSentencesWordsLessThan(TextComponent text, int minWordsQuantity) throws InfoHandlingException;
	
	Long findCountIdenticalWords(TextComponent text) throws InfoHandlingException;
	
	long countVowels(TextComponent sentence) throws InfoHandlingException;
	
	long countConsonants(TextComponent sentence) throws InfoHandlingException;
}
