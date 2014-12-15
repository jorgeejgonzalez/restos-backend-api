package org.idisoft.restos.data.repository;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

@Dependent
public class DataAccessObject<T> {
	
	@Inject
	private EntityManager entitymanager;
	
	public DataAccessObject()
	{
		
	}
	
	public DataAccessObject(EntityManager entitymanager)
	{
		this.entitymanager=entitymanager;
	}
	
	public T getSingleResult(CriteriaQuery<T> query) throws NoResultException
	{
		TypedQuery<T> typedquery=entitymanager.createQuery(query);
		return typedquery.getSingleResult();
	}

	
}
