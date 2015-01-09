package org.idisoft.restos.test.unit.data.repository.usuarios;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import javax.validation.ValidationException;

import org.idisoft.restos.data.repository.DataAccessObject;
import org.idisoft.restos.model.Usuario;
import org.idisoft.restos.model.jpa.ConstantesORM;
import org.idisoft.restos.model.jpa.UsuarioEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UsuariosRepositoryAddTest extends AbstractUsuariosRepositoryTest {
	
	@Test
	public void Add_UsuarioIsNotInRepository_ReturnsUsuario() throws Exception
	{
		instantiateEntities();
		instantiateRepositoryWithMocks();
		
		when(usuariojpafactorystub.copyEntity(validUsuarioEntity)).thenReturn(validUsuarioEntity);
		when(usuariojpadaostub.persist(validUsuarioEntity)).thenReturn(validUsuarioEntity);
		when(repository.findByCedula(validUsuario.getCedula())).thenThrow(new NoResultException());
		when(repository.findByLogin(validUsuario.getLogin())).thenThrow(new NoResultException());
		
		Usuario added=repository.add(validUsuario);
		assertNotNull(added);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=EntityExistsException.class)
	public void Add_CedulaIsInRepository_ThrowEntityExistsException() throws Exception
	{
		instantiateEntities();
		instantiateRepositoryWithMocks();
		when(usuariojpafactorystub.copyEntity(validUsuarioEntity)).thenReturn(validUsuarioEntity);
		when(usuariojpadaostub.findByStringKey(validUsuario.getCedula())).thenReturn(validUsuarioEntity);
				
		Usuario added=repository.add(validUsuario);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=EntityExistsException.class)
	public void Add_LoginIsInRepository_ThrowEntityExistsException() throws Exception
	{
		instantiateEntities();
		instantiateRepositoryWithMocks();
		
		DataAccessObject<UsuarioEntity>.Filter filter=usuariojpadaostub.new Filter(ConstantesORM.USUARIO_LOGIN_ATTRIBUTE_NAME, loginInRepository);
		
		when(usuariojpadaostub.createFilter(ConstantesORM.USUARIO_LOGIN_ATTRIBUTE_NAME, loginInRepository)).thenReturn(filter);
		when(usuariojpafactorystub.copyEntity(validUsuarioEntity)).thenReturn(validUsuarioEntity);
		when(usuariojpadaostub.findSingle(filter)).thenReturn(validUsuarioEntity);
				
		Usuario added=repository.add(validUsuarioEntity);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=ValidationException.class)
	public void Add_UsuarioIsNotValid_ThrowValidationException() throws Exception
	{
		instantiateEntities();
		instantiateRepositoryWithMocks();
		
		when(usuariojpafactorystub.copyEntity(invalidUsuarioEntity)).thenReturn(invalidUsuarioEntity);
		when(usuariojpafactorystub.copyEntity(invalidUsuario)).thenReturn(invalidUsuarioEntity);
		
		Usuario added=repository.add(invalidUsuarioEntity);
	}
}
