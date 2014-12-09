package org.idisoft.restos.data.factory.jpa;

import org.idisoft.restos.model.Usuario;
import org.idisoft.restos.model.jpa.UsuarioJPA;

public class UsuarioJPAFactory extends JPAFactory<Usuario> {

	private Usuario defaultUsuario()
	{
		Usuario retorno=new UsuarioJPA();
		retorno.setCedula("V00000000");
		retorno.setLogin("");
		retorno.setPassword("");
		return retorno;
	}
	
	@Override
	public Usuario createEntity() {
		Usuario retorno=defaultUsuario();
		return retorno;
	}

}
