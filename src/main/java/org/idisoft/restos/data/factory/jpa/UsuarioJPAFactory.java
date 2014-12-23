package org.idisoft.restos.data.factory.jpa;

import javax.enterprise.context.RequestScoped;

import org.idisoft.restos.model.EstatusRegistro;
import org.idisoft.restos.model.TipoUsuario;
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
		retorno.setEmail("");
		retorno.setTipo(TipoUsuario.FINAL);
		retorno.setNombre("");
		retorno.setApellido("");
		retorno.setDireccion("");
		retorno.setTelefono("");
		retorno.setEstatusRegistro(EstatusRegistro.ACTIVO);
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
		copia.setEmail(original.getEmail());
		copia.setTipo(original.getTipo());
		copia.setNombre(original.getNombre());
		copia.setApellido(original.getApellido());
		copia.setDireccion(original.getDireccion());
		copia.setTelefono(original.getTelefono());
		copia.setEstatusRegistro(original.getEstatusRegistro());
		return copia;
	}

}
