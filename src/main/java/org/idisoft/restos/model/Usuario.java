package org.idisoft.restos.model;

public interface Usuario
{
	
	public String getCedula();
	public void setCedula(final String cedula);
	
	public String getLogin();
	public void setLogin(final String login);
	
	public String getPassword();
	public void setPassword(final String password);
	
	public String getEmail();
	public void setEmail(final String email);
	
	public TipoUsuario getTipo();
	public void setTipo(final TipoUsuario tipo);
	
	public String getNombre();
	public void setNombre(final String nombre);
	
	public String getApellido();
	public void setApellido(final String apellido);
	
	public String getDireccion();
	public void setDireccion(final String direccion);
	
	public String getTelefono();
	public void setTelefono(final String telefono);
}
