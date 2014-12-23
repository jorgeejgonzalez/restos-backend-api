package org.idisoft.restos.data.repository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import javax.validation.ValidationException;

import org.idisoft.restos.data.factory.jpa.UsuarioJPAFactory;
import org.idisoft.restos.model.Usuario;
import org.idisoft.restos.model.jpa.BeanValidator;
import org.idisoft.restos.model.jpa.ConstantesORM;
import org.idisoft.restos.model.jpa.UsuarioJPA;

@RequestScoped
public class UsuariosRepository extends Repository<UsuarioJPA> {
	
	private UsuarioJPAFactory factory;
	
	public UsuariosRepository()
	{
		this.entityclass=UsuarioJPA.class;
	}
	
	@Inject
	public UsuariosRepository(final DataAccessObject<UsuarioJPA> daousuariojpa,
			final BeanValidator<UsuarioJPA> beanvalidator,
			final UsuarioJPAFactory factory)
	{
		super(daousuariojpa,beanvalidator);
		this.factory=factory;
		this.entityclass=UsuarioJPA.class;
		this.dataaccessobject.setEntityClass(UsuarioJPA.class);
	}
	
	public Usuario findByCedula(final String cedula) throws NoResultException
	{
		Usuario retorno=dataaccessobject.findByStringKey(cedula);
		return retorno;
	}
	
	public Usuario findByLogin(final String login) throws NoResultException, IllegalArgumentException
	{
		if(login==null || login.isEmpty())
		{
			throw new IllegalArgumentException();
		}
		
		DataAccessObject<UsuarioJPA>.Filter loginfilter=dataaccessobject.createFilter(
				ConstantesORM.USUARIO_LOGIN_ATTRIBUTE_NAME, 
				login);
		
		UsuarioJPA retorno=dataaccessobject.findSingle(loginfilter);
		
		return retorno;
	}
	
	public Usuario add(final Usuario usuario) throws EntityExistsException
	{	
		UsuarioJPA entity=(UsuarioJPA)factory.copyEntity(usuario);
		
		if(!isValidEntity(entity))
		{
			throw new ValidationException();
		}
		try
		{
			findByLogin(entity.getLogin());
			throw new EntityExistsException();
		}
		catch(NoResultException ex)
		{
			//do nothing
		}
		try
		{
			findByCedula(entity.getCedula());
			throw new EntityExistsException();
		}
		catch(NoResultException ex)
		{
			//do nothing
		}
			
		Usuario retorno=dataaccessobject.persist(entity);
		return retorno;
	}
}
