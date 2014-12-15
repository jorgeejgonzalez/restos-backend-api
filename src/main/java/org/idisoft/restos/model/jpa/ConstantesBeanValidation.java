package org.idisoft.restos.model.jpa;

public class ConstantesBeanValidation {
	
	public static final String USUARIO_CEDULA_REGEXP="[V,E]{1}[0-9]+";
	public static final int USUARIO_CEDULA_MIN_SIZE=8;
	public static final int USUARIO_CEDULA_MAX_SIZE=10;
	public static final String USUARIO_LOGIN_REGEXP="[A-Z,a-z,1-9,_,-]*";
	public static final int USUARIO_LOGIN_MIN_SIZE=6;
	public static final int USUARIO_LOGIN_MAX_SIZE=20;
	public static final String USUARIO_PASSWORD_REGEXP="";
	public static final int USUARIO_PASSWORD_MIN_SIZE=8;
	public static final int USUARIO_PASSWORD_MAX_SIZE=20;
	public static final String USUARIO_NOMBRE_REGEXP="[A-Z,a-z, ]*";
	public static final int USUARIO_NOMBRE_MIN_SIZE=4;
	public static final int USUARIO_NOMBRE_MAX_SIZE=50;
	
	
}
