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

import br.com.pontosimetrico.DAO.ProdutoDAO;
import br.com.pontosimetrico.model.Produto;


@Path("/produto")
public class ProdutoResource {

	@GET
	@Produces("Application/json")
	@Path("/showList/{id_empresa}")
	public String listaProduto(@PathParam("id_empresa")int id_empresa) throws SQLException, ClassNotFoundException{
		
		List<Produto>  produto     = new ArrayList<Produto>();
		Gson           gson        = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		ProdutoDAO     produtoDAO  = new ProdutoDAO();
		
		produto = produtoDAO.listar(id_empresa);
		
		return gson.toJson(produto);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/show/{id}")
	public String buscarProduto(@PathParam("id") int id) throws SQLException, ClassNotFoundException{
		
		Produto         produto         = new Produto();
		Gson            gson            = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		ProdutoDAO      produtoDAO      = new ProdutoDAO();				
		produto = produtoDAO.buscar(id);
		
		return gson.toJson(produto);
	}
	
	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces("Application/json")
	 @Path("/create")
	 public String inserirproduto(String json) throws SQLException, ClassNotFoundException{
		 Boolean    confirmar        = false;
		 Gson gson =  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		 ProdutoDAO  produtoDAO      = new ProdutoDAO();
		 
		 if (json != null){
			 Produto produto = gson.fromJson(json, Produto.class);
			 confirmar = produtoDAO.cadastrar(produto);			 
		 }
		 
		 if (confirmar == true){
			 return "Produto cadastrado com sucesso!";
		 }else {
			 return "Erro: O produto não foi cadastrado!";
		 }
			
	 }

	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces("Application/json")
	 @Path("/update")
	 public String alterarProduto(String json) throws SQLException, ClassNotFoundException{
		 Boolean    confirmar  = false;
		 Gson gson =  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		 ProdutoDAO produtoDAO     = new ProdutoDAO();
		 
		 if (json != null){
			 Produto produto = gson.fromJson(json, Produto.class);
			 confirmar       = produtoDAO.editar(produto);			 
		 }
		 
		 if (confirmar == true){
			 return "Produto editado com sucesso!";
		 }else {
			 return "Erro: O produto não foi editado!";
		 }			
	 }

	 @GET
	 @Produces("Application/json")
	 @Path("/destroy/{id}")
	 public String deletarProduto(@PathParam("id") int id) throws SQLException, ClassNotFoundException{	
		Boolean        confirmar      = false;
		ProdutoDAO  produtoDAO  = new ProdutoDAO();
		
		confirmar = produtoDAO.deletar(id);
		
		 if (confirmar == true){
			 return "Produto deletado com sucesso!";
		 }else {
			 return "Erro: O produto não pode ser deletado!";
		 }
	 }

}
