package org.idisoft.restos.test.integration.data.repository.dataaccessobject;

import static org.junit.Assert.*;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.idisoft.restos.data.repository.DataAccessObject;
import org.idisoft.restos.data.repository.DataAccessObject.Filter;
import org.idisoft.restos.model.jpa.ConstantesORM;
import org.idisoft.restos.model.jpa.UsuarioJPA;
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
	private DataAccessObject<UsuarioJPA> daousuariojpa;
	
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
		daousuariojpa.setEntityClass(UsuarioJPA.class);
	}
	
	@Test
	public void FindByStringKey_FilterValueInDatabase_ReturnsT()
	{	
		
		UsuarioJPA usuariocheck=daousuariojpa.findByStringKey(cedulaindataset);
		assertNotNull(usuariocheck);
	}
	
	@Test(expected=NoResultException.class)
	public void FindByStringKey_FilterValueNotInDatabase_ThrowsNoResultFoundException()
	{
		@SuppressWarnings("unused")
		UsuarioJPA usuariocheck=daousuariojpa.findByStringKey(cedulanotindataset);
	}
	
	@Test(expected=NoResultException.class)
	public void FindByStringKey_EstatusRegistroDeleted_ThrowsNoResultFoundException()
	{
		@SuppressWarnings("unused")
		UsuarioJPA usuariocheck=daousuariojpa.findByStringKey(ceduladeleted);
	}
	
}
