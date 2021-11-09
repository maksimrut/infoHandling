package com.rutkouski.infohandling.reader.impl;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.rutkouski.infohandling.exception.InfoHandlingException;

public class CustomFileReaderImplTest {
	CustomFileReaderImpl lineFileReaderImpl = new CustomFileReaderImpl();
	String file = "file.txt";
  @Test
  public void readTextTest() throws InfoHandlingException {
	  
	 String actual = lineFileReaderImpl.readText(file);
	  String expected = "123 tre dfg 456";
	  assertEquals(actual, expected);
  }
}
