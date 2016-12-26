package br.com.pontosimetrico.ADM.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/teste")
public class TesteWS {
	
	@GET
	@Path("/ola")
	@Produces(MediaType.TEXT_PLAIN)
	public String testador(){
		return "Olá porra!!!!";
	}
}
