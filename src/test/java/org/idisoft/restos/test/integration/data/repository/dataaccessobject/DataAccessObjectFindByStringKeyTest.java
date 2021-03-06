package org.idisoft.restos.test.integration.data.repository.dataaccessobject;

import static org.junit.Assert.*;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import org.idisoft.restos.administracionusuarios.business.repository.UsuarioEntity;
import org.idisoft.restos.data.DataAccessObject;
import org.idisoft.restos.test.util.ArquillianArchiver;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.Cleanup;
import org.jboss.arquillian.persistence.CleanupStrategy;
import org.jboss.arquillian.persistence.TestExecutionPhase;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
@UsingDataSet("data/usuarios.json")
@Cleanup(phase = TestExecutionPhase.AFTER, strategy = CleanupStrategy.USED_ROWS_ONLY)
public class DataAccessObjectFindByStringKeyTest {
	
	@Inject
	private DataAccessObject<UsuarioEntity> daousuariojpa;
	
	private String cedulaindataset="V123456789";
	private String ceduladeleted="V987654321";
	private String cedulanotindataset="V00000000";
	
	@Deployment
	public static Archive<?> createTestArchive()
	{
		WebArchive war=ArquillianArchiver.warFile();
		return  war;
	}
	
	@Before
	public void setEntityClass()
	{
		daousuariojpa.setEntityClass(UsuarioEntity.class);
	}
	
	@Test
	public void FindByStringKey_FilterValueInDatabase_ReturnsT()
	{	
		
		UsuarioEntity usuariocheck=daousuariojpa.findByStringKey(cedulaindataset);
		assertNotNull(usuariocheck);
	}
	
	@Test(expected=NoResultException.class)
	public void FindByStringKey_FilterValueNotInDatabase_ThrowsNoResultFoundException()
	{
		@SuppressWarnings("unused")
		UsuarioEntity usuariocheck=daousuariojpa.findByStringKey(cedulanotindataset);
	}
	
	@Test(expected=NoResultException.class)
	public void FindByStringKey_EstatusRegistroDeleted_ThrowsNoResultFoundException()
	{
		@SuppressWarnings("unused")
		UsuarioEntity usuariocheck=daousuariojpa.findByStringKey(ceduladeleted);
	}
	
}
