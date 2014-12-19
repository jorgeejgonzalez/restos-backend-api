package org.idisoft.restos.test.integration.data.repository.dataaccessobject;

import static org.junit.Assert.*;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.idisoft.restos.data.repository.DataAccessObject;
import org.idisoft.restos.model.jpa.ConstantesORM;
import org.idisoft.restos.model.jpa.UsuarioJPA;
import org.idisoft.restos.test.util.ArquillianArchiver;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.Cleanup;
import org.jboss.arquillian.persistence.CleanupStrategy;
import org.jboss.arquillian.persistence.TestExecutionPhase;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
@UsingDataSet("data/usuarios.json")
@Cleanup(phase = TestExecutionPhase.AFTER, strategy = CleanupStrategy.USED_ROWS_ONLY)
public class DataAccessObjectGetTypedQueryTest {
	
	@Inject
	private DataAccessObject<UsuarioJPA> daousuariojpa;
	
	@Inject
	private CriteriaBuilder criteriabuilder;
	
	private String cedulaindataset="V123456789";
	private String cedulanotindataset="V00000000";
	
	@Deployment
	public static Archive<?> createTestArchive()
	{
		WebArchive war=ArquillianArchiver.warFile();
		return  war;
	}
	
	@Test
	public void GetTypedQuery_QueryValid_ReturnsTypedQuery()
	{	
		CriteriaQuery<UsuarioJPA> query=criteriabuilder.createQuery(UsuarioJPA.class);		
		Root<UsuarioJPA> root=query.from(UsuarioJPA.class);
		Predicate condition=criteriabuilder.equal(
				root.get(ConstantesORM.USUARIO_CEDULA_ATTRIBUTE_NAME), 
				cedulaindataset);
		
		query=query.select(root);
		query=query.where(condition);
		
		TypedQuery<UsuarioJPA> typedquerycheck= daousuariojpa.getTypedQuery(query);
		assertNotNull(typedquerycheck);
	}
	
	@Test
	public void GetTypedQuery_SingleResultSuccesful_ProvidesResults()
	{
		CriteriaQuery<UsuarioJPA> query=criteriabuilder.createQuery(UsuarioJPA.class);		
		Root<UsuarioJPA> root=query.from(UsuarioJPA.class);
		Predicate condition=criteriabuilder.equal(
				root.get(ConstantesORM.USUARIO_CEDULA_ATTRIBUTE_NAME), 
				cedulaindataset);
		
		query=query.select(root);
		query=query.where(condition);
		
		TypedQuery<UsuarioJPA> typedquerycheck= daousuariojpa.getTypedQuery(query);
		UsuarioJPA usuariocheck=typedquerycheck.getSingleResult();
		
		assertNotNull(usuariocheck);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=NoResultException.class)
	public void GetTypedQuery_SingleResultFailed_ThrowNoResultException()
	{
		CriteriaQuery<UsuarioJPA> query=criteriabuilder.createQuery(UsuarioJPA.class);		
		Root<UsuarioJPA> root=query.from(UsuarioJPA.class);
		Predicate condition=criteriabuilder.equal(
				root.get(ConstantesORM.USUARIO_CEDULA_ATTRIBUTE_NAME), 
				cedulanotindataset);
		
		query=query.select(root);
		query=query.where(condition);
		
		TypedQuery<UsuarioJPA> typedquerycheck= daousuariojpa.getTypedQuery(query);
		UsuarioJPA usuariocheck=typedquerycheck.getSingleResult();	
	}
}
