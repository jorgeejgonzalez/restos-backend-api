package org.idisoft.restos.test.unit.data.repository.usuarios;

import static org.junit.Assert.*;

import javax.persistence.criteria.CriteriaBuilder;

import org.idisoft.restos.data.repository.DataAccessObject;
import org.idisoft.restos.data.repository.UsuariosRepository;
import org.idisoft.restos.model.Usuario;
import org.idisoft.restos.model.jpa.UsuarioJPA;
import org.junit.Before;
import org.junit.Test;


public class FindByLoginTest {
	
	

	private UsuariosRepository repository;
	private CriteriaBuilder criteriabuilderstub;
	private DataAccessObject<UsuarioJPA> usuariojpadaostub;
	
	
	@Before
	public void setUp()
	{
		repository=new UsuariosRepository(criteriabuilderstub,usuariojpadaostub);
	}
	
	@Test
	public void FindByLogin_LoginIsInDatabase_ReturnsUsuario() throws Exception
	{
		Usuario usuariocheck= repository.findByLogin("abcdef");
		assertNotNull(usuariocheck);
	}

}
