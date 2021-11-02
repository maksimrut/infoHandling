package com.rutkouski.infohandling.composite.impl;

import java.util.ArrayList;
import java.util.List;

import com.rutkouski.infohandling.composite.TextComponent;
import com.rutkouski.infohandling.composite.TypeEnum;

public class SymbolLeaf implements TextComponent {
	
	private TypeEnum type = TypeEnum.SYMBOL;
	private String symbol;
	
	public SymbolLeaf(String symbol) {
		this.symbol = symbol;
	}
	
	@Override
	public boolean add(TextComponent component) {
		return false;
	}

	@Override
	public boolean remove(TextComponent component) {
		return false;
	}

	@Override
	public List<TextComponent> getComponents() {
		ArrayList<TextComponent> list = new ArrayList<>();
		list.add(this);
		return list;
	}

	@Override
	public int getComponentsSize() {
		throw new UnsupportedOperationException();
	}

	@Override
	public TypeEnum getType() {
		return type;
	}

	@Override
	public String toStringRepresentation() {
		return symbol;
	}
}
