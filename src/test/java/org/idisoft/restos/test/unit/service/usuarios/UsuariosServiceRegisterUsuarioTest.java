package org.idisoft.restos.test.unit.service.usuarios;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.persistence.EntityExistsException;
import javax.validation.ValidationException;
import javax.ws.rs.core.Response;

import org.idisoft.restos.administracionusuarios.business.UsuarioDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UsuariosServiceRegisterUsuarioTest extends AbstractUsuarioServiceTest
{	
	
	@Before
	public void setUpTest()
	{
		setUpEntities();
		when(usuariosrepository.add(validUsuario)).thenReturn(validUsuario);
		when(usuariosrepository.add(invalidUsuario)).thenThrow(new ValidationException());
		setUpService();
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
	public void RegisterUsuario_RepositoryThrowsEntityExistsException_ResponseConflict()
	{
		when(usuariosrepository.add(validUsuario)).thenThrow(new EntityExistsException());
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