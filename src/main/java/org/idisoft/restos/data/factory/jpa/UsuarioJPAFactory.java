package org.idisoft.restos.data.factory.jpa;

import javax.enterprise.context.RequestScoped;

import org.idisoft.restos.model.Usuario;
import org.idisoft.restos.model.jpa.UsuarioJPA;

@RequestScoped
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
