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

import br.com.pontosimetrico.DAO.FuncionarioDAO;
import br.com.pontosimetrico.model.Funcionario;

@Path("/funcionario")
public class FuncionarioResource {

	@GET
	@Produces("Application/json")
	@Path("/showList/{id_empresa}")
	public String listaFuncionario(@PathParam("id_empresa")int id_empresa) throws SQLException, ClassNotFoundException{
		
		List<Funcionario>  funcionarios   = new ArrayList<Funcionario>();
		Gson               gson           = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		FuncionarioDAO     funcionarioDAO = new FuncionarioDAO();
		funcionarios = funcionarioDAO.listar(id_empresa);
		
		return gson.toJson(funcionarios);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/show/{id}")
	public String buscarFuncionario(@PathParam("id") int id) throws SQLException, ClassNotFoundException{
		
		Funcionario    funcionario    = new Funcionario();
		Gson           gson           = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();				
		funcionario = funcionarioDAO.buscar(id);
		
		return gson.toJson(funcionario);
	}
	
	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces("Application/json")
	 @Path("/create")
	 public String inserirFuncionario(String json) throws SQLException, ClassNotFoundException{
		 Boolean    confirmar           = false;
		 Gson       gson                = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		 FuncionarioDAO  funcionarioDAO = new FuncionarioDAO();
		 
		 if (json != null){
			 Funcionario funcionario = gson.fromJson(json, Funcionario.class);
			 confirmar = funcionarioDAO.cadastrar(funcionario);			 
		 }
		 
		 if (confirmar == true){
			 return "Funcionario cadastrado com sucesso!";
		 }else {
			 return "Erro: O funcioario não foi cadastrado!";
		 }
			
	 }

	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces("Application/json")
	 @Path("/update")
	 public String alterarFuncionario(String json) throws SQLException, ClassNotFoundException{
		 Boolean        confirmar        = false;
		 Gson gson =  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		 FuncionarioDAO funcionarioDAO   = new FuncionarioDAO();
		 
		 if (json != null){
			 Funcionario funcionario = gson.fromJson(json, Funcionario.class);
			 confirmar       = funcionarioDAO.editar(funcionario);			 
		 }
		 
		 if (confirmar == true){
			 return "Funcionario editado com sucesso!";
		 }else {
			 return "Erro: O funcionario não foi editado!";
		 }
			
	 }

	 @GET
	 @Produces("Application/json")
	 @Path("/destroy/{id}")
	 public String deletarFuncionario(@PathParam("id") int id) throws SQLException, ClassNotFoundException{	
		Boolean        confirmar      = false;
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		
		confirmar = funcionarioDAO.deletar(id);
		
		 if (confirmar == true){
			 return "Funcionario deletado com sucesso!";
		 }else {
			 return "Erro: O funcionario não pode ser deletado!";
		 }
	 }
	
}
