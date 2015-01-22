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
		
		war.addClasses(
				org.idisoft.restos.administracionusuarios.TipoUsuario.class,
				org.idisoft.restos.data.EstatusRegistro.class,
				org.idisoft.restos.data.Registro.class,
				org.idisoft.restos.administracionusuarios.Usuario.class,
				org.idisoft.restos.data.EntityValidator.class,
				org.idisoft.restos.administracionusuarios.business.repository.UsuarioEntity.class,
				org.idisoft.restos.administracionusuarios.business.UsuarioDTO.class);
		
		war.addClasses(org.idisoft.restos.factory.ModelFactory.class,
				org.idisoft.restos.factory.DTOFactory.class,
				org.idisoft.restos.administracionusuarios.business.UsuarioDTOFactory.class,
				org.idisoft.restos.factory.EntityFactory.class,
				org.idisoft.restos.administracionusuarios.business.repository.UsuarioEntityFactory.class);
		
		war.addClasses(org.idisoft.restos.data.DataAccessObject.class,
				org.idisoft.restos.data.Repository.class,
				org.idisoft.restos.administracionusuarios.business.repository.UsuariosRepository.class);
		
		war.addClasses(org.idisoft.restos.administracionusuarios.AdministradorUsuarios.class,
				org.idisoft.restos.administracionusuarios.business.AdministradorUsuariosBean.class);
		
		war.addClasses(org.idisoft.restos.rest.JaxRsActivator.class,
				org.idisoft.restos.rest.UsuariosService.class,
				org.idisoft.restos.rest.UsuariosServiceRest.class);
		
		
		war.addClass(org.idisoft.restos.data.CDIPersistenceResources.class);
		
		war.addClass(org.idisoft.restos.test.unit.service.AbstractRestServiceTest.class);
		war.addClass(org.idisoft.restos.test.integration.services.AbstractRestServiceIntegrationTest.class);
		war.addClass(org.idisoft.restos.test.util.TestEntitiesFactory.class);
		
		war.addClass(javax.persistence.EntityManager.class);
		war.addClass(javax.persistence.criteria.CriteriaBuilder.class);
		war.addClass(javax.persistence.criteria.CriteriaQuery.class);
		war.addClass(javax.persistence.TypedQuery.class);
		
		return war;
	}
	

}
