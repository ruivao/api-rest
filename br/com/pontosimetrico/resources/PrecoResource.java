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

import br.com.pontosimetrico.DAO.PrecoDAO;
import br.com.pontosimetrico.model.Preco;

@Path("/preco")
public class PrecoResource {
	
	@GET
	@Produces("Application/json")
	@Path("/showList/{id_empresa}")
	public String listaPreco(@PathParam("id_empresa")int id_empresa) throws SQLException, ClassNotFoundException{
		
		List<Preco>  preco    =  new ArrayList<Preco>();
		Gson         gson     =  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		PrecoDAO     precoDAO =  new PrecoDAO();
		
		preco = precoDAO.listar(id_empresa);
		
		return gson.toJson(preco);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/show/{id}")
	public String buscarPreco(@PathParam("id") int id) throws SQLException, ClassNotFoundException{
		
		Preco         preco         = new Preco();
		Gson          gson          = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		PrecoDAO      precoDAO      = new PrecoDAO();				
		preco = precoDAO.buscar(id);
		
		return gson.toJson(preco);
	}
	
	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces("Application/json")
	 @Path("/create")
	 public String inserirPreco(String json) throws SQLException, ClassNotFoundException{
		 Boolean    confirmar        = false;
		 Gson gson =  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		 PrecoDAO  precoDAO        = new PrecoDAO();
		 
		 if (json != null){
			 Preco preco = gson.fromJson(json, Preco.class);
			 confirmar = precoDAO.cadastrar(preco);			 
		 }
		 
		 if (confirmar == true){
			 return "Preço cadastrado com sucesso!";
		 }else {
			 return "Erro: O Preço não foi cadastrado!";
		 }
			
	 }

	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces("Application/json")
	 @Path("/update")
	 public String alterarPreco(String json) throws SQLException, ClassNotFoundException{
		 Boolean    confirmar  = false;
		 Gson gson =  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		 PrecoDAO precoDAO     = new PrecoDAO();
		 
		 if (json != null){
			 Preco preco = gson.fromJson(json, Preco.class);
			 confirmar       = precoDAO.editar(preco);			 
		 }
		 
		 if (confirmar == true){
			 return "Preço editado com sucesso!";
		 }else {
			 return "Erro: O preço não foi editado!";
		 }
			
	 }

	 @GET
	 @Produces("Application/json")
	 @Path("/destroy/{id}")
	 public String deletarPreco(@PathParam("id") int id) throws SQLException, ClassNotFoundException{	
		Boolean        confirmar      = false;
		PrecoDAO  precoDAO  = new PrecoDAO();
		
		confirmar = precoDAO.deletar(id);
		
		 if (confirmar == true){
			 return "Preço deletado com sucesso!";
		 }else {
			 return "Erro: O preço não pode ser deletado!";
		 }
	 }
}
