package org.idisoft.restos.test.integration.data.repository.usuarios;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.validation.ValidationException;

import org.idisoft.restos.data.repository.UsuariosRepository;
import org.idisoft.restos.model.Usuario;
import org.idisoft.restos.test.util.ArquillianArchiver;
import org.idisoft.restos.test.util.ConstantesDataTestFiles;
import org.idisoft.restos.test.util.TestEntitiesFactory;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.Cleanup;
import org.jboss.arquillian.persistence.CleanupStrategy;
import org.jboss.arquillian.persistence.TestExecutionPhase;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class UsuariosRepositoryAddTest {
	
	@Inject
	private UsuariosRepository repository;
	
	@Deployment
	public static Archive<?> createTestArchive()
	{
		WebArchive war=ArquillianArchiver.warFile();
		return  war;
	}

	@Test
	@Transactional(TransactionMode.ROLLBACK)
	@Cleanup(phase = TestExecutionPhase.AFTER, strategy = CleanupStrategy.USED_ROWS_ONLY)
	public void Add_UsuarioIsNotInRepository_ReturnsUsuario() throws Exception
	{
		Usuario usuariointegration=TestEntitiesFactory.usuarioIntegration();
		Usuario usuarioadded=repository.add(usuariointegration);
		assertNotNull(usuarioadded);
	}
	
	@Test
	@Transactional(TransactionMode.ROLLBACK)
	@Cleanup(phase = TestExecutionPhase.AFTER, strategy = CleanupStrategy.USED_ROWS_ONLY)
	public void Add_UsuarioIsNotInRepository_SameCedula() throws Exception
	{
		Usuario usuariointegration=TestEntitiesFactory.usuarioIntegration();
		Usuario usuarioadded=repository.add(usuariointegration);
		assertEquals(usuariointegration.getCedula(), usuarioadded.getCedula());
	}
	
	@Test
	@Transactional(TransactionMode.ROLLBACK)
	@Cleanup(phase = TestExecutionPhase.AFTER, strategy = CleanupStrategy.USED_ROWS_ONLY)
	public void Add_UsuarioIsNotInRepository_SameLogin() throws Exception
	{
		Usuario usuariointegration=TestEntitiesFactory.usuarioIntegration();
		Usuario usuarioadded=repository.add(usuariointegration);
		assertEquals(usuariointegration.getLogin(), usuarioadded.getLogin());
	}
	
	@SuppressWarnings("unused")
	@Test(expected=EntityExistsException.class)
	@UsingDataSet(ConstantesDataTestFiles.DATAFILE_USUARIOS)
	@Transactional(TransactionMode.COMMIT)
	@Cleanup(phase = TestExecutionPhase.AFTER, strategy = CleanupStrategy.USED_ROWS_ONLY)
	public void Add_CedulaIsInRepository_ThrowEntityExistsException() throws Exception
	{
		Usuario usuariointegration=TestEntitiesFactory.usuarioIntegration();
		Usuario usuariorepeated=repository.add(usuariointegration);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=EntityExistsException.class)
	@UsingDataSet(ConstantesDataTestFiles.DATAFILE_USUARIOS)
	@Transactional(TransactionMode.COMMIT)
	@Cleanup(phase = TestExecutionPhase.AFTER, strategy = CleanupStrategy.USED_ROWS_ONLY)
	public void Add_LoginIsInRepository_ThrowPersistenceException() throws Exception
	{
		String differentcedula="V987654321";
		Usuario usuariointegration=TestEntitiesFactory.usuarioIntegration();
		usuariointegration.setCedula(differentcedula);
		Usuario usuariorepeated=repository.add(usuariointegration);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=ValidationException.class)
	@Transactional(TransactionMode.COMMIT)
	@Cleanup(phase = TestExecutionPhase.AFTER, strategy = CleanupStrategy.USED_ROWS_ONLY)
	public void Add_UsuarioIsNotValid_ThrowValidationException() throws Exception
	{
		String emptynombre="";
		Usuario usuariointegration=TestEntitiesFactory.usuarioIntegration();
		usuariointegration.setNombre(emptynombre);
		Usuario usuarioinvalid=repository.add(usuariointegration);
	}

}
