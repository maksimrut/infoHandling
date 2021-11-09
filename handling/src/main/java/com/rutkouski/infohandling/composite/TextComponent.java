package com.rutkouski.infohandling.composite;

import java.util.List;

public interface TextComponent {
	
	boolean add(TextComponent component);
	
	boolean remove(TextComponent component);
	
	List<TextComponent> getComponents();
	
	TypeEnum getType();
	
	String toStringRepresentation();
	
}
