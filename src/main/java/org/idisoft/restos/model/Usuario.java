package org.idisoft.restos.model;

public interface Usuario {
	
	public String getCedula();
	public String getLogin();
	public String getPassword();
	
	public void setCedula(String cedula);
	public void setLogin(String login);
	public void setPassword(String password);
	

	public String getNombre();
	public void setNombre(String nombre);
}
