package com.rutkouski.infohandling.action.impl;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rutkouski.infohandling.composite.TextComponent;
import com.rutkouski.infohandling.exception.InfoHandlingException;
import com.rutkouski.infohandling.parser.impl.TextParser;
import com.rutkouski.infohandling.parser.impl.WordParser;
import com.rutkouski.infohandling.parser.InformationParser;
import com.rutkouski.infohandling.parser.impl.ExpressionParser;
import com.rutkouski.infohandling.parser.impl.LexemeParser;
import com.rutkouski.infohandling.parser.impl.ParagraphParser;
import com.rutkouski.infohandling.parser.impl.SentenceParser;

public class TextCompositeActionImplTest {

	private TextCompositeActionImpl service = new TextCompositeActionImpl();
	private WordParser wordParser = new WordParser();
	private ExpressionParser expressionParser = new ExpressionParser();
	private LexemeParser lexemeParser = new LexemeParser(wordParser, expressionParser);
	private SentenceParser sentenceParser = new SentenceParser(lexemeParser);;
	private ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);
	private InformationParser textParser = new TextParser(paragraphParser);

	@DataProvider(name = "dataConsonants")
    public Object[][] consonantsData() throws InfoHandlingException {
        String sentence1 = "Hello world.";
        String sentence2 = "HHHHello worlddd.";
        return new Object[][]{
                {7L, sentenceParser.parse(sentence1)},
                {12L, sentenceParser.parse(sentence2)}
        };
    }
		
	@Test(dataProvider = "dataConsonants")
	public void countConsonantsTest(Long expected, TextComponent component) throws InfoHandlingException {
		Long actual = service.countConsonants(component);
		assertEquals(actual, expected);
	}
  
	@DataProvider(name = "dataVowels")
	public Object[][] vowelsData() throws InfoHandlingException {
	    String sentence1 = "Hello world.";
	    String sentence2 = "Hellooo worldooo.";
	    return new Object[][]{
	        {3L, sentenceParser.parse(sentence1)},
	        {8L, sentenceParser.parse(sentence2)}
	    };
	}

    @Test(dataProvider = "dataVowels")
    public void countVowelsTest(Long expected, TextComponent component) throws InfoHandlingException {
	    Long actual = service.countVowels(component);
	    assertEquals(actual, expected);
	}
	  
	@Test
	public void findLongestWordSentencesTest() throws InfoHandlingException {
		TextComponent component = textParser.parse("Sentence1. Seeentence3? Seeentence2!");
		List<TextComponent> expected = List.of(sentenceParser.parse("Seeentence3?"), sentenceParser.parse("Seeentence2!"));
		List<TextComponent> actual = service.findLongestWordSentences(component);
		assertEquals(actual, expected);
	}
	    
	@Test
	public void sortParagraphsBySentenceQuantityTest() throws InfoHandlingException {
		String result = "\tSentence1?\n\tSentence1. Sentence2!\n\tSentence1. Sentence2. Sentence3\n";
	    String text = "\tSentence1. Sentence2!\n\tSentence1?\n\tSentence1. Sentence2. Sentence3\n";
	    TextComponent expected = textParser.parse(result);
	    TextComponent actual = textParser.parse(text);
	    service.sortParagraphsBySentenceQuantity(actual);
	    assertEquals(actual, expected);
	}

	@Test
	public void removeSentencesWordsLessThanTest() throws InfoHandlingException {
		TextComponent actual = textParser.parse("Sentence1 qw fg. Seentence3 re. Seentence2 rty tyu.");
		TextComponent expected = textParser.parse("Sentence1 qw fg. Seentence2 rty tyu.");
		service.removeSentencesWordsLessThan(actual, 3);
		assertEquals(actual, expected);
	}

	@Test
	public void findCountIdenticalWordsTest() throws InfoHandlingException {
		TextComponent textComponent = textParser.parse("Fisrt sentence with some words. And one more sentence with info! And third one with words.");
		long actual = service.findCountIdenticalWords(textComponent);
		assertEquals(actual, 5L);
	}
}
