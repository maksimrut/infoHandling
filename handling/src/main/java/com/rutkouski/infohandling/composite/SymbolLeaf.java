package com.rutkouski.infohandling.composite;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SymbolLeaf implements TextComponent {
	
	static Logger logger = LogManager.getLogger();
	private TypeEnum type = TypeEnum.SYMBOL;
	private char symbol;
	
	public SymbolLeaf(char symbol) {
		this.symbol = symbol;
	}
	
	@Override
	public boolean add(TextComponent component) {
		logger.error("Unsupported operation 'add' on symbol");
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(TextComponent component) {
		logger.error("Unsupported operation 'remove' on symbol");
		throw new UnsupportedOperationException();
	}

	@Override
	public List<TextComponent> getComponents() {
		ArrayList<TextComponent> list = new ArrayList<>();
		list.add(this);
		return list;
	}

	@Override
	public TypeEnum getType() {
		return type;
	}

	@Override
	public String toStringRepresentation() {
		return String.valueOf(symbol);
	}

}
