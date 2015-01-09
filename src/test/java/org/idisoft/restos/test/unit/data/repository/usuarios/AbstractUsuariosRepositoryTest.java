package org.idisoft.restos.test.unit.data.repository.usuarios;

import static org.mockito.Mockito.when;

import javax.validation.Validation;

import org.idisoft.restos.data.factory.jpa.UsuarioJPAFactory;
import org.idisoft.restos.data.repository.DataAccessObject;
import org.idisoft.restos.data.repository.UsuariosRepository;
import org.idisoft.restos.model.Usuario;
import org.idisoft.restos.model.dto.UsuarioDTO;
import org.idisoft.restos.model.jpa.BeanValidator;
import org.idisoft.restos.model.jpa.UsuarioJPA;
import org.idisoft.restos.test.util.TestEntitiesFactory;
import org.mockito.Mock;

public abstract class AbstractUsuariosRepositoryTest 
{
	protected UsuariosRepository repository;
	
	@Mock
	protected UsuarioJPAFactory usuariojpafactorystub;
	@Mock
	protected DataAccessObject<UsuarioJPA> usuariojpadaostub;
	
	protected BeanValidator<UsuarioJPA> beanvalidatorusuariojpa;
	
	protected Usuario invalidUsuario;
	protected Usuario validUsuario;
	protected UsuarioJPA validUsuarioEntity;
	protected UsuarioJPA invalidUsuarioEntity;
	protected UsuarioDTO validUsuarioTransfer;
	
	protected String cedulaInRepository="V123456789";
	protected String cedulaNotInRepository="V987654321";
	protected String loginInRepository="test";
	protected String loginNotInRepository="notest";
	protected String loginEmpty="";
	protected String loginNull=null;
	
	protected void instantiateEntities()
	{
		invalidUsuario=TestEntitiesFactory.validUsuario();
		invalidUsuario.setLogin(loginEmpty);
		invalidUsuarioEntity=TestEntitiesFactory.validUsuarioJPA();
		invalidUsuarioEntity.setLogin(loginEmpty);
		validUsuario=TestEntitiesFactory.validUsuario();
		validUsuarioEntity=TestEntitiesFactory.validUsuarioJPA();
		validUsuarioTransfer=TestEntitiesFactory.validUsuarioDTO();
	}
	
	protected void setUpMockitoRules()
	{
		when(usuariojpafactorystub.copyEntity(validUsuario)).thenReturn(validUsuarioEntity);
	}
	
	protected void instantiateRepositoryWithMocks()
	{	
		beanvalidatorusuariojpa=new BeanValidator<UsuarioJPA>(Validation.buildDefaultValidatorFactory());
		setUpMockitoRules();
		repository=new UsuariosRepository(
				usuariojpadaostub,
				beanvalidatorusuariojpa,
				usuariojpafactorystub);
	}
	
}
