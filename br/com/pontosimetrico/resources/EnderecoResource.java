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

import br.com.pontosimetrico.DAO.EnderecoDAO;
import br.com.pontosimetrico.model.Endereco;

@Path("/endereco")
public class EnderecoResource {
	@GET
	@Produces("Application/json")
	@Path("/showList/{id_empresa}")
	public String listaEndereco(@PathParam("id_empresa")int id_empresa) throws SQLException, ClassNotFoundException{
		
		List<Endereco>  endereco     = new ArrayList<Endereco>();
		Gson            gson         = new Gson();
		EnderecoDAO     enderecoDAO  = new EnderecoDAO();
		
		endereco = enderecoDAO.listar(id_empresa);
		
		return gson.toJson(endereco);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/show/{id}")
	public String buscarEndereco(@PathParam("id") int id) throws SQLException, ClassNotFoundException{
		
		Endereco         endereco         = new Endereco();
		Gson             gson             = new Gson();
		EnderecoDAO      enderecoDAO      = new EnderecoDAO();				
		endereco = enderecoDAO.buscar(id);
		
		return gson.toJson(endereco);
	}
	
	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces("Application/json")
	 @Path("/create")
	 public String inserirEndereco(String json) throws SQLException, ClassNotFoundException{
		 Boolean      confirmar        = false;
		 Gson         gson             = new Gson();
		 EnderecoDAO  enderecoDAO      = new EnderecoDAO();
		 
		 if (json != null){
			 Endereco endereco = gson.fromJson(json, Endereco.class);
			 confirmar = enderecoDAO.cadastrar(endereco);			 
		 }
		 
		 if (confirmar == true){
			 return "Endereço cadastrado com sucesso!";
		 }else {
			 return "Erro: O endereço não foi cadastrado!";
		 }
			
	 }

	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces("Application/json")
	 @Path("/update")
	 public String alterarEndereco(String json) throws SQLException, ClassNotFoundException{
		 Boolean    confirmar  = false;
		 Gson       gson       = new Gson();
		 EnderecoDAO enderecoDAO     = new EnderecoDAO();
		 
		 if (json != null){
			 Endereco endereco = gson.fromJson(json, Endereco.class);
			 confirmar         = enderecoDAO.editar(endereco);			 
		 }
		 
		 if (confirmar == true){
			 return "Endereço editado com sucesso!";
		 }else {
			 return "Erro: O endereço não foi editado!";
		 }			
	 }

	 @GET
	 @Produces("Application/json")
	 @Path("/destroy/{id}")
	 public String deletarEndereco(@PathParam("id") int id) throws SQLException, ClassNotFoundException{	
		Boolean        confirmar      = false;
		EnderecoDAO  enderecoDAO  = new EnderecoDAO();
		
		confirmar = enderecoDAO.deletar(id);
		
		 if (confirmar == true){
			 return "Endereço deletado com sucesso!";
		 }else {
			 return "Erro: O endereço não pode ser deletado!";
		 }
	 }	
	
}
