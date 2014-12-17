package org.idisoft.restos.data.repository;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public abstract class Repository<T> {
	
	protected Class<T> entityclass;
	
	@Inject
	protected DataAccessObject<T> dataaccessobject;
	
	protected CriteriaBuilder criteriabuilder;
	protected CriteriaQuery<T> criteriaquery;
	protected Root<T> criteriaroot;
	
	protected void setUpCriteriaElements()
	{
		criteriabuilder=dataaccessobject.getCriteriaBuilder();
		criteriaquery=dataaccessobject.getCriteriaQuery();
		criteriaroot=criteriaquery.from(entityclass);
	}
	
	protected TypedQuery<T> assembleTypedQuery(Predicate condition)
	{
		criteriaquery.select(criteriaroot);
		criteriaquery.where(condition);
		
		TypedQuery<T> typedquery=dataaccessobject.getTypedQuery(criteriaquery);
		
		return typedquery;
	}

}
