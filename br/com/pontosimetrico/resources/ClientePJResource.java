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

import br.com.pontosimetrico.DAO.ClientePJDAO;
import br.com.pontosimetrico.model.ClientePessoaJuridica;

@Path("/cliente_pj")
public class ClientePJResource {
	
	@GET
	@Produces("Application/json")
	@Path("/showList/{id_empresa}")
	public String listaClientePessoaJuridica(@PathParam("id_empresa")int id_empresa) throws SQLException, ClassNotFoundException{
		
		List<ClientePessoaJuridica>  clientePessoaJuridica   = new ArrayList<ClientePessoaJuridica>();
		Gson               gson           = new Gson();
		ClientePJDAO       clientePJDAO   = new ClientePJDAO();
		clientePessoaJuridica = clientePJDAO.listar(id_empresa);
		
		return gson.toJson(clientePessoaJuridica);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/show/{id}")
	public String buscarClientePessoaFisica(@PathParam("id") int id) throws SQLException, ClassNotFoundException{
		
		ClientePessoaJuridica    clientePessoaJuridica    = new ClientePessoaJuridica();
		Gson           gson           = new Gson();
		ClientePJDAO   clientePJDAO   = new ClientePJDAO();				
		clientePessoaJuridica = clientePJDAO.buscar(id);
		
		return gson.toJson(clientePessoaJuridica);
	}
	
	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces("Application/json")
	 @Path("/create")
	 public String inserirClientePessoaJuridica(String json) throws SQLException, ClassNotFoundException{
		 Boolean    confirmar           = false;
		 Gson gson =  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		 ClientePJDAO  clientePJDAO     = new ClientePJDAO();
		 
		 if (json != null){
			 ClientePessoaJuridica clientePessoaJuridica = gson.fromJson(json, ClientePessoaJuridica.class);
			 confirmar = clientePJDAO.cadastrar(clientePessoaJuridica);			 
		 }
		 
		 if (confirmar == true){
			 return "Cliente pessoa jurídica cadastrada com sucesso!";
		 }else {
			 return "Erro: A cliente pessoa jurídica não foi cadastrada!";
		 }
			
	 }

	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces("Application/json")
	 @Path("/update")
	 public String alterarClientePessoaJuridica(String json) throws SQLException, ClassNotFoundException{
		 Boolean        confirmar        = false;
		 Gson gson =  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		 ClientePJDAO   clientePJDAO     = new ClientePJDAO();
		 
		 if (json != null){
			 ClientePessoaJuridica clientePessoaJuridica = gson.fromJson(json, ClientePessoaJuridica.class);
			 confirmar = clientePJDAO.editar(clientePessoaJuridica);			 
		 }
		 
		 if (confirmar == true){
			 return "Cliente pessoa jurídica editado com sucesso!";
		 }else {
			 return "Erro: O cliente pessoa jurídica não foi editado!";
		 }			
	 }

	 @GET
	 @Produces("Application/json")
	 @Path("/destroy/{id}")
	 public String deletarClientePessoaJuridica(@PathParam("id") int id) throws SQLException, ClassNotFoundException{	
		Boolean        confirmar      = false;
		ClientePJDAO clientePJDAO = new ClientePJDAO();
		
		confirmar = clientePJDAO.deletar(id);
		
		 if (confirmar == true){
			 return "Cliente pessoa jurídica deletado com sucesso!";
		 }else {
			 return "Erro: O cliente fessoa jurídica não pode ser deletado!";
		 }
	 }

}
