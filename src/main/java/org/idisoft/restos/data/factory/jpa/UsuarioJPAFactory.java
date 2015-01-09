package org.idisoft.restos.data.factory.jpa;

import java.util.List;
import java.util.Set;

import javax.enterprise.context.RequestScoped;

import org.idisoft.restos.data.factory.ModelFactory;
import org.idisoft.restos.model.EstatusRegistro;
import org.idisoft.restos.model.TipoUsuario;
import org.idisoft.restos.model.Usuario;
import org.idisoft.restos.model.jpa.UsuarioJPA;

@RequestScoped
public class UsuarioJPAFactory implements ModelFactory<UsuarioJPA> {

	private UsuarioJPA defaultUsuarioJPA()
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
	public UsuarioJPA createEntity() {
		UsuarioJPA retorno=defaultUsuarioJPA();
		return retorno;
	}
	
	public UsuarioJPA createEntity(
			String cedula,
			String login,
			String password,
			String email,
			String nombre,
			String apellido,
			String direccion,
			String telefono)
	{
		UsuarioJPA retorno=defaultUsuarioJPA();
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
	
	public UsuarioJPA copyEntity(Usuario original) {
		UsuarioJPA copia=defaultUsuarioJPA();
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

	@Override
	public UsuarioJPA copyEntity(UsuarioJPA original) {
		return this.copyEntity((Usuario)original);
	}

	@Override
	public List<UsuarioJPA> createList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<UsuarioJPA> createSet() {
		// TODO Auto-generated method stub
		return null;
	}

}
