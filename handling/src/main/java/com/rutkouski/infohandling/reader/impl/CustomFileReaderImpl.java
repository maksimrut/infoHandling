package com.rutkouski.infohandling.reader.impl;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rutkouski.infohandling.exception.InfoHandlingException;
import com.rutkouski.infohandling.reader.CustomFileReader;

public class CustomFileReaderImpl implements CustomFileReader {
	
	static Logger logger = LogManager.getLogger();
	
	@Override
	public String readText(String filePath) throws InfoHandlingException {
		
		if (filePath == null || Files.notExists(Paths.get(filePath)) 
				|| filePath.length() == 0) {
			logger.error("File is not found " + filePath);
			throw new InfoHandlingException("File is not found " + filePath);
		}
		ClassLoader loader = getClass().getClassLoader();
		URL resourse = loader.getResource(filePath);
		String path = new File(resourse.getFile()).getAbsolutePath();
		
		List<String> stringlines;
		
		try {
			stringlines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
		} catch (IOException e) {
			logger.error("Failed or interrupted I/O operations ", e);
			throw new InfoHandlingException("Failed or interrupted I/O operations", e);
		}
		StringBuilder lines = new StringBuilder();
		for (String line : stringlines) {
			lines.append(line);
		}
		logger.info("Reading file is successful " + filePath);
		return lines.toString();
	}
}
