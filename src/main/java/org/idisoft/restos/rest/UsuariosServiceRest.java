package org.idisoft.restos.rest;

import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import javax.validation.ValidationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.idisoft.restos.administracionusuarios.AdministradorUsuarios;
import org.idisoft.restos.administracionusuarios.Usuario;
import org.idisoft.restos.administracionusuarios.business.UsuarioDTOFactory;
import org.idisoft.restos.administracionusuarios.business.repository.UsuariosRepository;

public class UsuariosServiceRest implements UsuariosService {
	
	private AdministradorUsuarios administradorUsuarios;
	
	public UsuariosServiceRest()
	{
		
	}
	
	@Inject
	public UsuariosServiceRest(final AdministradorUsuarios administradorUsuarios)
	{
		this.administradorUsuarios=administradorUsuarios;
	}

	@Override
	public Response authenticateUsuario(final String login, final String password) 
	{
		
	}

	@Override
	public Response registerUsuario(final Usuario usuario) 
	{
		return null;
	}

}
