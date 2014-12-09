package org.idisoft.restos.data.repository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

@Stateless
public class DataAccessObject<T> {
	
	@Inject
	private EntityManager entitymanager;
	
	public T querySingle(CriteriaQuery<T> criteria) throws NoResultException
	{
		TypedQuery<T> query=entitymanager.createQuery(criteria);
		return query.getSingleResult();
	}

}
