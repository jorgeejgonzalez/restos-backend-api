package org.idisoft.restos.data.repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.idisoft.restos.model.Registro;
import org.idisoft.restos.model.jpa.BeanValidator;

public abstract class Repository<T extends Registro> {
	
	protected Class<T> entityclass;
	
	protected DataAccessObject<T> dataaccessobject;
	protected BeanValidator<T> beanvalidator;
	protected CriteriaBuilder criteriabuilder;
	protected CriteriaQuery<T> criteriaquery;
	
	public Repository()
	{
	}
	
	public Repository(final DataAccessObject<T> dataaccessobject, BeanValidator<T> beanvalidator)
	{
		this.dataaccessobject=dataaccessobject;
		this.criteriabuilder=dataaccessobject.getCriteriaBuilder();
		this.criteriaquery=dataaccessobject.getCriteriaQuery();
		
		this.beanvalidator=beanvalidator;
	}
	
	protected TypedQuery<T> assembleTypedQuery(final CriteriaQuery<T> query,
			final Root<T> root, 
			final Predicate condition)
	{
		CriteriaQuery<T> criteria =query.select(root);
		criteria.where(condition);
		
		TypedQuery<T> typedquery=dataaccessobject.getTypedQuery(criteria);
		
		return typedquery;
	}
	
	protected boolean isValidEntity(final T entity)
	{
		beanvalidator.validate(entity);
		return beanvalidator.isValid();
	}

}
