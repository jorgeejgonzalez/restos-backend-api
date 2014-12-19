package org.idisoft.restos.test.integration;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.idisoft.restos.test.integration.data.repository.dataaccessobject.DataAccessObjectGetTypedQueryTest;
import org.idisoft.restos.test.integration.data.repository.usuarios.UsuariosRepositoryFindByLoginTest;
import org.idisoft.restos.test.integration.services.usuario.UsuariosServiceAuthenticateUserTest;

@RunWith(Suite.class)
@SuiteClasses({ 
	DataAccessObjectGetTypedQueryTest.class, 
	UsuariosRepositoryFindByLoginTest.class,
	UsuariosServiceAuthenticateUserTest.class 
	})
public class IntegrationTests {

}
