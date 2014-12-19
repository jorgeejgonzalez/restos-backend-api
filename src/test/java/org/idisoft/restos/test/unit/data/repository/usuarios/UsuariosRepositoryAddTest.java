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
public class UsuariosRepositoryAddTest {
	
	private UsuariosRepository repository;
	
	@Mock
	private DataAccessObject<UsuarioJPA> usuariojpadaostub;
	
	private UsuarioJPA validusuario;
	
	@Before
	public void setUpMockitoRules()
	{
	}
	
	@Before
	public void setUpEntities()
	{
		validusuario=TestEntitiesFactory.validUsuarioJPA();
	}
	
	@Test
	public void Add_UsuarioIsNotInRepository_ReturnsUsuario() throws Exception
	{
		repository=new UsuariosRepository(usuariojpadaostub);
		Usuario added=repository.add(validusuario);
		assertNotNull(added);
	}
}
