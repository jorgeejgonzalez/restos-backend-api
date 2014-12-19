package org.idisoft.restos.test.unit.data.factory.jpa.usuario;

import static org.junit.Assert.*;

import org.idisoft.restos.data.factory.jpa.UsuarioJPAFactory;
import org.idisoft.restos.model.Usuario;
import org.idisoft.restos.model.jpa.UsuarioJPA;
import org.idisoft.restos.test.util.TestEntitiesFactory;
import org.junit.Before;
import org.junit.Test;

public class UsuarioJPAFactoryCopyEntityTest {
	
	UsuarioJPAFactory factory;
	Usuario validusuario;
	
	@Before
	public void instantiateFactory()
	{
		factory=new UsuarioJPAFactory();
		validusuario=TestEntitiesFactory.validUsuario();
	}

	@Test
	public void CopyEntity_DefaultInvocation_UsuarioJPA() 
	{
		Usuario usuariotest=factory.copyEntity(validusuario);
		assertTrue(usuariotest instanceof UsuarioJPA);
	}
	
	@Test
	public void CopyEntity_DefaultInvocation_SameCedula()
	{
		Usuario usuariotest=factory.copyEntity(validusuario);
		assertEquals(validusuario.getCedula(), usuariotest.getCedula());
	}
	
	@Test
	public void CopyEntity_DefaultInvocation_SameLogin()
	{
		Usuario usuariotest=factory.copyEntity(validusuario);
		assertEquals(validusuario.getLogin(), usuariotest.getLogin());
	}
	
	@Test
	public void CopyEntity_DefaultInvocation_SamePassword()
	{
		Usuario usuariotest=factory.copyEntity(validusuario);
		assertEquals(validusuario.getPassword(), usuariotest.getPassword());
	}
	
	@Test
	public void CopyEntity_DefaultInvocation_SameNombre()
	{
		Usuario usuariotest=factory.copyEntity(validusuario);
		assertEquals(validusuario.getNombre(), usuariotest.getNombre());
	}

}
