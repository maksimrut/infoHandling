package com.rutkouski.infohandling.parser;

import com.rutkouski.infohandling.composite.TextComponent;
import com.rutkouski.infohandling.exception.InfoHandlingException;

public interface InformationParser { 
	
	TextComponent parse(String text) throws InfoHandlingException;
}
