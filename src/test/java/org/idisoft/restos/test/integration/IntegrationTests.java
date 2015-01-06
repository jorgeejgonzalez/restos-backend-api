package org.idisoft.restos.test.integration;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.idisoft.restos.test.integration.data.repository.dataaccessobject.DataAccessObjectFindByStringKeyTest;
import org.idisoft.restos.test.integration.data.repository.dataaccessobject.DataAccessObjectFindSingleTest;

@RunWith(Suite.class)
@SuiteClasses({ 
	DataAccessObjectFindByStringKeyTest.class,
	DataAccessObjectFindSingleTest.class
	})
public class IntegrationTests {

}
