package org.idisoft.restos.data.repository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import javax.validation.ValidationException;

import org.idisoft.restos.data.factory.jpa.UsuarioEntityFactory;
import org.idisoft.restos.model.Usuario;
import org.idisoft.restos.model.jpa.BeanValidator;
import org.idisoft.restos.model.jpa.ConstantesORM;
import org.idisoft.restos.model.jpa.UsuarioEntity;

@RequestScoped
public class UsuariosRepository extends Repository<UsuarioEntity> {
	
	
	private UsuarioEntityFactory factory;
	
	public UsuariosRepository()
	{
		this.entityclass=UsuarioEntity.class;
	}
	
	@Inject
	public UsuariosRepository(final DataAccessObject<UsuarioEntity> daousuariojpa,
			final BeanValidator<UsuarioEntity> beanvalidator,
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
