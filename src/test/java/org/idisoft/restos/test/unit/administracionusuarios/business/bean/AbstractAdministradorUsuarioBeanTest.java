package org.idisoft.restos.test.unit.administracionusuarios.business.bean;

import static org.mockito.Mockito.when;

import javax.persistence.NoResultException;

import org.idisoft.restos.administracionusuarios.business.AdministradorUsuariosBean;
import org.idisoft.restos.administracionusuarios.business.UsuarioDTOFactory;
import org.idisoft.restos.administracionusuarios.business.repository.UsuariosRepository;
import org.idisoft.restos.test.util.TestEntitiesFactory;
import org.junit.Before;
import org.mockito.Mock;

public abstract class AbstractAdministradorUsuarioBeanTest {
	
	protected AdministradorUsuariosBean administradorUsuarios;
	
	@Mock
	protected UsuariosRepository usuariosRepositoryMock;	
	@Mock
	protected UsuarioDTOFactory usuarioDTOsFactoryMock;
	
	protected void instanciacion()
	{
		administradorUsuarios=new AdministradorUsuariosBean(usuarioDTOsFactoryMock,usuariosRepositoryMock);
	}
	
	protected void mockitoRules()
	{
		when(usuariosRepositoryMock.findByLogin(TestEntitiesFactory.VALID_LOGIN)).thenReturn(TestEntitiesFactory.validUsuario());
		when(usuarioDTOsFactoryMock.copyEntity(TestEntitiesFactory.validUsuario())).thenReturn(TestEntitiesFactory.validUsuarioDTO());
	}


}
