package org.idisoft.restos.test.unit.administracionusuarios.business.bean;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.persistence.NoResultException;

import org.apache.http.auth.AuthenticationException;
import org.idisoft.restos.administracionusuarios.Usuario;
import org.idisoft.restos.administracionusuarios.business.AdministradorUsuariosBean;
import org.idisoft.restos.administracionusuarios.business.UsuarioDTO;
import org.idisoft.restos.administracionusuarios.business.UsuarioDTOFactory;
import org.idisoft.restos.administracionusuarios.business.repository.UsuariosRepository;
import org.idisoft.restos.test.util.TestEntitiesFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AutenticarUsuarioTest {
	
	private AdministradorUsuariosBean administradorUsuarios;
	
	@Mock
	private UsuariosRepository usuariosRepositoryMock;	
	@Mock
	private UsuarioDTOFactory usuarioDTOsFactoryMock;
	
	private String loginNotInRepository="notest";
	private String loginTest;
	private String passwordTest;	
	
	
	@Before
	public void instanciacion()
	{
		administradorUsuarios=new AdministradorUsuariosBean(usuarioDTOsFactoryMock,usuariosRepositoryMock);
		loginTest=TestEntitiesFactory.VALID_LOGIN;
		passwordTest=TestEntitiesFactory.VALID_PASSWORD;
	}
	
	@Before
	public void mockitoRules()
	{
		when(usuariosRepositoryMock.findByLogin(TestEntitiesFactory.VALID_LOGIN)).thenReturn(TestEntitiesFactory.validUsuario());
		when(usuariosRepositoryMock.findByLogin(loginNotInRepository)).thenThrow(new NoResultException());
		
		when(usuarioDTOsFactoryMock.copyEntity(TestEntitiesFactory.validUsuario())).thenReturn(TestEntitiesFactory.validUsuarioDTO());
	}
	
	@Test
	public void AutenticarUsuario_Success_ReturnsNotNull() throws Exception
	{
		Usuario usuariocheck=administradorUsuarios.auntenticarUsuario(loginTest, passwordTest);
		assertNotNull(usuariocheck);
	}
	
	@Test
	public void AutenticarUsuario_Success_ReturnsUsuarioDTO() throws Exception
	{
		Usuario usuariocheck=administradorUsuarios.auntenticarUsuario(loginTest, passwordTest);
		assertTrue(usuariocheck instanceof UsuarioDTO);
	}

	@Test(expected=IllegalArgumentException.class)
	public void AutenticarUsuario_LoginNull_ThrowIllegalArgumentException() throws Exception 
	{
		loginTest=null;
		administradorUsuarios.auntenticarUsuario(loginTest, passwordTest);		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void AutenticarUsuario_LoginEmpty_ThrowIllegalArgumentException() throws Exception 
	{
		loginTest="";
		administradorUsuarios.auntenticarUsuario(loginTest, passwordTest);		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void AutenticarUsuario_PasswordNull_ThrowIllegalArgumentException() throws Exception
	{
		passwordTest=null;
		administradorUsuarios.auntenticarUsuario(loginTest, passwordTest);
	}
	
	@Test(expected=NoResultException.class)
	public void AutenticarUsuario_LoginNotInRepository_ThrowsNoResultException() throws Exception
	{
		loginTest=loginNotInRepository;
		administradorUsuarios.auntenticarUsuario(loginTest, passwordTest);
	}
	
	@Test(expected=AuthenticationException.class)
	public void AutenticarUsuario_PasswordNoMatch_ThrowsAuthenticationException() throws Exception
	{
		passwordTest="anything";
		administradorUsuarios.auntenticarUsuario(loginTest, passwordTest);
	}
	
	

}
