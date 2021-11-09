package com.rutkouski.infohandling.reader;

import com.rutkouski.infohandling.exception.InfoHandlingException;

public interface CustomFileReader {
	
	String readText(String filePath) throws InfoHandlingException;
}
