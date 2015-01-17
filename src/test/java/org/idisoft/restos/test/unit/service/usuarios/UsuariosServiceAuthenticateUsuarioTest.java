package org.idisoft.restos.test.unit.service.usuarios;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.persistence.NoResultException;
import javax.ws.rs.core.Response;

import org.idisoft.restos.administracionusuarios.Usuario;
import org.idisoft.restos.administracionusuarios.business.UsuarioDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UsuariosServiceAuthenticateUsuarioTest extends AbstractUsuarioServiceTest
{
	
	@Before
	public void setUpMockitoRules()
	{
		setUpEntities();
		
		when(usuariosrepository.findByLogin(loginInDataset)).thenReturn(validUsuario);
		when(usuariosrepository.findByLogin(loginNotInDataset)).thenThrow(new NoResultException());
		when(usuariosrepository.findByLogin(loginEmpty)).thenThrow(new IllegalArgumentException());
		when(usuariosrepository.findByLogin(loginNull)).thenThrow(new IllegalArgumentException());
		
		setUpService();
	}
	
	
	@Test
	public void AuthenticateUser_LoginAndPasswordMatch_ResponseOK()
	{
		Response response=usuarioService.authenticateUsuario(loginInDataset, passwordInDataset);
		assertOK(response);
	}
	
	@Test
	public void AuthenticateUser_LoginAndPasswordMatch_EntityUsuarioDTO()
	{
		Response response=usuarioService.authenticateUsuario(loginInDataset, passwordInDataset);
		Usuario entity=(Usuario)response.getEntity();
		assertTrue(entity instanceof UsuarioDTO);
	}
	
	@Test
	public void AuthenticateUser_LoginIsNotInDatabase_ResponseNotFound()
	{
		Response response=usuarioService.authenticateUsuario(loginNotInDataset, passwordNotInDataset);
		assertNotFound(response);
	}
	
	@Test
	public void AuthenticateUser_LoginAndPassworDoNotMatch_ResponseUnauthorized()
	{
		Response response=usuarioService.authenticateUsuario(loginInDataset, passwordNotInDataset);
		assertUnauthorized(response);
	}
	
	@Test
	public void AuthenticateUser_LoginIsNull_ResponseNotAcceptable()
	{
		Response response=usuarioService.authenticateUsuario(loginNull, passwordInDataset);
		assertNotAcceptable(response);
	}
	
	@Test
	public void AuthenticateUser_LoginIsEmpty_ResponseNotAcceptable()
	{
		Response response=usuarioService.authenticateUsuario(loginEmpty, passwordInDataset);
		assertNotAcceptable(response);
	}
	
	@Test
	public void AuthenticateUser_PasswordIsNull_ResponseNotAcceptable()
	{
		Response response=usuarioService.authenticateUsuario(loginInDataset, passwordNull);
		assertNotAcceptable(response);
	}
	
	@Test
	public void AuthenticateUser_PasswordIsEmpty_ResponseNotAcceptable()
	{
		Response response=usuarioService.authenticateUsuario(loginInDataset, passwordEmpty);
		assertNotAcceptable(response);
	}

}
