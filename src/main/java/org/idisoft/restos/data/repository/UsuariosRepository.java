package org.idisoft.restos.data.repository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.idisoft.restos.data.factory.jpa.UsuarioJPAFactory;
import org.idisoft.restos.model.Usuario;
import org.idisoft.restos.model.jpa.ConstantesORM;
import org.idisoft.restos.model.jpa.UsuarioJPA;

@RequestScoped
public class UsuariosRepository extends Repository<UsuarioJPA> {
	
	UsuarioJPAFactory factory;
	
	public UsuariosRepository()
	{
		this.entityclass=UsuarioJPA.class;
	}
	
	@Inject
	public UsuariosRepository(final DataAccessObject<UsuarioJPA> daousuariojpa,
			final UsuarioJPAFactory factory)
	{
		super(daousuariojpa);
		this.factory=factory;
		this.entityclass=UsuarioJPA.class;
	}
	
	public Usuario findByLogin(final String login) throws NoResultException, IllegalArgumentException
	{
		if(login==null || login.isEmpty())
		{
			throw new IllegalArgumentException();
		}
		
		Root<UsuarioJPA> criteriaroot=criteriaquery.from(entityclass);
		
		Predicate criteriacondition=criteriabuilder.equal(
				criteriaroot.get(ConstantesORM.USUARIO_LOGIN_ATTRIBUTE_NAME),
				login);
		
		TypedQuery<UsuarioJPA> typedquery=assembleTypedQuery(criteriaquery, criteriaroot, criteriacondition);
		
		UsuarioJPA retorno=typedquery.getSingleResult();
		
		return retorno;
	}
	
	public Usuario add(final Usuario usuario) throws EntityExistsException
	{	
		UsuarioJPA entity=(UsuarioJPA)factory.copyEntity(usuario);
		Usuario retorno=dataaccessobject.persist(entity);
		return retorno;
	}
}
