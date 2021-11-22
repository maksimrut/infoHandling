package com.rutkouski.infohandling.interpreter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rutkouski.infohandling.exception.InfoHandlingException;
import com.rutkouski.infohandling.interpreter.impl.NonTerminalExpression;
import com.rutkouski.infohandling.interpreter.impl.TerminalExpressionAnd;
import com.rutkouski.infohandling.interpreter.impl.TerminalExpressionLeftShift;
import com.rutkouski.infohandling.interpreter.impl.TerminalExpressionNot;
import com.rutkouski.infohandling.interpreter.impl.TerminalExpressionOr;
import com.rutkouski.infohandling.interpreter.impl.TerminalExpressionRightShift;
import com.rutkouski.infohandling.interpreter.impl.TerminalExpressionXor;

public class ExpressionInterpreter {
	
	static Logger logger = LogManager.getLogger();
    private static final String INFIX_EXPRESSION_SPLIT_REGEX =
            "(?=>{3}|((?<!>)>{2}(?!>))|<{2}|\\^|\\||&|~|\\(|\\)|((?<!\\d)\\d+(?!\\d))(?<=\\d))";
    private static final String NUMBER_REGEX = "\\d+";
    
    private ArrayList<AbstractMathExpression> listExpression;

    public ExpressionInterpreter(String expression) throws InfoHandlingException {
        listExpression = new ArrayList<>();
        parse(expression);
    }

    public Integer calculate() {
        Context context = new Context();
        for (AbstractMathExpression terminal : listExpression) {
        	terminal.interpret(context);
        }
        logger.info("Expression calculated successfully");
        return context.popValue();
    }

    private void parse(String expression) throws InfoHandlingException {
        String[] elementsArray = expression.split(INFIX_EXPRESSION_SPLIT_REGEX);
        List<String> resultForm = convertToResultForm(elementsArray);
        
        for (String lexeme : resultForm) {
            switch (lexeme) {
                case "^":
                    listExpression.add(new TerminalExpressionXor());
                    break;
                case "|":
                    listExpression.add(new TerminalExpressionOr());
                    break;
                case "&":
                    listExpression.add(new TerminalExpressionAnd());
                    break;
                case ">>":
                    listExpression.add(new TerminalExpressionRightShift());
                    break;
                case "<<":
                    listExpression.add(new TerminalExpressionLeftShift());
                    break;
                case "~":
                    listExpression.add(new TerminalExpressionNot());
                    break;
                default:
                    try {
                        listExpression.add(new NonTerminalExpression(Integer.parseInt(lexeme)));
                    } catch (NumberFormatException e) {
                        logger.error("Invalid expression {}", expression);
                        throw new InfoHandlingException("Invalid expression " + expression);
                    }
            }
        }
        logger.info("Expression parsed successfully");
    }

    private List<String> convertToResultForm(String[] elementsArray) throws InfoHandlingException {
        List<String> result = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();
        for (String element : elementsArray) {
            if (element.matches(NUMBER_REGEX)) {
                result.add(element);
            } else if (element.equals(ExpressionOperation.OPEN_BRACKET.toString())) {
                stack.push(ExpressionOperation.OPEN_BRACKET.toString());
            } else if (element.equals(ExpressionOperation.CLOSE_BRACKET.toString())) {
                while (!stack.isEmpty() && !stack.peek().equals(ExpressionOperation.OPEN_BRACKET.toString())) {
                    result.add(stack.pop());
                }
                stack.pop();
            } else {
                try {
                    int currentElementPrecedence = ExpressionOperation.getOperationByString(element).getPrecedence();
                    while (!stack.isEmpty()
                            && ExpressionOperation.getOperationByString(stack.peek()).getPrecedence() <= currentElementPrecedence) {
                        result.add(stack.pop());
                    }
                    stack.push(element);
                } catch (IllegalArgumentException e) {
                    logger.error("Invalid expression");
                    throw new InfoHandlingException("Invalid expression");
                }
            }
        }
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        logger.info("Expression successfully converted");
        return result;
    }
}
