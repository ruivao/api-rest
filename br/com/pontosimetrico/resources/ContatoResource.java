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

import br.com.pontosimetrico.DAO.ContatoDAO;
import br.com.pontosimetrico.model.Contato;

@Path("/contato")
public class ContatoResource {
	
	@GET
	@Produces("Application/json")
	@Path("/showList/{id_empresa}")
	public String listaContato(@PathParam("id_empresa")int id_empresa) throws SQLException, ClassNotFoundException{
		
		List<Contato>  contato     = new ArrayList<Contato>();
		Gson           gson        = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		ContatoDAO     contatoDAO  = new ContatoDAO();
		
		contato = contatoDAO.listar(id_empresa);
		
		return gson.toJson(contato);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/show/{id}")
	public String buscarContato(@PathParam("id") int id) throws SQLException, ClassNotFoundException{
		
		Contato         contato         = new Contato();
		Gson            gson            = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		ContatoDAO      contatoDAO      = new ContatoDAO();				
		contato = contatoDAO.buscar(id);
		
		return gson.toJson(contato);
	}
	
	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces("Application/json")
	 @Path("/create")
	 public String inserirContato(String json) throws SQLException, ClassNotFoundException{
		 Boolean     confirmar       = false;
		 Gson        gson            = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		 ContatoDAO  contatoDAO      = new ContatoDAO();
		 
		 if (json != null){
			 Contato contato = gson.fromJson(json, Contato.class);
			 confirmar = contatoDAO.cadastrar(contato);			 
		 }
		 
		 if (confirmar == true){
			 return "Contato cadastrado com sucesso!";
		 }else {
			 return "Erro: O contato não foi cadastrado!";
		 }
			
	 }

	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces("Application/json")
	 @Path("/update")
	 public String alterarContato(String json) throws SQLException, ClassNotFoundException{
		 Boolean    confirmar  = false;
		 Gson gson =  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		 ContatoDAO contatoDAO     = new ContatoDAO();
		 
		 if (json != null){
			 Contato contato = gson.fromJson(json, Contato.class);
			 confirmar       = contatoDAO.editar(contato);			 
		 }
		 
		 if (confirmar == true){
			 return "Contato editado com sucesso!";
		 }else {
			 return "Erro: O contato não foi editado!";
		 }			
	 }

	 @GET
	 @Produces("Application/json")
	 @Path("/destroy/{id}")
	 public String deletarContato(@PathParam("id") int id) throws SQLException, ClassNotFoundException{	
		Boolean        confirmar      = false;
		ContatoDAO  contatoDAO  = new ContatoDAO();
		
		confirmar = contatoDAO.deletar(id);
		
		 if (confirmar == true){
			 return "Contato deletado com sucesso!";
		 }else {
			 return "Erro: O contato não pode ser deletado!";
		 }
	 }	
	
}
