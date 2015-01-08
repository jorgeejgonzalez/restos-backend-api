package org.idisoft.restos.data.factory.dto;


import org.idisoft.restos.model.EstatusRegistro;
import org.idisoft.restos.model.TipoUsuario;
import org.idisoft.restos.model.Usuario;
import org.idisoft.restos.model.dto.UsuarioDTO;

public class UsuarioDTOFactory extends DTOFactory<Usuario> {
	
	public static final String RESTRICTED_PASSWORD="********";
	
	private Usuario defaultUsuario()
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
		retorno.setEstatusRegistro(EstatusRegistro.ACTIVO);
		return retorno;	
	}

	@Override
	public Usuario createEntity() {
		Usuario retorno=defaultUsuario();
		return retorno;
	}
	
	@Override
	public Usuario copyEntity(final Usuario original)
	{
		Usuario retorno=defaultUsuario();
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
