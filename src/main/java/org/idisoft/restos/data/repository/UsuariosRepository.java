package org.idisoft.restos.data.repository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.idisoft.restos.model.Usuario;
import org.idisoft.restos.model.jpa.ConstantesORM;
import org.idisoft.restos.model.jpa.UsuarioJPA;

@RequestScoped
public class UsuariosRepository extends Repository<UsuarioJPA> {
	
	public UsuariosRepository()
	{
		this.entityclass=UsuarioJPA.class;
	}
	
	@Inject
	public UsuariosRepository(final DataAccessObject<UsuarioJPA> daousuariojpa)
	{
		super(daousuariojpa);
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
}
