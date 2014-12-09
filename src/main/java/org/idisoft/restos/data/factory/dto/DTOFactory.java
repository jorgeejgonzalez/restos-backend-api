package org.idisoft.restos.data.factory.dto;

import java.util.List;
import java.util.Set;

import org.idisoft.restos.data.factory.ModelFactory;

public abstract class DTOFactory<T> extends ModelFactory<T>{
	
	public abstract T createEntity(T entity);
	public abstract T createEntityFromParent();
	public abstract T createEntityFromChild();
	public abstract List<T> createListFromParent();
	public abstract List<T> createListFromChild();
	public abstract Set<T> createSetFromParent();
	public abstract Set<T> createSetFromChild();

}
