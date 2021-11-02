package com.rutkouski.infohandling.composite.impl;

import java.util.ArrayList;
import java.util.List;

import com.rutkouski.infohandling.composite.TextComponent;
import com.rutkouski.infohandling.composite.TypeEnum;

public class TextComposite implements TextComponent {

	private ArrayList<TextComponent> list;
	private TypeEnum type;
	
	public TextComposite(TypeEnum type) {
		list = new ArrayList<>();
		this.type = type;
	}
	
	@Override
	public boolean add(TextComponent component) {
		return list.add(component);
	}

	@Override
	public boolean remove(TextComponent component) {
		return list.remove(component);
	}

	@Override
	public List<TextComponent> getComponents() {
		return list;
	}

	@Override
	public int getComponentsSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TypeEnum getType() {
		return type;
	}

	@Override
	public String toStringRepresentation() {

		StringBuilder sb = new StringBuilder();
		for (TextComponent component : list) {
			sb.append(component.toStringRepresentation());
		}
		return sb.toString();
	}
}
