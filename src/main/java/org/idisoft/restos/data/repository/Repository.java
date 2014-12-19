package org.idisoft.restos.data.repository;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public abstract class Repository<T> {
	
	protected Class<T> entityclass;
	
	protected DataAccessObject<T> dataaccessobject;
	
	protected CriteriaBuilder criteriabuilder;
	protected CriteriaQuery<T> criteriaquery;
	
	public Repository()
	{
	}
	
	@Inject
	public Repository(final DataAccessObject<T> dataaccessobject)
	{
		this.dataaccessobject=dataaccessobject;
		this.criteriabuilder=dataaccessobject.getCriteriaBuilder();
		this.criteriaquery=dataaccessobject.getCriteriaQuery();
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

}
