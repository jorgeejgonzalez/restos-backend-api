package org.idisoft.restos.util;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.idisoft.restos.model.jpa.UsuarioJPA;

public class CDIPersistenceResources {
	
	@Produces
    @PersistenceContext
    private EntityManager entitymanager;
	
	@Produces
	public CriteriaBuilder getCriteriaBuilder()
	{
		return entitymanager.getCriteriaBuilder();
	}
	
	@Produces
	public CriteriaQuery<UsuarioJPA> getCriteriaUsuarioJPA()
	{
		return entitymanager.getCriteriaBuilder().createQuery(UsuarioJPA.class);		
	}
	
	@Produces
	public Root<UsuarioJPA> getRootUsuarioJPA()
	{
		return entitymanager.getCriteriaBuilder().createQuery(UsuarioJPA.class).from(UsuarioJPA.class);
	}

}
