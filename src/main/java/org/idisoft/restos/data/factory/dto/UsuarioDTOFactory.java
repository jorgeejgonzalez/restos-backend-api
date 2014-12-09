package org.idisoft.restos.data.factory.dto;

import java.util.List;
import java.util.Set;

import org.idisoft.restos.model.Usuario;
import org.idisoft.restos.model.dto.UsuarioDTO;

public class UsuarioDTOFactory extends DTOFactory<Usuario> {

	@Override
	public Usuario createEntity() {
		Usuario retorno=new UsuarioDTO();
		retorno.setCedula("V00000000");
		retorno.setLogin("");
		retorno.setPassword("");
		return retorno;
	}
	
	@Override
	public Usuario createEntity(Usuario entity)
	{
		Usuario retorno=new UsuarioDTO();
		retorno.setCedula(entity.getCedula());
		retorno.setLogin(entity.getLogin());
		retorno.setPassword(entity.getPassword());
		return retorno;
	}
	
	@Override
	public Usuario createEntityFromParent() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Usuario createEntityFromChild() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Usuario> createListFromParent() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Usuario> createListFromChild() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<Usuario> createSetFromParent() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<Usuario> createSetFromChild() {
		throw new UnsupportedOperationException();
	}

	

}
