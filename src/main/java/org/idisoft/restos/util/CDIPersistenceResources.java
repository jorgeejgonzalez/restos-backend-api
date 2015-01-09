package org.idisoft.restos.util;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ValidatorFactory;

import org.idisoft.restos.data.repository.DataAccessObject;
import org.idisoft.restos.model.jpa.BeanValidator;
import org.idisoft.restos.model.jpa.UsuarioJPA;

public class CDIPersistenceResources {
	
	@Produces
    @PersistenceContext
    private EntityManager entitymanager;
	
	@Produces
	private ValidatorFactory validatorFactory;
	
	
	
}
