package com.rutkouski.infohandling.composite;

import java.util.List;

public interface TextComponent {
	
	boolean add(TextComponent component);
	
	boolean remove(TextComponent component);
	
	List<TextComponent> getComponents();
	
	int getComponentsSize();
	
	TypeEnum getType();
	
	String toStringRepresentation();
}
