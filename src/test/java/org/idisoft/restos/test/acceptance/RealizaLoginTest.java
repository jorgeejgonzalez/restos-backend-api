package org.idisoft.restos.test.acceptance;

import static org.junit.Assert.*;

import java.net.URL;

import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;
import org.idisoft.restos.rest.UsuariosService;
import org.idisoft.restos.test.util.ArquillianArchiver;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.Cleanup;
import org.jboss.arquillian.persistence.CleanupStrategy;
import org.jboss.arquillian.persistence.TestExecutionPhase;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
@UsingDataSet("data/usuarios.json")
@Cleanup(phase = TestExecutionPhase.AFTER, strategy = CleanupStrategy.USED_ROWS_ONLY)
public class RealizaLoginTest {
	
	/*
	 * load this data
	 * INSERT INTO usuarios 
	 * VALUES('V987654321','acceptance','testasclient','test@integration.com',0,'test','test','test','12345678901',0)
	 */
	
	
	@ArquillianResource
	private URL deploymenturl;
	
	@Deployment
	public static Archive<?> createTestArchive()
	{
		WebArchive war=ArquillianArchiver.warFile();
		return  war;
	}
	
	
	@Test
	@RunAsClient
	public void AutenticacionExitosa(
			@ArquillianResteasyResource("services") 
			UsuariosService usuarioservice) 
	{	
		//	Given
		final String loginindataset="acceptance";
		final String passwordindataset="testasclient";
		
		//	When
		final Response response=usuarioservice.authenticateUsuario(loginindataset, passwordindataset);
		
		//	Then
		assertEquals(HttpStatus.SC_OK, response.getStatus());
	}
	
	@Test
	@RunAsClient
	public void AutenticacionFallidaPorPassword(
			@ArquillianResteasyResource("services") 
			UsuariosService usuarioservice) 
	{	
		//	Given
		final String loginindataset="acceptance";
		final String passwordindataset="abcd1234";
		
		//	When
		final Response response=usuarioservice.authenticateUsuario(loginindataset, passwordindataset);
		
		//	Then
		assertEquals(HttpStatus.SC_UNAUTHORIZED, response.getStatus());
	}
	
	@Test
	@RunAsClient
	public void AutenticacionFallidaPorLogin(
			@ArquillianResteasyResource("services") 
			UsuariosService usuarioservice) 
	{	
		//	Given
		final String loginindataset="abcdef";
		final String passwordindataset="abcd1234";
		
		//	When
		final Response response=usuarioservice.authenticateUsuario(loginindataset, passwordindataset);
		
		//	Then
		assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatus());
	}

}
