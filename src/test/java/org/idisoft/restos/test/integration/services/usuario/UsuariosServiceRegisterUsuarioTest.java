package org.idisoft.restos.test.integration.services.usuario;

import static org.junit.Assert.*;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.idisoft.restos.model.Usuario;
import org.idisoft.restos.model.dto.UsuarioDTO;
import org.idisoft.restos.service.UsuariosService;
import org.idisoft.restos.test.integration.services.AbstractRestServiceIntegrationTest;
import org.idisoft.restos.test.util.TestEntitiesFactory;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.Cleanup;
import org.jboss.arquillian.persistence.CleanupStrategy;
import org.jboss.arquillian.persistence.TestExecutionPhase;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
@Cleanup(phase = TestExecutionPhase.AFTER, strategy = CleanupStrategy.USED_ROWS_ONLY)
public class UsuariosServiceRegisterUsuarioTest extends AbstractRestServiceIntegrationTest
{	
	@Inject
	private UsuariosService usuarioService;
	
	private Usuario validUsuario;
	private Usuario invalidUsuario;
	
	@Before
	public void setUpTest()
	{
		invalidUsuario=TestEntitiesFactory.usuarioIntegration();
		invalidUsuario.setCedula("");
		validUsuario=TestEntitiesFactory.usuarioIntegration();
		
	}
	
	@Test
	public void RegisterUsuario_ValidParameters_ResponseOK()
	{	
		Response response=usuarioService.registerUsuario(validUsuario);
		assertOK(response);
	}
	
	@Test
	public void RegisterUsuario_ValidParameters_EntityNotNull()
	{	
		Response response=usuarioService.registerUsuario(validUsuario);
		assertNotNull(response.getEntity());
	}
	
	@Test
	public void RegisterUsuario_ValidParameters_EntityUsuarioDTO()
	{
		Response response=usuarioService.registerUsuario(validUsuario);
		assertTrue(response.getEntity() instanceof UsuarioDTO);
	}
	
	@Test
	@UsingDataSet("data/usuarios.json")
	public void RegisterUsuario_RepositoryThrowsEntityExistsException_ResponseConflict()
	{
		Response response=usuarioService.registerUsuario(validUsuario);
		assertConflict(response);
	}
	
	@Test
	public void RegisterUsuario_RepositoryThrowsValidationException_ResponseNotAcceptable()
	{
		Response response=usuarioService.registerUsuario(invalidUsuario);
		assertNotAcceptable(response);
	}
}