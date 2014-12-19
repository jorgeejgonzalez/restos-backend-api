package org.idisoft.restos.test.integration.services.usuario;

import static org.junit.Assert.*;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.idisoft.restos.model.Usuario;
import org.idisoft.restos.model.dto.UsuarioDTO;
import org.idisoft.restos.service.UsuariosService;
import org.idisoft.restos.test.integration.services.AbstractRestServiceIntegrationTest;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
@UsingDataSet("data/usuarios.json")
public class UsuariosServiceAuthenticateUserTest extends AbstractRestServiceIntegrationTest {
	
	@Inject
	private UsuariosService usuarioservice;
	
	private String loginindataset="test";
	private String passwordindataset="integration";
	private String loginnotindataset="abcdef";
	private String passwordnotindataset="abc123";
	
	@Test
	public void AuthenticateUser_LoginAndPasswordMatch_ResponseOK()
	{
		Response response=usuarioservice.authenticateUsuario(loginindataset, passwordindataset);
		assertOK(response);
	}
	
	@Test
	public void AuthenticateUser_LoginAndPasswordMatch_EntityUsuario()
	{
		Response response=usuarioservice.authenticateUsuario(loginindataset, passwordindataset);
		Usuario entity=(Usuario)response.getEntity();
		assertEquals(loginindataset, entity.getLogin());
	}
	
	@Test
	public void AuthenticateUser_LoginAndPasswordMatch_EntityUsuarioDTO()
	{
		Response response=usuarioservice.authenticateUsuario(loginindataset, passwordindataset);
		Usuario entity=(Usuario)response.getEntity();
		assertTrue(entity instanceof UsuarioDTO);
	}
	
	@Test
	public void AuthenticateUser_LoginIsNotInDatabase_ResponseNotFound()
	{
		Response response=usuarioservice.authenticateUsuario(loginnotindataset, passwordnotindataset);
		assertNotFound(response);
	}
	
	@Test
	public void AuthenticateUser_LoginAndPassworDoNotMatch_ResponseUnauthorized()
	{
		Response response=usuarioservice.authenticateUsuario(loginindataset, passwordnotindataset);
		assertUnauthorized(response);
	}
	
	@Test
	public void AuthenticateUser_LoginIsNull_ResponseNotAcceptable()
	{
		String login=null;
		Response response=usuarioservice.authenticateUsuario(login, passwordindataset);
		assertNotAcceptable(response);
	}
	
	@Test
	public void AuthenticateUser_LoginIsEmpty_ResponseNotAcceptable()
	{
		String login="";
		Response response=usuarioservice.authenticateUsuario(login, passwordindataset);
		assertNotAcceptable(response);
	}
	
	@Test
	public void AuthenticateUser_PasswordIsNull_ResponseNotAcceptable()
	{
		String password=null;
		Response response=usuarioservice.authenticateUsuario(loginindataset, password);
		assertNotAcceptable(response);
	}
	
	@Test
	public void AuthenticateUser_PasswordIsEmpty_ResponseNotAcceptable()
	{
		String password="";
		Response response=usuarioservice.authenticateUsuario(loginindataset, password);
		assertNotAcceptable(response);
	}

}
