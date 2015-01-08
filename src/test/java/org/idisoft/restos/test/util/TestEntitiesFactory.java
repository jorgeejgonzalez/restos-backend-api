package org.idisoft.restos.test.util;

import org.idisoft.restos.model.EstatusRegistro;
import org.idisoft.restos.model.TipoUsuario;
import org.idisoft.restos.model.Usuario;
import org.idisoft.restos.model.dto.UsuarioDTO;
import org.idisoft.restos.model.jpa.UsuarioJPA;

public class TestEntitiesFactory {
	
	public static String VALID_CEDULA="V123456789";
	public static String VALID_LOGIN="unittest";
	public static String VALID_PASSWORD="unittest";
	public static String VALID_EMAIL="unit@test.com";
	public static String VALID_NOMBRE="unit";
	public static String VALID_APELLIDO="test";
	public static String VALID_DIRECCION="fake address";
	public static String VALID_TELEFONO="12345678901";
	
	public static TipoUsuario DEFAULT_TIPO=TipoUsuario.FINAL;
	public static EstatusRegistro DEFAULT_ESTATUSREGISTRO=EstatusRegistro.ACTIVO;
	
	public static Usuario validUsuario()
	{
		return validUsuarioJPA();
	}
	
	public static Usuario invalidUsuario()
	{
		return invalidUsuarioJPA();
	}
	
	private static Usuario assembleUsuario(Usuario usuario)
	{
		usuario.setCedula(VALID_CEDULA);
		usuario.setLogin(VALID_LOGIN);
		usuario.setPassword(VALID_PASSWORD);
		usuario.setNombre(VALID_NOMBRE);
		usuario.setApellido(VALID_APELLIDO);
		usuario.setEmail(VALID_EMAIL);
		usuario.setTipo(DEFAULT_TIPO);
		usuario.setDireccion(VALID_DIRECCION);
		usuario.setTelefono(VALID_TELEFONO);
		
		return usuario;
	}
	
	public static UsuarioJPA validUsuarioJPA()
	{
		UsuarioJPA retorno=(UsuarioJPA)assembleUsuario(new UsuarioJPA());
		retorno.setEstatusRegistro(DEFAULT_ESTATUSREGISTRO);
		return retorno;
	}
	
	public static UsuarioJPA invalidUsuarioJPA()
	{
		UsuarioJPA retorno=(UsuarioJPA)assembleUsuario(new UsuarioJPA());
		retorno.setLogin("");
		return retorno;
	}
	
	public static UsuarioDTO validUsuarioDTO()
	{	
		UsuarioDTO retorno=(UsuarioDTO)assembleUsuario(new UsuarioDTO());
		return retorno;
	}
	
	public Usuario usuarioDeleted()
	{
		UsuarioJPA retorno=(UsuarioJPA)assembleUsuario(new UsuarioJPA());
		retorno.setCedula("V00000000");
		retorno.setEstatusRegistro(EstatusRegistro.ELIMINADO);
		return retorno;
	}
	
	public static Usuario usuarioIntegration()
	{
		UsuarioJPA retorno=new UsuarioJPA();
		retorno.setCedula("V123456789");
		retorno.setLogin("integrationtest");
		retorno.setPassword("integrationtest");
		retorno.setTipo(TipoUsuario.FINAL);
		retorno.setEmail("test@integration.com");
		retorno.setNombre("integration");
		retorno.setApellido("test");
		retorno.setDireccion("integration test");
		retorno.setTelefono("12345678901");
		retorno.setEstatusRegistro(EstatusRegistro.ACTIVO);
		return retorno;
	}

}
