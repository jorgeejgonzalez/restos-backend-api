package org.idisoft.restos.model.dto;

import org.idisoft.restos.model.Usuario;

public class UsuarioDTO implements Usuario {

	private String cedula;
	private String login;
	private String password;
	
	@Override
	public String getCedula() {
		return cedula;
	}

	@Override
	public String getLogin() {
		return login;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setCedula(String cedula) {
		this.cedula=cedula;
	}

	@Override
	public void setLogin(String login) {
		this.login=login;
	}

	@Override
	public void setPassword(String password) {
		this.password=password;
	}

}
