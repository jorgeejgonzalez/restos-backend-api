package org.idisoft.restos.administracionusuarios;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import javax.validation.ValidationException;

import org.apache.http.auth.AuthenticationException;

public interface AdministradorUsuarios {
	
	public Usuario auntenticarUsuario(final String login, final String password) 
	throws NoResultException,ValidationException,AuthenticationException;
	
	public Usuario registrarUsuario(final Usuario usuario)
	throws EntityExistsException, ValidationException;

}
