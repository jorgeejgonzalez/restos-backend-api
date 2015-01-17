package org.idisoft.restos.test.unit.service.usuarios;

import org.idisoft.restos.administracionusuarios.Usuario;
import org.idisoft.restos.administracionusuarios.business.UsuarioDTOFactory;
import org.idisoft.restos.administracionusuarios.business.repository.UsuariosRepository;
import org.idisoft.restos.rest.UsuariosService;
import org.idisoft.restos.rest.UsuariosServiceRest;
import org.idisoft.restos.test.unit.service.AbstractRestServiceTest;
import org.idisoft.restos.test.util.TestEntitiesFactory;
import org.mockito.Mock;

public class AbstractUsuarioServiceTest extends AbstractRestServiceTest {
	
	@Mock
	protected UsuariosRepository usuariosrepository;
	protected UsuarioDTOFactory usuariodtofactory;
	
	protected UsuariosService usuarioService;
	
	protected Usuario validUsuario;
	protected Usuario invalidUsuario;
	protected Usuario validUsuarioDTO;
	protected String loginInDataset="unittest";
	protected String passwordInDataset="unittest";
	protected String loginNotInDataset="notest";
	protected String passwordNotInDataset="passfail";
	protected String loginEmpty="";
	protected String passwordEmpty="";
	protected String loginNull=null;
	protected String passwordNull=null;
	
	protected void setUpEntities()
	{
		usuariodtofactory=new UsuarioDTOFactory();
		validUsuario=TestEntitiesFactory.validUsuario();
		invalidUsuario=TestEntitiesFactory.invalidUsuario();
		validUsuarioDTO=TestEntitiesFactory.validUsuarioDTO();
	}
	
	protected void setUpService()
	{
		usuarioService=new UsuariosServiceRest(usuariosrepository, usuariodtofactory);
	}

}
