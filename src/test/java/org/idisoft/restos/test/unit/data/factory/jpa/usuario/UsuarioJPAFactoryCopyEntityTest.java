package org.idisoft.restos.test.unit.data.factory.jpa.usuario;

import static org.junit.Assert.*;

import org.idisoft.restos.data.factory.jpa.UsuarioJPAFactory;
import org.idisoft.restos.model.Usuario;
import org.idisoft.restos.model.jpa.UsuarioJPA;
import org.idisoft.restos.test.util.TestEntitiesFactory;
import org.junit.Before;
import org.junit.Test;

public class UsuarioJPAFactoryCopyEntityTest {
	
	private UsuarioJPAFactory factory;
	private Usuario validusuario;
	private Usuario usuariotest;
	
	@Before
	public void instantiateFactory()
	{
		factory=new UsuarioJPAFactory();
		validusuario=TestEntitiesFactory.validUsuario();
		usuariotest=factory.copyEntity(validusuario);
	}

	@Test
	public void CopyEntity_DefaultInvocation_UsuarioJPA() 
	{
		assertTrue(usuariotest instanceof UsuarioJPA);
	}
	
	@Test
	public void CopyEntity_DefaultInvocation_SameCedula()
	{
		assertEquals(validusuario.getCedula(), usuariotest.getCedula());
	}
	
	@Test
	public void CopyEntity_DefaultInvocation_SameLogin()
	{
		assertEquals(validusuario.getLogin(), usuariotest.getLogin());
	}
	
	@Test
	public void CopyEntity_DefaultInvocation_SamePassword()
	{
		assertEquals(validusuario.getPassword(), usuariotest.getPassword());
	}
	
	@Test
	public void CopyEntity_DefaultInvocation_SameNombre()
	{
		assertEquals(validusuario.getNombre(), usuariotest.getNombre());
	}
	
	@Test
	public void CopyEntity_DefaultInvocation_SameApellido()
	{
		assertEquals(validusuario.getApellido(), usuariotest.getApellido());
	}
	
	@Test
	public void CopyEntity_DefaultInvocation_SameDireccion()
	{
		assertEquals(validusuario.getDireccion(), usuariotest.getDireccion());
	}
	
	@Test
	public void CopyEntity_DefaultInvocation_SameTelefono()
	{
		assertEquals(validusuario.getTelefono(), usuariotest.getTelefono());
	}
	
	@Test
	public void CopyEntity_DefaultInvocation_SameTipo()
	{
		assertEquals(validusuario.getTipo(), usuariotest.getTipo());
	}

}
