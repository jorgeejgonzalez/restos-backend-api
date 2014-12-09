package org.idisoft.restos.test.unit.data.factory.dto.usuario;

import static org.junit.Assert.*;

import org.idisoft.restos.data.factory.dto.UsuarioDTOFactory;
import org.idisoft.restos.model.Usuario;
import org.idisoft.restos.model.dto.UsuarioDTO;
import org.junit.Before;
import org.junit.Test;

public class CreateEntityTest {

	private UsuarioDTOFactory factory;
	
	@Before
	public void setUp()
	{
		factory=new UsuarioDTOFactory();
	}
	
	@Test
	public void CreateEntity_DefaultInvocation_UsuarioDTO() {
		Usuario usuariotest=factory.createEntity();
		assertTrue(usuariotest instanceof UsuarioDTO);
	}
	
	@Test
	public void CreateEntity_DefaultInvocation_DefaultCedula()
	{
		String defaultCedula="V00000000";
		Usuario usuariotest=factory.createEntity();
		assertEquals(defaultCedula,usuariotest.getCedula());
	}
	
	@Test
	public void CreateEntity_DefaultInvocation_DefaultLogin()
	{
		String defaultLogin="";
		Usuario usuariotest=factory.createEntity();
		assertEquals(defaultLogin,usuariotest.getLogin());
	}
	
	@Test
	public void CreateEntity_DefaultInvocation_DefaultPassword()
	{
		String defaultPassword="";
		Usuario usuariotest=factory.createEntity();
		assertEquals(defaultPassword,usuariotest.getPassword());
	}
	
	@Test
	public void CreateEntity_PassEntity_CopyCedula()
	{
		Usuario usuariofirst=factory.createEntity();
		usuariofirst.setCedula("V123456789");
		Usuario usuariosecond=factory.createEntity(usuariofirst);
		assertEquals(usuariofirst.getCedula(),usuariosecond.getCedula());
	}
	
	@Test
	public void CreateEntity_PassEntity_CopyLogin()
	{
		Usuario usuariofirst=factory.createEntity();
		usuariofirst.setLogin("abcdef");
		Usuario usuariosecond=factory.createEntity(usuariofirst);
		assertEquals(usuariofirst.getLogin(),usuariosecond.getLogin());
	}
	
	@Test
	public void CreateEntity_PassEntity_CopyPassword()
	{
		Usuario usuariofirst=factory.createEntity();
		usuariofirst.setPassword("abcdefgh");
		Usuario usuariosecond=factory.createEntity(usuariofirst);
		assertEquals(usuariofirst.getPassword(),usuariosecond.getPassword());
	}

}
