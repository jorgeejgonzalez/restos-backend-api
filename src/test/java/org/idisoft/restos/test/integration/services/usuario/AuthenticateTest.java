package org.idisoft.restos.test.integration.services.usuario;

import static org.junit.Assert.*;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.idisoft.restos.model.Usuario;
import org.idisoft.restos.model.dto.UsuarioDTO;
import org.idisoft.restos.service.UsuariosService;
import org.idisoft.restos.test.integration.services.AbstractRestServiceTest;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
@UsingDataSet("data/usuarios.json")
public class AuthenticateTest extends AbstractRestServiceTest {
	
	@Inject
	private UsuariosService usuarioservice; 
	
	@Test
	public void AuthenticateUser_LoginAndPasswordMatch_ResponseOK()
	{
		String loginindataset="jorgeejgonzalez";
		String passwordindataset="algoalgo";
		Response response=usuarioservice.authenticateUser(loginindataset, passwordindataset);
		assertOK(response);
	}
	
	@Test
	public void AuthenticateUser_LoginAndPasswordMatch_EntityUsuario()
	{
		String loginindataset="jorgeejgonzalez";
		String passwordindataset="algoalgo";
		Response response=usuarioservice.authenticateUser(loginindataset, passwordindataset);
		Usuario entity=(Usuario)response.getEntity();
		assertEquals(loginindataset, entity.getLogin());
	}
	
	@Test
	public void AuthenticateUser_LoginAndPasswordMatch_EntityUsuarioDTO()
	{
		String loginindataset="jorgeejgonzalez";
		String passwordindataset="algoalgo";
		Response response=usuarioservice.authenticateUser(loginindataset, passwordindataset);
		Usuario entity=(Usuario)response.getEntity();
		assertTrue(entity instanceof UsuarioDTO);
	}
	
	@Test
	public void AuthenticateUser_LoginIsNotInDatabase_ResponseNotFound()
	{
		String login="abcdefg";
		String password="abcdef";
		Response response=usuarioservice.authenticateUser(login, password);
		assertNotFound(response);
	}
	
	@Test
	public void AuthenticateUser_LoginAndPassworDoNotMatch_ResponseUnauthorized()
	{
		String loginindataset="jorgeejgonzalez";
		String passwordnotindataset="abcdefgij";
		Response response=usuarioservice.authenticateUser(loginindataset, passwordnotindataset);
		assertUnauthorized(response);
	}
	
	@Test
	public void AuthenticateUser_LoginIsNull_ResponseNotAcceptable()
	{
		String login=null;
		String password="abcdef";
		Response response=usuarioservice.authenticateUser(login, password);
		assertNotAcceptable(response);
	}
	
	@Test
	public void AuthenticateUser_LoginIsEmpty_ResponseNotAcceptable()
	{
		String login="";
		String password="abcdef";
		Response response=usuarioservice.authenticateUser(login, password);
		assertNotAcceptable(response);
	}
	
	@Test
	public void AuthenticateUser_PasswordIsNull_ResponseNotAcceptable()
	{
		String login="abcdefgh";
		String password=null;
		Response response=usuarioservice.authenticateUser(login, password);
		assertNotAcceptable(response);
	}
	
	@Test
	public void AuthenticateUser_PasswordIsEmpty_ResponseNotAcceptable()
	{
		String login="abcdefgh";
		String password="";
		Response response=usuarioservice.authenticateUser(login, password);
		assertNotAcceptable(response);
	}

}
