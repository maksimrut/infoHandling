package com.rutkouski.infohandling.reader.impl;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.rutkouski.infohandling.exception.InfoHandlingException;

public class CustomFileReaderImplTest {
	private CustomFileReaderImpl reader = new CustomFileReaderImpl();
	private String file = "readerTest.txt";
	private String notExists = "doesntExist.txt.txt";
	
  @Test
  public void readTextTest() throws InfoHandlingException {
	  
	  String actual = reader.readText(file);
	  String expected = "123 tre dfg 456";
	  assertEquals(actual, expected);
  }
  
  @Test (expectedExceptions = InfoHandlingException.class)
	public void CustomFileReaderNotExistsTest() throws InfoHandlingException{
	  reader.readText(notExists);
	}
  
  @Test (expectedExceptions = InfoHandlingException.class)
	public void CustomFileReaderNullTest() throws InfoHandlingException{
	  reader.readText(null);
	}
}
