package org.idisoft.restos.test.integration.services;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.idisoft.restos.test.util.ArquillianArchiver;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;

public abstract class AbstractRestServiceTest {
	
	@Deployment
	public static Archive<?> createTestArchive()
	{
		WebArchive war=ArquillianArchiver.warFile();
		return  war;
	}
	
	protected void assertOK(Response response)
	{
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}
	
	protected void assertNotFound(Response response)
	{
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}
	
	protected void assertUnauthorized(Response response)
	{
		assertEquals(Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
	}
	
	protected void assertNotAcceptable(Response response)
	{
		assertEquals(Status.NOT_ACCEPTABLE.getStatusCode(),response.getStatus());
	}

}
