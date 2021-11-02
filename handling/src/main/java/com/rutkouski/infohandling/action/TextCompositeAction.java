package com.rutkouski.infohandling.action;

import java.util.List;

import com.rutkouski.infohandling.composite.TextComponent;

public interface TextCompositeAction {
	
	TextComponent sortBySentenceQuantity(TextComponent component);
	
	List<TextComponent> findLongestWordSentences(TextComponent component);
	
	TextComponent deleteSentencesWordsLessThan(TextComponent component, int wordsQuantity);
	
	List<TextComponent> findCountIdenticalWords(TextComponent component);
	
	int countVowels(TextComponent component);
	
	int countConsonants(TextComponent component);
}
