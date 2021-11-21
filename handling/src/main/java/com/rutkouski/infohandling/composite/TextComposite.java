package com.rutkouski.infohandling.composite;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import com.rutkouski.infohandling.exception.InfoHandlingException;

public class TextComposite implements TextComponent {

	private static final String TABULATION_REGEX = "\t";
	private ArrayList<TextComponent> list;
	private TypeEnum type;
	
	public TextComposite(TypeEnum type) throws InfoHandlingException {
		EnumSet<TypeEnum> possibleTypes = EnumSet.range(TypeEnum.TEXT, TypeEnum.EXPRESSION);
		if (!possibleTypes.contains(type)) {
			throw new InfoHandlingException("Illegal text composite type");
		}
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
	public TypeEnum getType() {
		return type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((list == null) ? 0 : list.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		TextComposite other = (TextComposite) obj;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (type == TypeEnum.TEXT) {
            builder.append(TABULATION_REGEX);
        }
        String delimiter = type.getDelimeter();
		
		for (TextComponent component : list) {
			builder.append(component.toString()).append(delimiter);
		}
		return builder.toString();
	}
}
