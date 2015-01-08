package org.idisoft.restos.service;

import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import javax.validation.ValidationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.idisoft.restos.data.factory.dto.UsuarioDTOFactory;
import org.idisoft.restos.data.factory.jpa.UsuarioJPAFactory;
import org.idisoft.restos.data.repository.UsuariosRepository;
import org.idisoft.restos.model.Usuario;

public class UsuariosServiceRest implements UsuariosService {
	
	private UsuariosRepository usuariosrepository;
	private UsuarioDTOFactory usuariodtofactory;
	private UsuarioJPAFactory usuariojpafactory;
	
	public UsuariosServiceRest()
	{
		
	}
	
	@Inject
	public UsuariosServiceRest(final UsuariosRepository usuariosrepository,
			final UsuarioDTOFactory usuariodtofactory,
			final UsuarioJPAFactory usuariojpafactory)
	{
		this.usuariosrepository=usuariosrepository;
		this.usuariodtofactory=usuariodtofactory;
		this.usuariojpafactory=usuariojpafactory;
	}

	@Override
	public Response authenticateUsuario(final String login, final String password) 
	{
		Response.ResponseBuilder builder=null;
		
		try
		{
			if(password==null || password.isEmpty())
			{
				throw new IllegalArgumentException();
			}
			
			Usuario usuario=usuariosrepository.findByLogin(login);
			
			if(password.equals(usuario.getPassword()))
			{
				builder=Response.status(Status.OK);
				usuario=usuariodtofactory.copyEntity(usuario);
				builder.entity(usuario);
			}
			else
			{
				builder=Response.status(Status.UNAUTHORIZED);
			}
		}
		catch(IllegalArgumentException ex)
		{
			builder=Response.status(Status.NOT_ACCEPTABLE);
		}
		catch(NoResultException ex)
		{
			builder=Response.status(Status.NOT_FOUND);
		}
		
		builder.type(MediaType.APPLICATION_JSON);
		return builder.build();
	}

	@Override
	public Response registerUsuario(final Usuario usuario) 
	{
		Response.ResponseBuilder builder=null;
		try
		{
			Usuario usuariojpa= usuariojpafactory.copyEntity(usuario);
			
			Usuario usuarioregistro=usuariosrepository.add(usuariojpa);
			
			Usuario usuariodto=usuariodtofactory.copyEntity(usuarioregistro);
			
			builder=Response.status(Status.OK);
			builder.entity(usuariodto);
		}
		catch(ValidationException exception)
		{
			builder=Response.status(Status.NOT_ACCEPTABLE);
		}
		catch(EntityExistsException exception)
		{
			builder=Response.status(Status.CONFLICT);
		}
		
		return builder.build();
	}

}
