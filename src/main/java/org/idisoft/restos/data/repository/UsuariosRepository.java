package org.idisoft.restos.data.repository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.idisoft.restos.model.Usuario;
import org.idisoft.restos.model.jpa.ConstantesORM;
import org.idisoft.restos.model.jpa.UsuarioJPA;

@Stateless
public class UsuariosRepository extends Repository<Usuario> {
	
	@Inject
	private DataAccessObject<UsuarioJPA> dao;
	
	@Inject
	private CriteriaBuilder criteriabuilder;
	
	
	public UsuariosRepository()
	{
		
	}
	
	public UsuariosRepository(DataAccessObject<UsuarioJPA> dao,CriteriaBuilder criteriabuilder)
	{
		this.dao=dao;
		this.criteriabuilder=criteriabuilder;
	}
	
	Usuario findByCedula(String cedula)
	{
		CriteriaQuery<UsuarioJPA> criteriaquery=criteriabuilder.createQuery(UsuarioJPA.class);
		Root<UsuarioJPA> root=criteriaquery.from(UsuarioJPA.class);
		Predicate conditionquery=criteriabuilder.equal(
				root.get(ConstantesORM.USUARIO_CEDULA_COLUMN_NAME), 
				cedula);
		
		criteriaquery=criteriaquery.select(root);
		criteriaquery=criteriaquery.where(conditionquery);
		
		Usuario retorno=dao.querySingle(criteriaquery);
		
		return retorno;
	}

}
