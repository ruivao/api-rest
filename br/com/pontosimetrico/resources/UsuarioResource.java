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

import br.com.pontosimetrico.DAO.UsuarioDAO;
import br.com.pontosimetrico.model.Usuario;


@Path("/usuario")
public class UsuarioResource {
	@GET
	@Produces("Application/json")
	@Path("/showList/{id_empresa}")
	public String listaUsuario(@PathParam("id_empresa")int id_empresa) throws SQLException, ClassNotFoundException{
		
		List<Usuario>  usuario     = new ArrayList<Usuario>();
		Gson           gson      = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		UsuarioDAO     UsuarioDAO = new UsuarioDAO();
		
		usuario = UsuarioDAO.listar(id_empresa);
		
		return gson.toJson(usuario);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/show/{id}")
	public String buscarUsuario(@PathParam("id") int id) throws SQLException, ClassNotFoundException{
		
		Usuario         usuario         = new Usuario();
		Gson            gson            = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		UsuarioDAO      usuarioDAO      = new UsuarioDAO();				
		usuario = usuarioDAO.buscar(id);
		
		return gson.toJson(usuario);
	}
	
	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces("Application/json")
	 @Path("/create")
	 public String inserirUsuario(String json) throws SQLException, ClassNotFoundException{
		 Boolean    confirmar        = false;
		 Gson gson =  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		 UsuarioDAO  usuarioDAO        = new UsuarioDAO();
		 
		 if (json != null){
			 Usuario usuario = gson.fromJson(json, Usuario.class);
			 confirmar = usuarioDAO.cadastrar(usuario);			 
		 }
		 
		 if (confirmar == true){
			 return "Usuário cadastrado com sucesso!";
		 }else {
			 return "Erro: O usuário não foi cadastrado!";
		 }
			
	 }

	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces("Application/json")
	 @Path("/update")
	 public String alterarUsuario(String json) throws SQLException, ClassNotFoundException{
		 Boolean    confirmar  = false;
		 Gson       gson       =  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		 UsuarioDAO usuarioDAO =  new UsuarioDAO();
		 
		 if (json != null){
			 Usuario usuario = gson.fromJson(json, Usuario.class);
			 confirmar       = usuarioDAO.editar(usuario);			 
		 }
		 
		 if (confirmar == true){
			 return "Usuario editado com sucesso!";
		 }else {
			 return "Erro: O usário não foi editado!";
		 }			
	 }

	 @GET
	 @Produces("Application/json")
	 @Path("/destroy/{id}")
	 public String deletarUsuario(@PathParam("id") int id) throws SQLException, ClassNotFoundException{	
		Boolean        confirmar      = false;
		UsuarioDAO     usuarioDAO     = new UsuarioDAO();
		
		confirmar = usuarioDAO.deletar(id);
		
		 if (confirmar == true){
			 return "Usuário deletado com sucesso!";
		 }else {
			 return "Erro: O usuário não pode ser deletado!";
		 }
	 }

}
