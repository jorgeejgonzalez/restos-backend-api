package org.idisoft.restos.data.repository;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

@Dependent
public class DataAccessObject<T> {

	private final EntityManager entitymanager;
	
	private Class<T> entityclass;
	
	@Inject
	public DataAccessObject(final EntityManager entitymanager)
	{
		this.entitymanager=entitymanager;
	}
	
	public CriteriaBuilder getCriteriaBuilder()
	{
		return entitymanager.getCriteriaBuilder();
	}
	
	public CriteriaQuery<T> getCriteriaQuery()
	{
		return entitymanager.getCriteriaBuilder().createQuery(entityclass);
	}
	
	public TypedQuery<T> getTypedQuery(final CriteriaQuery<T> criteriaquery) throws NoResultException
	{
		return entitymanager.createQuery(criteriaquery);
	}

	
}
