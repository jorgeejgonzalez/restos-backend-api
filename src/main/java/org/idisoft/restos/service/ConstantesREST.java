package org.idisoft.restos.service;

import org.idisoft.restos.model.jpa.ConstantesBeanValidation;

public class ConstantesREST {
	
	public static final String SERVICES_PATH="/services";
	
	public static final String USUARIOS_SERVICE="/usuarios";
	public static final String USUARIOS_FUNCTION_AUTHENTICATE="/{login:"+ConstantesBeanValidation.USUARIO_LOGIN_REGEXP+"}";
	public static final String USUARIOS_FUNCTION_REGISTER="/{cedula:"+ConstantesBeanValidation.USUARIO_CEDULA_REGEXP+"}";

}
