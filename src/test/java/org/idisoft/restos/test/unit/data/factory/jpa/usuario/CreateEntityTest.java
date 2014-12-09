package org.idisoft.restos.test.unit.data.factory.jpa.usuario;

import static org.junit.Assert.*;

import org.idisoft.restos.data.factory.jpa.UsuarioJPAFactory;
import org.idisoft.restos.model.Usuario;
import org.idisoft.restos.model.jpa.UsuarioJPA;
import org.junit.Before;
import org.junit.Test;

public class CreateEntityTest {
	
	UsuarioJPAFactory factory;
	
	@Before
	public void instantiateFactory()
	{
		factory=new UsuarioJPAFactory();
	}

	@Test
	public void CreateEntity_DefaultInvocation_UsuarioJPA() {
		Usuario usuariotest=factory.createEntity();
		assertTrue(usuariotest instanceof UsuarioJPA);
	}
	
	@Test
	public void CreateEntity_DefaultInvocation_DefaultCedulaData()
	{
		String defaultCedula="V00000000";
		Usuario usuariotest=factory.createEntity();
		assertTrue(defaultCedula.equals(usuariotest.getCedula()));
	}
	
	@Test
	public void CreateEntity_DefaultInvocation_DefaultLoginData()
	{
		String defaultLogin="";
		Usuario usuariotest=factory.createEntity();
		assertTrue(defaultLogin.equals(usuariotest.getLogin()));
	}
	
	@Test
	public void CreateEntity_DefaultInvocation_DefaultPasswordData()
	{
		String defaultPassword="";
		Usuario usuariotest=factory.createEntity();
		assertTrue(defaultPassword.equals(usuariotest.getPassword()));
	}

}
