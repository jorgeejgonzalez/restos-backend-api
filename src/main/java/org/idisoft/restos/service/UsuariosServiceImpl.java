package org.idisoft.restos.service;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.idisoft.restos.data.factory.dto.UsuarioDTOFactory;
import org.idisoft.restos.data.repository.UsuariosRepository;
import org.idisoft.restos.model.Usuario;

public class UsuariosServiceImpl implements UsuariosService {
	
	@Inject
	private UsuariosRepository usuariosrepository;
	
	@Inject
	private UsuarioDTOFactory usuariodtofactory;

	@Override
	public Response authenticateUser(String login, String password) {
		
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
				usuario=usuariodtofactory.createEntity(usuario);
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

}
