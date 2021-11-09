package com.rutkouski.infohandling.parser;

import com.rutkouski.infohandling.composite.TextComponent;

public interface TextParser { 
	
	void parse(String text, TextComponent component);
	
	default void nextChainLink(String text, TextComponent component, TextParser nextParser) {
		nextParser.parse(text, component);
	}
}
