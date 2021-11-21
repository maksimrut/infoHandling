package com.rutkouski.infohandling.composite;

import java.util.EnumSet;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rutkouski.infohandling.exception.InfoHandlingException;

public class SymbolLeaf implements TextComponent {
	
	static Logger logger = LogManager.getLogger();
	private TypeEnum type;
	private char symbol;
	
	public SymbolLeaf(TypeEnum type, char symbol) throws InfoHandlingException {
		EnumSet<TypeEnum> possibleTypes = EnumSet.range(TypeEnum.SYMBOL, TypeEnum.MATH_SIGN);
		if (!possibleTypes.contains(type)) {
			throw new InfoHandlingException("Illegal text leaf type");
		}
		this.type = type;
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
		logger.error("Unsupported operation 'getComponents' on symbol");
		throw new UnsupportedOperationException();
	}

	@Override
	public TypeEnum getType() {
		return type;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + symbol;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		SymbolLeaf other = (SymbolLeaf) obj;
		if (symbol != other.symbol) 
			return false;
		if (type != other.type) 
			return false;
		return true;
	}

	@Override
	public String toString() {
		return Character.toString(symbol);
	}
}
