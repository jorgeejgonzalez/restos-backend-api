package org.idisoft.restos.data.repository;

import org.idisoft.restos.data.factory.ModelFactory;
import org.idisoft.restos.model.Registro;
import org.idisoft.restos.model.jpa.BeanValidator;

public abstract class Repository<T extends Registro> {
	
	protected Class<T> entityclass;
	
	protected DataAccessObject<T> dataAccessObject;
	protected BeanValidator<T> beanValidator;
	protected ModelFactory<T> entityFactory;
	
	public Repository()
	{
	}
	
	public Repository(final DataAccessObject<T> dataaccessobject, 
			final BeanValidator<T> beanvalidator,
			final ModelFactory<T> entityFactory)
	{
		this.dataAccessObject=dataaccessobject;
		this.beanValidator=beanvalidator;
		this.entityFactory=entityFactory;
	}
	
	protected boolean isValidEntity(final T entity)
	{
		beanValidator.validate(entityFactory.copyEntity(entity));
		return beanValidator.isValid();
	}

}
