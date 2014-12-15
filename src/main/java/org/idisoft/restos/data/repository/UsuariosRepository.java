package org.idisoft.restos.data.repository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.idisoft.restos.model.Usuario;
import org.idisoft.restos.model.jpa.ConstantesORM;
import org.idisoft.restos.model.jpa.UsuarioJPA;

@RequestScoped
public class UsuariosRepository extends Repository<Usuario> {
	
	@Inject
	private CriteriaBuilder criteriabuilder;
	
	@Inject
	private DataAccessObject<UsuarioJPA> daousuariojpa;
	
	public UsuariosRepository()
	{
		
	}
	
	public UsuariosRepository(CriteriaBuilder cb, DataAccessObject<UsuarioJPA> dao)
	{
		this.criteriabuilder=cb;
		this.daousuariojpa=dao;
	}
	
	public Usuario findByLogin(String login) throws NoResultException, IllegalArgumentException
	{
		if(login==null || login.isEmpty())
		{
			throw new IllegalArgumentException();
		}
		
		CriteriaQuery<UsuarioJPA> criteria=criteriabuilder.createQuery(UsuarioJPA.class);		
		Root<UsuarioJPA> root=criteria.from(UsuarioJPA.class);
		Predicate condition=criteriabuilder.equal(
				root.get(ConstantesORM.USUARIO_LOGIN_ATTRIBUTE_NAME),
				login);
		
		criteria=criteria.select(root);
		criteria=criteria.where(condition);
		
		UsuarioJPA retorno=daousuariojpa.getSingleResult(criteria);
		
		return retorno;
	}
}
