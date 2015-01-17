package org.idisoft.restos.administracionusuarios.business.repository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import javax.validation.ValidationException;

import org.idisoft.restos.administracionusuarios.Usuario;
import org.idisoft.restos.data.ConstantesORM;
import org.idisoft.restos.data.EntityValidator;
import org.idisoft.restos.data.Repository;
import org.idisoft.restos.data.DataAccessObject;
import org.idisoft.restos.data.DataAccessObject.Filter;

@RequestScoped
public class UsuariosRepository extends Repository<UsuarioEntity> {
	
	
	private UsuarioEntityFactory factory;
	
	public UsuariosRepository()
	{
		this.entityclass=UsuarioEntity.class;
	}
	
	@Inject
	public UsuariosRepository(final DataAccessObject<UsuarioEntity> daousuariojpa,
			final EntityValidator<UsuarioEntity> beanvalidator,
			final UsuarioEntityFactory entityfactory)
	{
		super(daousuariojpa,beanvalidator, entityfactory);		
		this.entityclass=UsuarioEntity.class;
		this.dataAccessObject.setEntityClass(UsuarioEntity.class);
		this.factory=entityfactory;
	}
	
	public Usuario findByCedula(final String cedula) throws NoResultException
	{
		Usuario retorno=dataAccessObject.findByStringKey(cedula);
		return retorno;
	}
	
	public Usuario findByLogin(final String login) throws NoResultException, IllegalArgumentException
	{
		if(login==null || login.isEmpty())
		{
			throw new IllegalArgumentException();
		}
		
		DataAccessObject<UsuarioEntity>.Filter loginfilter=dataAccessObject.createFilter(
				ConstantesORM.USUARIO_LOGIN_ATTRIBUTE_NAME, 
				login);
		
		UsuarioEntity retorno=dataAccessObject.findSingle(loginfilter);
		
		return retorno;
	}
	
	public Usuario add(final Usuario usuario) throws ValidationException, EntityExistsException
	{	
		UsuarioEntity entity=factory.copyEntity(usuario);
		
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
			
		Usuario retorno=dataAccessObject.persist(entity);
		return retorno;
	}
}
