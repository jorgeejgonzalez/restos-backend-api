package org.idisoft.restos.util;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;

public class CDIPersistenceResources {
	
	@SuppressWarnings("unused")
    @Produces
    @PersistenceContext
    private EntityManager entitymanager;
	
	@Produces
	public CriteriaBuilder getCriteriaBuilder()
	{
		return entitymanager.getCriteriaBuilder();
	}

}
