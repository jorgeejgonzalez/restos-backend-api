package org.idisoft.restos.data.factory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Factory<T> {
	
	public abstract T createEntity();
	
	public List<T> createList()
	{
		return new ArrayList<T>();
	}
	
	public Set<T> createSet()
	{
		return new HashSet<T>();
	}

}
