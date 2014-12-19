package org.idisoft.restos.test.unit.data.repository.usuarios;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
public class UsuariosRepositoryFindByLoginTest {
	
	private UsuariosRepository repository;
	
	@Mock
	private DataAccessObject<UsuarioJPA> usuariojpadaostub;
	@Mock
	private CriteriaBuilder crtieriabuilderstub;
	@Mock
	private CriteriaQuery<UsuarioJPA> criteriaqueryusuariostub;
	@Mock
	private Root<UsuarioJPA> criteriarootusuariostub;
	@Mock
	private TypedQuery<UsuarioJPA> typedqueryusuariostub;
	
	private UsuarioJPA validusuario;
	private String loginsuccess="test";
	private String loginfail="notest";
	private String loginempty="";
	private String loginnull=null;
	
	@Before
	public void setUpMockitoRules()
	{
		when(usuariojpadaostub.getCriteriaBuilder()).thenReturn(crtieriabuilderstub);
		when(usuariojpadaostub.getCriteriaQuery()).thenReturn(criteriaqueryusuariostub);
		when(criteriaqueryusuariostub.from(UsuarioJPA.class)).thenReturn(criteriarootusuariostub);
		when(criteriaqueryusuariostub.select(criteriarootusuariostub)).thenReturn(criteriaqueryusuariostub);
		when(usuariojpadaostub.getTypedQuery(criteriaqueryusuariostub)).thenReturn(typedqueryusuariostub);
	}
	
	@Before
	public void setUpEntities()
	{
		validusuario=TestEntitiesFactory.validUsuarioJPA();
	}
	
	@Test
	public void FindByLogin_LoginIsInDatabase_ReturnsUsuario() throws Exception
	{
		when(typedqueryusuariostub.getSingleResult()).thenReturn(validusuario);
		
		repository=new UsuariosRepository(usuariojpadaostub);
		Usuario usuariocheck= repository.findByLogin(loginsuccess);
		assertNotNull(usuariocheck);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=NoResultException.class)
	public void FindByLogin_LoginIsNotInDatabase_ThrowsNoResultException() throws Exception
	{
		when(typedqueryusuariostub.getSingleResult()).thenThrow(new NoResultException());
		
		repository=new UsuariosRepository(usuariojpadaostub);
		Usuario usuariocheck= repository.findByLogin(loginfail);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class)
	public void FindByLogin_LoginIsNull_ThrowsIllegalArgumenException()
	{
		repository=new UsuariosRepository(usuariojpadaostub);
		Usuario usuariocheck= repository.findByLogin(loginnull);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class)
	public void FindByLogin_LoginIsEmpty_ThrowsIllegalArgumenException()
	{
		repository=new UsuariosRepository(usuariojpadaostub);
		Usuario usuariocheck= repository.findByLogin(loginempty);
	}

}
