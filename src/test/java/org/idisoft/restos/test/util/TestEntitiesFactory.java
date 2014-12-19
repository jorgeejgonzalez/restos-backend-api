package org.idisoft.restos.test.util;

import org.idisoft.restos.model.Usuario;
import org.idisoft.restos.model.dto.UsuarioDTO;
import org.idisoft.restos.model.jpa.UsuarioJPA;

public class TestEntitiesFactory {
	
	public static Usuario validUsuario()
	{
		String validCedula="V123456789";
		String validLogin="unittest";
		String validPassword="unittest";
		String validNombre="unit";
		
		Usuario retorno=new UsuarioJPA();
		retorno.setCedula(validCedula);
		retorno.setLogin(validLogin);
		retorno.setPassword(validPassword);
		retorno.setNombre(validNombre);
		
		return retorno;
	}
	
	public static UsuarioJPA validUsuarioJPA()
	{
		String validCedula="V123456789";
		String validLogin="unittest";
		String validPassword="unittest";
		String validNombre="unit test";
		
		UsuarioJPA retorno=new UsuarioJPA();
		retorno.setCedula(validCedula);
		retorno.setLogin(validLogin);
		retorno.setPassword(validPassword);
		retorno.setNombre(validNombre);
		
		return retorno;
	}
	
	public static UsuarioDTO validUsuarioDTO()
	{
		String validCedula="V123456789";
		String validLogin="unittest";
		String validPassword="unittest";
		String validNombre="unit test";
		
		UsuarioDTO retorno=new UsuarioDTO();
		retorno.setCedula(validCedula);
		retorno.setLogin(validLogin);
		retorno.setPassword(validPassword);
		retorno.setNombre(validNombre);
		
		return retorno;
	}

}
