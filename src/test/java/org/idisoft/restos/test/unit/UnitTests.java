package org.idisoft.restos.test.unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.idisoft.restos.test.unit.model.jpa.usuario.*;
import org.idisoft.restos.test.unit.data.factory.dto.usuario.*;
import org.idisoft.restos.test.unit.data.factory.jpa.usuario.*;
import org.idisoft.restos.test.unit.data.repository.usuarios.*;
import org.idisoft.restos.test.unit.service.usuarios.*;

@RunWith(Suite.class)
@SuiteClasses({ 
	BeanValidationTest.class, 
	UsuarioDTOFactoryCreateEntityTest.class,
	UsuarioJPAFactoryCreateEntityTest.class,
	UsuariosRepositoryFindByLoginTest.class,
	UsuariosServiceAuthenticateUsuarioTest.class
	})
public class UnitTests {

}
