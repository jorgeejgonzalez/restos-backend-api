package org.idisoft.restos.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(ConstantesREST.USUARIOS_SERVICE)
public interface UsuariosService {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path(ConstantesREST.USUARIOS_FUNCION_AUTHENTICATE)
	public Response authenticateUsuario(@PathParam("login") final String login, final String password);

}
