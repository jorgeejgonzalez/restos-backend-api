package org.idisoft.restos.test.unit.data.repository.usuarios;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.persistence.EntityExistsException;

import org.idisoft.restos.data.factory.jpa.UsuarioJPAFactory;
import org.idisoft.restos.data.repository.DataAccessObject;
import org.idisoft.restos.data.repository.UsuariosRepository;
import org.idisoft.restos.model.Usuario;
import org.idisoft.restos.model.jpa.UsuarioJPA;
import org.idisoft.restos.test.util.TestEntitiesFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UsuariosRepositoryAddTest {
	
	private UsuariosRepository repository;
	
	@Mock
	private UsuarioJPAFactory usuariojpafactorystub;
	@Mock
	private DataAccessObject<UsuarioJPA> usuariojpadaostub;
	
	private Usuario validusuario;
	private UsuarioJPA validusuariojpa;
	
	@Before
	public void setUpMockitoRules()
	{
		validusuario=TestEntitiesFactory.validUsuario();
		validusuariojpa=TestEntitiesFactory.validUsuarioJPA();
		
		when(usuariojpafactorystub.copyEntity(validusuario)).thenReturn(validusuariojpa);
	}
	
	
	public void instantiateRepository()
	{
		repository=new UsuariosRepository(usuariojpadaostub,usuariojpafactorystub);
	}
	
	@Test
	public void Add_UsuarioIsNotInRepository_ReturnsUsuario() throws Exception
	{
		when(usuariojpadaostub.persist(validusuariojpa)).thenReturn(validusuariojpa);
		
		instantiateRepository();
		
		Usuario added=repository.add(validusuario);
		assertNotNull(added);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=EntityExistsException.class)
	public void Add_UsuarioIsNInRepository_ThrowEntityExistsException() throws Exception
	{
		when(usuariojpadaostub.persist(validusuariojpa)).thenThrow(new EntityExistsException());
		
		instantiateRepository();
		
		Usuario added=repository.add(validusuario);
	}
}
