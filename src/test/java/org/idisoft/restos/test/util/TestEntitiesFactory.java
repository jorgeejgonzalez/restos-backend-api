package org.idisoft.restos.test.util;

import org.idisoft.restos.model.EstatusRegistro;
import org.idisoft.restos.model.TipoUsuario;
import org.idisoft.restos.model.Usuario;
import org.idisoft.restos.model.dto.UsuarioDTO;
import org.idisoft.restos.model.jpa.UsuarioJPA;

public class TestEntitiesFactory {
	
	public static Usuario validUsuario()
	{
		return validUsuarioJPA();
	}
	
	private static Usuario assembleUsuario(Usuario usuario)
	{
		String validCedula="V123456789";
		String validLogin="unittest";
		String validPassword="unittest";
		String validEmail="unit@test.com";
		String validNombre="unit";
		String validApellido="test";
		TipoUsuario validTipo=TipoUsuario.FINAL;
		String validDireccion="fake address";
		String validTelefono="12345678901";
		EstatusRegistro validEstatusRegistro=EstatusRegistro.ACTIVO;
		
		usuario.setCedula(validCedula);
		usuario.setLogin(validLogin);
		usuario.setPassword(validPassword);
		usuario.setNombre(validNombre);
		usuario.setApellido(validApellido);
		usuario.setEmail(validEmail);
		usuario.setTipo(validTipo);
		usuario.setDireccion(validDireccion);
		usuario.setTelefono(validTelefono);
		usuario.setEstatusRegistro(validEstatusRegistro);
		
		return usuario;
		
	}
	
	public static UsuarioJPA validUsuarioJPA()
	{
		UsuarioJPA retorno=(UsuarioJPA)assembleUsuario(new UsuarioJPA());
		return retorno;
	}
	
	public static UsuarioDTO validUsuarioDTO()
	{	
		UsuarioDTO retorno=(UsuarioDTO)assembleUsuario(new UsuarioJPA());
		return retorno;
	}

}
