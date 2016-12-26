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

import br.com.pontosimetrico.DAO.LoginDAO;
import br.com.pontosimetrico.model.Login;


@Path("/login")
public class LoginResource {

 	@GET
	@Produces("Application/json")
	@Path("/showList/{id_empresa}")
	public String listarLogin(@PathParam("id_empresa")int id_empresa) throws SQLException, ClassNotFoundException{
		
		List<Login>  login     = new ArrayList<Login>();
		Gson         gson      = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		LoginDAO     loginDAO  = new LoginDAO();
		
		login = loginDAO.listar(id_empresa);
		
		return gson.toJson(login);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/show/{id}")
	public String buscarLogin(@PathParam("id") int id) throws SQLException, ClassNotFoundException{
		
		Login         login         = new Login();
		Gson          gson          = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		LoginDAO      loginDAO      = new LoginDAO();				
		login = loginDAO.buscar(id);
		
		return gson.toJson(login);
	}
	
	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces("Application/json")
	 @Path("/create")
	 public String inserirlogin(String json) throws SQLException, ClassNotFoundException{
		 Boolean    confirmar        = false;
		 Gson gson =  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		 LoginDAO  loginDAO      = new LoginDAO();
		 
		 if (json != null){
			 Login login = gson.fromJson(json, Login.class);
			 confirmar = loginDAO.cadastrar(login);			 
		 }
		 
		 if (confirmar == true){
			 return "Login cadastrado com sucesso!";
		 }else {
			 return "Erro: O login não foi cadastrado!";
		 }
	 }

	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces("Application/json")
	 @Path("/update")
	 public String alterarLogin(String json) throws SQLException, ClassNotFoundException{
		 Boolean    confirmar  = false;
		 Gson gson =  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		 LoginDAO loginDAO     = new LoginDAO();
		 
		 if (json != null){
			 Login login = gson.fromJson(json, Login.class);
			 confirmar       = loginDAO.editar(login);			 
		 }
		 
		 if (confirmar == true){
			 return "Login editado com sucesso!";
		 }else {
			 return "Erro: O login não foi editado!";
		 }			
	 }

	 @GET
	 @Produces("Application/json")
	 @Path("/destroy/{id}")
	 public String deletarlogin(@PathParam("id") int id) throws SQLException, ClassNotFoundException{	
		Boolean        confirmar      = false;
		LoginDAO  loginDAO  = new LoginDAO();
		
		confirmar = loginDAO.deletar(id);
		
		 if (confirmar == true){
			 return "Login deletado com sucesso!";
		 }else {
			 return "Erro: O login não pode ser deletado!";
		 }
	 }
	
}
