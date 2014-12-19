package org.idisoft.restos.test.util;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

public class ArquillianArchiver {
	
	public static WebArchive warFile()
	{
		WebArchive war=ShrinkWrap.create(WebArchive.class,"test.war");				
		
		war.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		war.addAsResource("META-INF/persistence.xml");
		war.addAsWebInfResource("restostest-ds.xml");
		
		war.addClasses(org.idisoft.restos.model.Usuario.class,
				org.idisoft.restos.model.jpa.UsuarioJPA.class,
				org.idisoft.restos.model.dto.UsuarioDTO.class);
		
		war.addClasses(org.idisoft.restos.data.factory.ModelFactory.class,
				org.idisoft.restos.data.factory.dto.DTOFactory.class,
				org.idisoft.restos.data.factory.dto.UsuarioDTOFactory.class);
		
		war.addClasses(org.idisoft.restos.data.repository.DataAccessObject.class,
				org.idisoft.restos.data.repository.Repository.class,
				org.idisoft.restos.data.repository.UsuariosRepository.class);
		
		war.addClasses(org.idisoft.restos.service.JaxRsActivator.class,
				org.idisoft.restos.service.UsuariosService.class,
				org.idisoft.restos.service.UsuariosServiceImpl.class);
		
		war.addClass(org.idisoft.restos.util.CDIPersistenceResources.class);
		
		war.addClass(org.idisoft.restos.test.unit.service.AbstractRestServiceTest.class);
		war.addClass(org.idisoft.restos.test.integration.services.AbstractRestServiceIntegrationTest.class);
		
		war.addClass(javax.persistence.EntityManager.class);
		war.addClass(javax.persistence.criteria.CriteriaBuilder.class);
		war.addClass(javax.persistence.criteria.CriteriaQuery.class);
		war.addClass(javax.persistence.TypedQuery.class);
		
		return war;
	}
	

}
