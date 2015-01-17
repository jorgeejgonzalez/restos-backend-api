package org.idisoft.restos.administracionusuarios.business;


import org.idisoft.restos.administracionusuarios.TipoUsuario;
import org.idisoft.restos.administracionusuarios.Usuario;
import org.idisoft.restos.factory.DTOFactory;

public class UsuarioDTOFactory extends DTOFactory<Usuario> {
	
	public static final String RESTRICTED_PASSWORD="********";
	
	private Usuario defaultUsuarioDTO()
	{
		Usuario retorno=new UsuarioDTO();
		retorno.setCedula("V00000000");
		retorno.setLogin("");
		retorno.setPassword(UsuarioDTOFactory.RESTRICTED_PASSWORD);
		retorno.setEmail("");
		retorno.setTipo(TipoUsuario.FINAL);
		retorno.setNombre("");
		retorno.setApellido("");
		retorno.setDireccion("");
		retorno.setTelefono("");
		return retorno;	
	}

	@Override
	public Usuario createEntity() {
		Usuario retorno=defaultUsuarioDTO();
		return retorno;
	}
	
	@Override
	public Usuario copyEntity(final Usuario original)
	{
		Usuario retorno=defaultUsuarioDTO();
		retorno.setCedula(original.getCedula());
		retorno.setLogin(original.getLogin());
		retorno.setNombre(original.getNombre());
		retorno.setApellido(original.getApellido());
		retorno.setDireccion(original.getDireccion());
		retorno.setTelefono(original.getTelefono());
		retorno.setTipo(original.getTipo());
		return retorno;
	}
	
}
