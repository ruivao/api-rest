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

import br.com.pontosimetrico.DAO.TelefoneDAO;
import br.com.pontosimetrico.model.Telefone;

@Path("/telefone")
public class TelefoneResource {
	
	@GET
	@Produces("Application/json")
	@Path("/showList/{id_empresa}")
	public String listaTelefone(@PathParam("id_empresa")int id_empresa) throws SQLException, ClassNotFoundException{
		
		List<Telefone>  telefone     = new ArrayList<Telefone>();
		Gson            gson         = new Gson();
		TelefoneDAO     telefoneDAO  = new TelefoneDAO();
		
		telefone = telefoneDAO.listar(id_empresa);
		
		return gson.toJson(telefone);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/show/{id}")
	public String buscarTelefone(@PathParam("id") int id) throws SQLException, ClassNotFoundException{
		
		Telefone         telefone         = new Telefone();
		Gson             gson             = new Gson();
		TelefoneDAO      telefoneDAO      = new TelefoneDAO();				
		telefone = telefoneDAO.buscar(id);
		
		return gson.toJson(telefone);
	}
	
	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces("Application/json")
	 @Path("/create")
	 public String inserirTelefone(String json) throws SQLException, ClassNotFoundException{
		 Boolean      confirmar        = false;
		 Gson         gson             = new Gson();
		 TelefoneDAO  telefoneDAO      = new TelefoneDAO();
		 
		 if (json != null){
			 Telefone telefone = gson.fromJson(json, Telefone.class);
			 confirmar = telefoneDAO.cadastrar(telefone);			 
		 }
		 
		 if (confirmar == true){
			 return "telefone cadastrado com sucesso!";
		 }else {
			 return "Erro: O telefone não foi cadastrado!";
		 }
			
	 }

	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces("Application/json")
	 @Path("/update")
	 public String alterarTelefone(String json) throws SQLException, ClassNotFoundException{
		 Boolean    confirmar  = false;
		 Gson       gson       = new Gson();
		 TelefoneDAO telefoneDAO     = new TelefoneDAO();
		 
		 if (json != null){
			 Telefone telefone = gson.fromJson(json, Telefone.class);
			 confirmar         = telefoneDAO.editar(telefone);			 
		 }
		 
		 if (confirmar == true){
			 return "telefone editado com sucesso!";
		 }else {
			 return "Erro: O telefone não foi editado!";
		 }			
	 }

	 @GET
	 @Produces("Application/json")
	 @Path("/destroy/{id}")
	 public String deletarTelefone(@PathParam("id") int id) throws SQLException, ClassNotFoundException{	
		Boolean        confirmar      = false;
		TelefoneDAO  telefoneDAO  = new TelefoneDAO();
		
		confirmar = telefoneDAO.deletar(id);
		
		 if (confirmar == true){
			 return "telefone deletado com sucesso!";
		 }else {
			 return "Erro: O telefone não pode ser deletado!";
		 }
	 }	
}
