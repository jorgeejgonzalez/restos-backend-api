package org.idisoft.restos.administracionusuarios.business;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import javax.validation.ValidationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.http.auth.AuthenticationException;
import org.idisoft.restos.administracionusuarios.AdministradorUsuarios;
import org.idisoft.restos.administracionusuarios.Usuario;
import org.idisoft.restos.administracionusuarios.business.repository.UsuariosRepository;

@RequestScoped
public class AdministradorUsuariosBean implements AdministradorUsuarios {
	
	private UsuarioDTOFactory usuarioDTOsFactory;
	private UsuariosRepository usuariosRepository;
	
	
	public AdministradorUsuariosBean()
	{
		
	}
	
	@Inject
	public AdministradorUsuariosBean(final UsuarioDTOFactory usuarioDTOsFactory,
			final UsuariosRepository usuariosRepository)
	{
		this.usuarioDTOsFactory=usuarioDTOsFactory;
		this.usuariosRepository=usuariosRepository;
	}

	@Override
	public Usuario auntenticarUsuario(String login, String password)
			throws NoResultException, IllegalArgumentException,
			AuthenticationException 
	{
		if(login==null || login.isEmpty() || password==null)
		{
			throw new IllegalArgumentException();
		}
		
		Usuario usuarioParaAutenticar=usuariosRepository.findByLogin(login);
		
		if(!usuarioParaAutenticar.getPassword().equals(password))
		{
			throw new AuthenticationException();
		}
		
		return usuarioDTOsFactory.copyEntity(usuarioParaAutenticar);
	}

	@Override
	public Usuario registrarUsuario(Usuario usuario)
			throws EntityExistsException, ValidationException 
	{
		Usuario usuarioRegistrado=usuariosRepository.add(usuario);
		return usuarioDTOsFactory.copyEntity(usuarioRegistrado);
	}

}
