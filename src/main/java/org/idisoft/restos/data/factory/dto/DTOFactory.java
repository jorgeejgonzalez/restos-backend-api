package org.idisoft.restos.data.factory.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.idisoft.restos.data.factory.ModelFactory;

public abstract class DTOFactory<T> implements ModelFactory<T>{
	
	@Override
	public List<T> createList()
	{
		return new ArrayList<T>(0);
	}
	
	@Override
	public Set<T> createSet()
	{
		return new HashSet<T>(0);
	}

}
