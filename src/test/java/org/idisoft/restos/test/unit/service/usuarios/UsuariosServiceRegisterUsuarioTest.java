package org.idisoft.restos.test.unit.service.usuarios;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.persistence.EntityExistsException;
import javax.validation.ValidationException;
import javax.ws.rs.core.Response;

import org.idisoft.restos.model.dto.UsuarioDTO;
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
		Response response=usuarioService.registerUsuario(validUsuario.getCedula(), validUsuario.getLogin(), validUsuario.getPassword(), 
				validUsuario.getEmail(),validUsuario.getNombre(), validUsuario.getApellido(), 
				validUsuario.getDireccion(), validUsuario.getTelefono());
		assertOK(response);
	}
	
	@Test
	public void RegisterUsuario_ValidParameters_EntityNotNull()
	{	
		Response response=usuarioService.registerUsuario(validUsuario.getCedula(), validUsuario.getLogin(), validUsuario.getPassword(), 
				validUsuario.getEmail(),validUsuario.getNombre(), validUsuario.getApellido(), 
				validUsuario.getDireccion(), validUsuario.getTelefono());
		assertNotNull(response.getEntity());
	}
	
	@Test
	public void RegisterUsuario_ValidParameters_EntityUsuarioDTO()
	{
		Response response=usuarioService.registerUsuario(validUsuario.getCedula(), validUsuario.getLogin(), validUsuario.getPassword(), 
				validUsuario.getEmail(),validUsuario.getNombre(), validUsuario.getApellido(), 
				validUsuario.getDireccion(), validUsuario.getTelefono());
		assertTrue(response.getEntity() instanceof UsuarioDTO);
	}
	
	@Test
	public void RegisterUsuario_RepositoryThrowsEntityExistsException_ResponseConflict()
	{
		when(usuariosrepository.add(validUsuario)).thenThrow(new EntityExistsException());
		Response response=usuarioService.registerUsuario(validUsuario.getCedula(), validUsuario.getLogin(), validUsuario.getPassword(), 
				validUsuario.getEmail(),validUsuario.getNombre(), validUsuario.getApellido(), 
				validUsuario.getDireccion(), validUsuario.getTelefono());
		assertConflict(response);
	}
	
	@Test
	public void RegisterUsuario_RepositoryThrowsValidationException_ResponseNotAcceptable()
	{
		Response response=usuarioService.registerUsuario(invalidUsuario.getCedula(), invalidUsuario.getLogin(), invalidUsuario.getPassword(), 
				invalidUsuario.getEmail(),invalidUsuario.getNombre(), invalidUsuario.getApellido(), 
				invalidUsuario.getDireccion(), invalidUsuario.getTelefono());
		assertNotAcceptable(response);
	}
}