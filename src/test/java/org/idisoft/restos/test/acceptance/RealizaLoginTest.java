package org.idisoft.restos.test.acceptance;

import static org.junit.Assert.*;

import java.net.URL;

import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;
import org.idisoft.restos.service.UsuariosService;
import org.idisoft.restos.test.util.ArquillianArchiver;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class RealizaLoginTest {
	
	/*
	 * load this data
	 * INSERT INTO usuarios 
	 * VALUES('V987654321','acceptance','testasclient','test@integration.com',0,'test','test','test','12345678901',0)
	 */
	
	
	@ArquillianResource
	private URL deploymenturl;
	
	@Deployment(testable=false)
	public static Archive<?> createTestArchive()
	{
		WebArchive war=ArquillianArchiver.warFile();
		return  war;
	}
	
	
	@Test
	public void AutenticacionExitosa(
			@ArquillianResteasyResource("services") 
			UsuariosService usuarioservice) 
	{	
		//	Given
		final String loginindataset="acceptance";
		final String passwordindataset="testasclient";
		
		//	When
		final Response response=usuarioservice.authenticateUser(loginindataset, passwordindataset);
		
		//	Then
		assertEquals(HttpStatus.SC_OK, response.getStatus());
	}
	
	@Test
	public void AutenticacionFallidaPorPassword(
			@ArquillianResteasyResource("services") 
			UsuariosService usuarioservice) 
	{	
		//	Given
		final String loginindataset="acceptance";
		final String passwordindataset="abcd1234";
		
		//	When
		final Response response=usuarioservice.authenticateUser(loginindataset, passwordindataset);
		
		//	Then
		assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatus());
	}
	
	@Test
	public void AutenticacionFallidaPorLogin(
			@ArquillianResteasyResource("services") 
			UsuariosService usuarioservice) 
	{	
		//	Given
		final String loginindataset="abcdef";
		final String passwordindataset="abcd1234";
		
		//	When
		final Response response=usuarioservice.authenticateUser(loginindataset, passwordindataset);
		
		//	Then
		assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatus());
	}

}
