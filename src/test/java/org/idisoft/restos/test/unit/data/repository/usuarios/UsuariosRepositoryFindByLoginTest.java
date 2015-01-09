package org.idisoft.restos.test.unit.data.repository.usuarios;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.persistence.NoResultException;

import org.idisoft.restos.data.repository.DataAccessObject;
import org.idisoft.restos.model.Usuario;
import org.idisoft.restos.model.jpa.ConstantesORM;
import org.idisoft.restos.model.jpa.UsuarioJPA;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UsuariosRepositoryFindByLoginTest extends AbstractUsuariosRepositoryTest {
	
	@Test
	public void FindByLogin_LoginIsInDatabase_ReturnsUsuario() throws Exception
	{
		instantiateEntities();
		
		DataAccessObject<UsuarioJPA>.Filter filter=usuariojpadaostub.new Filter(ConstantesORM.USUARIO_LOGIN_ATTRIBUTE_NAME, loginInRepository);
		
		when(usuariojpadaostub.createFilter(ConstantesORM.USUARIO_LOGIN_ATTRIBUTE_NAME, loginInRepository)).thenReturn(filter);
		when(usuariojpadaostub.findSingle(filter)).thenReturn(validUsuarioEntity);
		
		instantiateRepositoryWithMocks();
		
		Usuario usuariocheck= repository.findByLogin(loginInRepository);
		assertNotNull(usuariocheck);
	}
	
	@Test(expected=NoResultException.class)
	public void FindByLogin_LoginIsNotInDatabase_ThrowsNoResultException() throws Exception
	{
		instantiateEntities();
		
		DataAccessObject<UsuarioJPA>.Filter filter=usuariojpadaostub.new Filter(ConstantesORM.USUARIO_LOGIN_ATTRIBUTE_NAME, loginNotInRepository);
		
		when(usuariojpadaostub.createFilter(ConstantesORM.USUARIO_LOGIN_ATTRIBUTE_NAME, loginNotInRepository)).thenReturn(filter);
		when(usuariojpadaostub.findSingle(filter)).thenThrow(new NoResultException());
		
		instantiateRepositoryWithMocks();
		
		@SuppressWarnings("unused")
		Usuario usuariocheck= repository.findByLogin(loginNotInRepository);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class)
	public void FindByLogin_LoginIsNull_ThrowsIllegalArgumenException()
	{
		instantiateRepositoryWithMocks();
		Usuario usuariocheck= repository.findByLogin(loginNull);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class)
	public void FindByLogin_LoginIsEmpty_ThrowsIllegalArgumenException()
	{
		instantiateRepositoryWithMocks();
		Usuario usuariocheck= repository.findByLogin(loginEmpty);
	}
	
	@Test(expected=NoResultException.class)
	public void FindByLogin_UsuarioEstatusRegistroIsDeleted_ThrowsNoResultException() throws Exception
	{
		instantiateEntities();
		
		DataAccessObject<UsuarioJPA>.Filter filter=usuariojpadaostub.new Filter(ConstantesORM.USUARIO_LOGIN_ATTRIBUTE_NAME, loginNotInRepository);
		
		when(usuariojpadaostub.createFilter(ConstantesORM.USUARIO_LOGIN_ATTRIBUTE_NAME, loginNotInRepository)).thenReturn(filter);
		when(usuariojpadaostub.findSingle(filter)).thenThrow(new NoResultException());
		
		instantiateRepositoryWithMocks();
		
		@SuppressWarnings("unused")
		Usuario usuariocheck= repository.findByLogin(loginNotInRepository);
	}

}
