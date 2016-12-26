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

import br.com.pontosimetrico.DAO.ClientePFDAO;
import br.com.pontosimetrico.model.ClientePessoaFisica;


@Path("/cliente_pf")
public class ClientePFResource {

	@GET
	@Produces("Application/json")
	@Path("/showList/{id_empresa}")
	public String listaClientePessoaFisica(@PathParam("id_empresa")int id_empresa) throws SQLException, ClassNotFoundException{
		
		List<ClientePessoaFisica>  clientePessoaFisica   = new ArrayList<ClientePessoaFisica>();
		Gson               gson           =  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		ClientePFDAO       clientePFDAO   = new ClientePFDAO();
		clientePessoaFisica = clientePFDAO.listar(id_empresa);
		
		return gson.toJson(clientePessoaFisica);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/show/{id}")
	public String buscarClientePessoaFisica(@PathParam("id") int id) throws SQLException, ClassNotFoundException{
		
		ClientePessoaFisica    clientePessoaFisica    = new ClientePessoaFisica();
		Gson           gson           =  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		ClientePFDAO   clientePFDAO   = new ClientePFDAO();				
		clientePessoaFisica = clientePFDAO.buscar(id);
		
		return gson.toJson(clientePessoaFisica);
	}
	
	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces("Application/json")
	 @Path("/create")
	 public String inserirClientePessoaFisica(String json) throws SQLException, ClassNotFoundException{
		 Boolean       confirmar        =  false;
		 Gson          gson             =  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		 ClientePFDAO  clientePFDAO     =  new ClientePFDAO();
		 
		 if (json != null){
			 ClientePessoaFisica clientePessoaFisica = gson.fromJson(json, ClientePessoaFisica.class);
			 confirmar = clientePFDAO.cadastrar(clientePessoaFisica);			 
		 }
		 
		 if (confirmar == true){
			 return "Cliente pessoa física cadastrada com sucesso!";
		 }else {
			 return "Erro: A cliente pessoa física não foi cadastrada!";
		 }
			
	 }

	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces("Application/json")
	 @Path("/update")
	 public String alterarClientePessoaFisica(String json) throws SQLException, ClassNotFoundException{
		 Boolean        confirmar        = false;
		 Gson           gson             = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		 ClientePFDAO   clientePFDAO     = new ClientePFDAO();
		 
		 if (json != null){
			 ClientePessoaFisica clientePessoaFisica = gson.fromJson(json, ClientePessoaFisica.class);
			 confirmar = clientePFDAO.editar(clientePessoaFisica);			 
		 }
		 
		 if (confirmar == true){
			 return "Cliente pessoa física editado com sucesso!";
		 }else {
			 return "Erro: O cliente pessoa física não foi editado!";
		 }			
	 }

	 @GET
	 @Produces("Application/json")
	 @Path("/destroy/{id}")
	 public String deletarClientePessoaFisica(@PathParam("id") int id) throws SQLException, ClassNotFoundException{	
		Boolean        confirmar      = false;
		ClientePFDAO   clientePFDAO   = new ClientePFDAO();
		
		confirmar = clientePFDAO.deletar(id);
		
		 if (confirmar == true){
			 return "Cliente pessoa física deletado com sucesso!";
		 }else {
			 return "Erro: O cliente fessoa fisica não pode ser deletado!";
		 }
	 }
}
