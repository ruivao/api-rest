package br.com.pontosimetrico.resources;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.pontosimetrico.DAO.SociosDAO;
import br.com.pontosimetrico.model.Socios;


@Path("/socios")
public class SociosResource {

	@GET
	@Produces("Application/json")
	@Path("/showList/{id_empresa}")
	public String listaSocios(@PathParam("id_empresa")int id_empresa) throws SQLException, ClassNotFoundException{
		
		List<Socios>  socios     = new ArrayList<Socios>();
		Gson           gson      = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		SociosDAO      sociosDAO = new SociosDAO();
		
		socios = sociosDAO.listar(id_empresa);
		
		return gson.toJson(socios);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/show/{id}")
	public String buscarSocios(@PathParam("id") int id) throws SQLException, ClassNotFoundException{
		
		Socios         socios         = new Socios();
		Gson           gson           = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		SociosDAO      sociosDAO      = new SociosDAO();				
		socios = sociosDAO.buscar(id);
		
		return gson.toJson(socios);
	}
	
	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces("Application/json")
	 @Path("/create")
	 public String inserirSocio(String json) throws SQLException, ClassNotFoundException{
		 Boolean    confirmar        = false;
		 Gson gson =  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		 SociosDAO  sociosDAO        = new SociosDAO();
		 
		 if (json != null){
			 Socios socios = gson.fromJson(json, Socios.class);
			 confirmar = sociosDAO.cadastrar(socios);			 
		 }
		 
		 if (confirmar == true){
			 return "Socio cadastrado com sucesso!";
		 }else {
			 return "Erro: O socio não foi cadastrado!";
		 }
			
	 }

	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces("Application/json")
	 @Path("/update")
	 public String alterarSocio(String json) throws SQLException, ClassNotFoundException{
		 Boolean    confirmar  = false;
		 Gson gson =  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		 SociosDAO sociosDAO   = new SociosDAO();
		 
		 if (json != null){
			 Socios socios = gson.fromJson(json, Socios.class);
			 confirmar       = sociosDAO.editar(socios);			 
		 }
		 
		 if (confirmar == true){
			 return "Socio editado com sucesso!";
		 }else {
			 return "Erro: O socio não foi editado!";
		 }
			
	 }

	 @GET
	 @Produces("Application/json")
	 @Path("/destroy/{id}")
	 public String deletarSocio(@PathParam("id") int id) throws SQLException, ClassNotFoundException{	
		Boolean        confirmar      = false;
		SociosDAO  sociosDAO  = new SociosDAO();
		
		confirmar = sociosDAO.deletar(id);
		
		 if (confirmar == true){
			 return "Socios deletado com sucesso!";
		 }else {
			 return "Erro: O socios não pode ser deletado!";
		 }
	 }

}
