package com.rutkouski.infohandling.interpreter.impl;

import com.rutkouski.infohandling.interpreter.AbstractMathExpression;
import com.rutkouski.infohandling.interpreter.Context;

public class TerminalExpressionXor implements AbstractMathExpression {
	
	@Override
	public void interpret(Context context) {
		context.pushValue((context.popValue() ^ context.popValue()));
	}
}
