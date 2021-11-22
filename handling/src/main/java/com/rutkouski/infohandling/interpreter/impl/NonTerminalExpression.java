package com.rutkouski.infohandling.interpreter.impl;

import com.rutkouski.infohandling.interpreter.AbstractMathExpression;
import com.rutkouski.infohandling.interpreter.Context;

public class NonTerminalExpression implements AbstractMathExpression {
	
	private int number;
	
	public NonTerminalExpression(int number) {
		this.number = number;
	}
	
	@Override
	public void interpret(Context context) {
		context.pushValue(number);
	}
}
