package org.idisoft.restos.test.unit.data.repository.usuarios;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
public class UsuariosRepositoryFindByLoginTest {
	
	private UsuariosRepository repository;
	
	@Mock
	private UsuarioJPAFactory usuariojpafactorystub;
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
	
	public void instantiateObjects()
	{
		validusuario=TestEntitiesFactory.validUsuarioJPA();
		repository=new UsuariosRepository(usuariojpadaostub,usuariojpafactorystub);
	}
	
	@Test
	public void FindByLogin_LoginIsInDatabase_ReturnsUsuario() throws Exception
	{
		when(typedqueryusuariostub.getSingleResult()).thenReturn(validusuario);
		
		instantiateObjects();
		
		Usuario usuariocheck= repository.findByLogin(loginsuccess);
		assertNotNull(usuariocheck);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=NoResultException.class)
	public void FindByLogin_LoginIsNotInDatabase_ThrowsNoResultException() throws Exception
	{
		when(typedqueryusuariostub.getSingleResult()).thenThrow(new NoResultException());
		
		instantiateObjects();
		Usuario usuariocheck= repository.findByLogin(loginfail);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class)
	public void FindByLogin_LoginIsNull_ThrowsIllegalArgumenException()
	{
		instantiateObjects();
		Usuario usuariocheck= repository.findByLogin(loginnull);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class)
	public void FindByLogin_LoginIsEmpty_ThrowsIllegalArgumenException()
	{
		instantiateObjects();
		Usuario usuariocheck= repository.findByLogin(loginempty);
	}

}
