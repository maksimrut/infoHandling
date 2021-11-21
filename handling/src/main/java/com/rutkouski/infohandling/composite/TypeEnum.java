package com.rutkouski.infohandling.composite;

public enum TypeEnum {
	
	TEXT("\n\t"),
	PARAGRAPH(" "),
	SENTENCE(" "),
	LEXEME(""),
	WORD(""),
	EXPRESSION(""),
	SYMBOL(""),
	PUNCTUATION(""),
	MATH_SIGN("");
	
	private final String delimeter;
	
	TypeEnum(String delimeter) {
		this.delimeter = delimeter;
	}
	
	public String getDelimeter() {
		return delimeter;
	}
}
