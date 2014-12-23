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
	
	protected Usuario validusuario;
	protected UsuarioJPA validusuariojpa;
	protected UsuarioDTO validusuariodto;
	
	protected String cedulainrepository="V123456789";
	protected String cedulanotinrepository="V987654321";
	protected String logininrepository="test";
	protected String loginnotinrepository="notest";
	protected String loginempty="";
	protected String loginnull=null;
	
	protected void instantiateEntities()
	{
		validusuario=TestEntitiesFactory.validUsuario();
		validusuariojpa=TestEntitiesFactory.validUsuarioJPA();
		validusuariodto=TestEntitiesFactory.validUsuarioDTO();
	}
	
	protected void setUpMockitoRules()
	{
		when(usuariojpafactorystub.copyEntity(validusuario)).thenReturn(validusuariojpa);
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
