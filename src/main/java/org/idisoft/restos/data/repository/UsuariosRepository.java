package org.idisoft.restos.data.repository;

import javax.enterprise.context.RequestScoped;
import javax.persistence.NoResultException;
import javax.persistence.criteria.Predicate;

import org.idisoft.restos.model.Usuario;
import org.idisoft.restos.model.jpa.ConstantesORM;
import org.idisoft.restos.model.jpa.UsuarioJPA;

@RequestScoped
public class UsuariosRepository extends Repository<UsuarioJPA> {
	
	public UsuariosRepository()
	{
		this.entityclass=UsuarioJPA.class;
	}
	
	public UsuariosRepository(DataAccessObject<UsuarioJPA> daousuariojpa)
	{
		this.entityclass=UsuarioJPA.class;
		this.dataaccessobject=daousuariojpa;
	}
	
	public Usuario findByLogin(String login) throws NoResultException, IllegalArgumentException
	{
		if(login==null || login.isEmpty())
		{
			throw new IllegalArgumentException();
		}
		
		setUpCriteriaElements();
		
		Predicate condition=criteriabuilder.equal(
				criteriaroot.get(ConstantesORM.USUARIO_LOGIN_ATTRIBUTE_NAME),
				login);
		
		UsuarioJPA retorno=assembleTypedQuery(condition).getSingleResult();
		
		return retorno;
	}
}
