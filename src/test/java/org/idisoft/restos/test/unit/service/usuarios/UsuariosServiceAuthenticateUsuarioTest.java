package org.idisoft.restos.test.unit.service.usuarios;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.persistence.NoResultException;
import javax.ws.rs.core.Response;

import org.idisoft.restos.data.factory.dto.UsuarioDTOFactory;
import org.idisoft.restos.data.repository.UsuariosRepository;
import org.idisoft.restos.model.Usuario;
import org.idisoft.restos.model.dto.UsuarioDTO;
import org.idisoft.restos.service.UsuariosService;
import org.idisoft.restos.service.UsuariosServiceImpl;
import org.idisoft.restos.test.unit.service.AbstractRestServiceTest;
import org.idisoft.restos.test.util.TestEntitiesFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UsuariosServiceAuthenticateUsuarioTest extends AbstractRestServiceTest
{
	
	@Mock
	private UsuariosRepository usuariosrepository;
	@Mock  
	private UsuarioDTOFactory usuariodtofactory;
	
	private UsuariosService usuarioservice;
	
	private Usuario validusuario;
	private Usuario validusuariodto;
	private String loginindataset="unittest";
	private String passwordindataset="unittest";
	private String loginnotindataset="notest";
	private String passwordnotindataset="passfail";
	private String loginempty="";
	private String passwordempty="";
	private String loginnull=null;
	private String passwordnull=null;
	

	@Before
	public void setUpMockitoRules()
	{
		when(usuariosrepository.findByLogin(loginindataset)).thenReturn(validusuario);
		when(usuariosrepository.findByLogin(loginnotindataset)).thenThrow(new NoResultException());
		when(usuariosrepository.findByLogin(loginempty)).thenThrow(new IllegalArgumentException());
		when(usuariosrepository.findByLogin(loginnull)).thenThrow(new IllegalArgumentException());
		when(usuariodtofactory.createEntity(validusuario)).thenReturn(validusuariodto);
		
		usuarioservice=new UsuariosServiceImpl(usuariosrepository, usuariodtofactory);
	}
	
	@Before
	public void setUpEntities()
	{
		validusuario=TestEntitiesFactory.validUsuario();
		validusuariodto=TestEntitiesFactory.validUsuarioDTO();
	}
	
	@Test
	public void AuthenticateUser_LoginAndPasswordMatch_ResponseOK()
	{
		Response response=usuarioservice.authenticateUsuario(loginindataset, passwordindataset);
		assertOK(response);
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
		Response response=usuarioservice.authenticateUsuario(loginnull, passwordindataset);
		assertNotAcceptable(response);
	}
	
	@Test
	public void AuthenticateUser_LoginIsEmpty_ResponseNotAcceptable()
	{
		Response response=usuarioservice.authenticateUsuario(loginempty, passwordindataset);
		assertNotAcceptable(response);
	}
	
	@Test
	public void AuthenticateUser_PasswordIsNull_ResponseNotAcceptable()
	{
		Response response=usuarioservice.authenticateUsuario(loginindataset, passwordnull);
		assertNotAcceptable(response);
	}
	
	@Test
	public void AuthenticateUser_PasswordIsEmpty_ResponseNotAcceptable()
	{
		Response response=usuarioservice.authenticateUsuario(loginindataset, passwordempty);
		assertNotAcceptable(response);
	}

}
