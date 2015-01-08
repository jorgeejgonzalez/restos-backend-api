package org.idisoft.restos.data.factory.jpa;

import javax.enterprise.context.RequestScoped;

import org.idisoft.restos.model.EstatusRegistro;
import org.idisoft.restos.model.TipoUsuario;
import org.idisoft.restos.model.Usuario;
import org.idisoft.restos.model.jpa.UsuarioJPA;

@RequestScoped
public class UsuarioJPAFactory extends JPAFactory<Usuario> {

	private Usuario defaultUsuarioJPA()
	{
		UsuarioJPA retorno=new UsuarioJPA();
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
		Usuario retorno=defaultUsuarioJPA();
		return retorno;
	}
	
	public Usuario createEntity(
			String cedula,
			String login,
			String password,
			String email,
			String nombre,
			String apellido,
			String direccion,
			String telefono)
	{
		Usuario retorno=defaultUsuarioJPA();
		retorno.setCedula(cedula);
		retorno.setLogin(login);
		retorno.setPassword(password);
		retorno.setEmail(email);
		retorno.setNombre(nombre);
		retorno.setApellido(apellido);
		retorno.setDireccion(direccion);
		retorno.setTelefono(telefono);
		return retorno;
	}

	@Override
	public Usuario copyEntity(Usuario original) {
		Usuario copia=defaultUsuarioJPA();
		copia.setCedula(original.getCedula());
		copia.setLogin(original.getLogin());
		copia.setPassword(original.getPassword());
		copia.setEmail(original.getEmail());
		copia.setTipo(original.getTipo());
		copia.setNombre(original.getNombre());
		copia.setApellido(original.getApellido());
		copia.setDireccion(original.getDireccion());
		copia.setTelefono(original.getTelefono());
		return copia;
	}

}
