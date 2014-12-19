package org.idisoft.restos.data.factory.jpa;

import org.idisoft.restos.data.factory.ModelFactory;

public abstract class JPAFactory<T> extends ModelFactory<T> {
	
	public abstract T copyEntity(final T original);
}
