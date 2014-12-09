package org.idisoft.restos.data.factory.jpa;

import org.idisoft.restos.data.factory.Factory;

public abstract class JPAFactory<T> extends Factory<T> {
	
	@Override
	public abstract T createEntity();

}
