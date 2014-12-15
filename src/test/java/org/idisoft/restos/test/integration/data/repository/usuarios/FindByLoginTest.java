package org.idisoft.restos.test.integration.data.repository.usuarios;

import static org.junit.Assert.*;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import org.idisoft.restos.data.repository.UsuariosRepository;
import org.idisoft.restos.model.Usuario;
import org.idisoft.restos.test.util.ArquillianArchiver;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.Cleanup;
import org.jboss.arquillian.persistence.CleanupStrategy;
import org.jboss.arquillian.persistence.TestExecutionPhase;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class FindByLoginTest {
	
	@Inject
	private UsuariosRepository repository;
	
	@Deployment
	public static Archive<?> createTestArchive()
	{
		WebArchive war=ArquillianArchiver.warFile();
		return  war;
	}

	@Test
	@UsingDataSet("data/usuarios.json")
	@Cleanup(phase = TestExecutionPhase.AFTER, strategy = CleanupStrategy.USED_ROWS_ONLY)
	public void FindByLogin_LoginIsInDatabase_ReturnsUsuario() throws Exception
	{
		String loginindataset="test";
		Usuario usuariocheck= repository.findByLogin(loginindataset);
		assertNotNull(usuariocheck);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=NoResultException.class)
	public void FindByLogin_LoginIsNotInDatabase_NoResultException()
	{
		String loginnotindataset="test";
		Usuario usuariocheck= repository.findByLogin(loginnotindataset);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class)
	public void FindByLogin_LoginIsNull_IllegalArgumentException()
	{
		String loginnull=null;
		Usuario usuariocheck= repository.findByLogin(loginnull);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class)
	public void FindByLogin_LoginIsEmpty_IllegalArgumentException()
	{
		String loginempty="";
		Usuario usuariocheck= repository.findByLogin(loginempty);
	}

}
