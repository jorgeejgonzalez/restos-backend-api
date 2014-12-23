package org.idisoft.restos.test.unit.data.repository.usuarios;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import javax.validation.ValidationException;

import org.idisoft.restos.data.repository.DataAccessObject;
import org.idisoft.restos.model.Usuario;
import org.idisoft.restos.model.jpa.ConstantesORM;
import org.idisoft.restos.model.jpa.UsuarioJPA;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UsuariosRepositoryAddTest extends AbstractUsuariosRepositoryTest {
	
	@Test
	public void Add_UsuarioIsNotInRepository_ReturnsUsuario() throws Exception
	{
		instantiateEntities();
		when(usuariojpafactorystub.copyEntity(validusuariojpa)).thenReturn(validusuariojpa);
		when(usuariojpadaostub.persist(validusuariojpa)).thenReturn(validusuariojpa);
		instantiateRepositoryWithMocks();
		when(repository.findByCedula(validusuario.getCedula())).thenThrow(new NoResultException());
		when(repository.findByLogin(validusuario.getLogin())).thenThrow(new NoResultException());
		Usuario added=repository.add(validusuario);
		assertNotNull(added);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=EntityExistsException.class)
	public void Add_CedulaIsInRepository_ThrowEntityExistsException() throws Exception
	{
		instantiateEntities();
		when(usuariojpafactorystub.copyEntity(validusuariojpa)).thenReturn(validusuariojpa);
		when(usuariojpadaostub.findByStringKey(validusuario.getCedula())).thenReturn(validusuariojpa);
		instantiateRepositoryWithMocks();
		
		Usuario added=repository.add(validusuario);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=EntityExistsException.class)
	public void Add_LoginIsInRepository_ThrowEntityExistsException() throws Exception
	{
		instantiateEntities();
		DataAccessObject<UsuarioJPA>.Filter filter=usuariojpadaostub.new Filter(ConstantesORM.USUARIO_LOGIN_ATTRIBUTE_NAME, logininrepository);
		when(usuariojpadaostub.createFilter(ConstantesORM.USUARIO_LOGIN_ATTRIBUTE_NAME, logininrepository)).thenReturn(filter);
		when(usuariojpafactorystub.copyEntity(validusuariojpa)).thenReturn(validusuariojpa);
		when(usuariojpadaostub.findSingle(filter)).thenReturn(validusuariojpa);
		instantiateRepositoryWithMocks();
		
		Usuario added=repository.add(validusuariojpa);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=ValidationException.class)
	public void Add_UsuarioIsNotValid_ThrowValidationException() throws Exception
	{
		instantiateEntities();
		validusuariojpa.setNombre("");
		when(usuariojpafactorystub.copyEntity(validusuariojpa)).thenReturn(validusuariojpa);
		instantiateRepositoryWithMocks();
		Usuario added=repository.add(validusuariojpa);
	}
}
