package org.idisoft.restos.test.unit.data.factory.jpa.usuario;

import static org.junit.Assert.*;

import org.idisoft.restos.data.factory.jpa.UsuarioJPAFactory;
import org.idisoft.restos.model.EstatusRegistro;
import org.idisoft.restos.model.Usuario;
import org.idisoft.restos.model.jpa.UsuarioJPA;
import org.junit.Before;
import org.junit.Test;

public class UsuarioJPAFactoryCreateEntityTest {
	
	private UsuarioJPAFactory factory;
	
	private String ceduladefault="V00000000";
	private String logindefault="";
	private String passworddefault="";
	private String nombredefault="";
	
	private String validCedula="V123456789";
	private String validLogin="unittest";
	private String validPassword="unittest";
	private String validEmail="unit@test.com";
	private String validNombre="unit";
	private String validApellido="test";
	private String validDireccion="fake address";
	private String validTelefono="12345678901";
	
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
		Usuario usuariotest=factory.createEntity();
		assertTrue(ceduladefault.equals(usuariotest.getCedula()));
	}
	
	@Test
	public void CreateEntity_DefaultInvocation_DefaultLoginData()
	{
		Usuario usuariotest=factory.createEntity();
		assertTrue(logindefault.equals(usuariotest.getLogin()));
	}
	
	@Test
	public void CreateEntity_DefaultInvocation_DefaultPasswordData()
	{
		Usuario usuariotest=factory.createEntity();
		assertTrue(passworddefault.equals(usuariotest.getPassword()));
	}
	
	@Test
	public void CreateEntity_DefaultInvocation_DefaultNombreData()
	{
		Usuario usuariotest=factory.createEntity();
		assertTrue(nombredefault.equals(usuariotest.getNombre()));
	}
	
	@Test
	public void CreateEntity_ValidParameters_ReturnsUsuarioJPA()
	{	
		Usuario usuariotest=factory.createEntity(
				validCedula,
				validLogin,
				validPassword,
				validEmail,
				validNombre,
				validApellido,
				validDireccion,
				validTelefono);
		
		assertTrue(usuariotest instanceof UsuarioJPA);	
	}
	
	@Test
	public void CreateEntity_ValidParameters_EstatusRegistroIsActivo()
	{	
		Usuario usuariotest=factory.createEntity(
				validCedula,
				validLogin,
				validPassword,
				validEmail,
				validNombre,
				validApellido,
				validDireccion,
				validTelefono);
		
		assertEquals(EstatusRegistro.ACTIVO,usuariotest.getEstatusRegistro());	
	}
	
	@Test
	public void CreateEntity_ValidParameters_SameCedula()
	{	
		Usuario usuariotest=factory.createEntity(
				validCedula,
				validLogin,
				validPassword,
				validEmail,
				validNombre,
				validApellido,
				validDireccion,
				validTelefono);
		
		assertEquals(validCedula,usuariotest.getCedula());	
	}

}
