package org.idisoft.restos.test.unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.idisoft.restos.test.unit.model.jpa.usuario.*;
import org.idisoft.restos.test.unit.administracionusuarios.business.bean.AdministradorUsuariosAutenticarUsuarioTest;
import org.idisoft.restos.test.unit.administracionusuarios.business.bean.AdministradorUsuariosRegistrarUsuarioTest;
import org.idisoft.restos.test.unit.data.factory.dto.usuario.*;
import org.idisoft.restos.test.unit.data.factory.jpa.usuario.*;
import org.idisoft.restos.test.unit.data.repository.usuarios.*;
import org.idisoft.restos.test.unit.service.usuarios.*;

@RunWith(Suite.class)
@SuiteClasses({ 
	UsuarioEntityBeanValidationTest.class,
	UsuarioEntityEqualsTest.class,
	UsuarioDTOFactoryCreateEntityTest.class,
	UsuarioDTOFactoryCopyEntityTest.class,
	UsuarioEntityFactoryCreateEntityTest.class,
	UsuarioEntityFactoryCopyEntityTest.class,
	UsuariosRepositoryFindByLoginTest.class,
	UsuariosRepositoryAddTest.class,	
	AdministradorUsuariosAutenticarUsuarioTest.class,
	AdministradorUsuariosRegistrarUsuarioTest.class,
	UsuariosServiceAuthenticateUsuarioTest.class,
	UsuariosServiceRegisterUsuarioTest.class
	})
public class UnitTests {

}
