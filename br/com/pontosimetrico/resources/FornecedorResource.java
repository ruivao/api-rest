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

import br.com.pontosimetrico.DAO.FornecedorDAO;
import br.com.pontosimetrico.model.Fornecedor;


@Path("/fornecedor")
public class FornecedorResource {

	@GET
	@Produces("Application/json")
	@Path("/showList/{id_empresa}")
	public String listaFornecedor(@PathParam("id_empresa")int id_empresa) throws SQLException, ClassNotFoundException{		
		List<Fornecedor>  fornecedores = new ArrayList<Fornecedor>();
		Gson           gson            =  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		FornecedorDAO  fornecedorDAO   =  new FornecedorDAO();
		
		fornecedores = fornecedorDAO.listar(id_empresa);
		
		return gson.toJson(fornecedores);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/show/{id}")
	public String buscarFornecedor(@PathParam("id") int id) throws SQLException, ClassNotFoundException{
		
		Fornecedor     fornecedor     = new Fornecedor();
		Gson           gson           = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		FornecedorDAO  fornecedorDAO  = new FornecedorDAO();				
		fornecedor = fornecedorDAO.buscar(id);
		
		return gson.toJson(fornecedor);
	}
	
	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces("Application/json")
	 @Path("/create")
	 public String inserirFornecedor(String json) throws SQLException, ClassNotFoundException{
		 Boolean    confirmar        = false;
		 Gson           gson         =  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		 FornecedorDAO fornecedorDAO = new FornecedorDAO();
		 
		 if (json != null){
			 Fornecedor fornecedor = gson.fromJson(json, Fornecedor.class);
			 confirmar = fornecedorDAO.cadastrar(fornecedor);			 
		 }
		 
		 if (confirmar == true){
			 return "Fornecedor cadastrado com sucesso!";
		 }else {
			 return "Erro: O fornecedor não foi cadastrado!";
		 }
			
	 }

	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces("Application/json")
	 @Path("/update")
	 public String alterarFornecedor(String json) throws SQLException, ClassNotFoundException{
		 Boolean    confirmar  = false;
		 Gson       gson       =  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		 FornecedorDAO fornecedorDAO = new FornecedorDAO();
		 
		 if (json != null){
			 Fornecedor fornecedor = gson.fromJson(json, Fornecedor.class);
			 			 			 
			 confirmar       = fornecedorDAO.editar(fornecedor);			 
		 }
		 
		 if (confirmar == true){
			 return "Fornecedor editado com sucesso!";
		 }else {
			 return "Erro: O fornecedor não foi editado!";
		 }
			
	 }

		@GET
		@Produces("Application/json")
		@Path("/destroy/{id}")
		public String deletarFornecedor(@PathParam("id") int id) throws SQLException, ClassNotFoundException{	
			Boolean        confirmar      = false;
			FornecedorDAO  fornecedorDAO  = new FornecedorDAO();
			
			confirmar = fornecedorDAO.deletar(id);
			
			 if (confirmar == true){
				 return "Fornecedor deletado com sucesso!";
			 }else {
				 return "Erro: O fornecedor não pode ser deletado!";
			 }
		}

}
