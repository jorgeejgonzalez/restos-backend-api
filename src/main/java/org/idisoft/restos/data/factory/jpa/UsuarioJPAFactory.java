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
		retorno.setNombre("");
		return retorno;
	}
	
	@Override
	public Usuario createEntity() {
		Usuario retorno=defaultUsuario();
		return retorno;
	}

	@Override
	public Usuario copyEntity(Usuario original) {
		Usuario copia=defaultUsuario();
		copia.setCedula(original.getCedula());
		copia.setLogin(original.getLogin());
		copia.setPassword(original.getPassword());
		copia.setNombre(original.getNombre());
		return copia;
	}

}
