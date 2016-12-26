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

import br.com.pontosimetrico.DAO.ServicoDAO;
import br.com.pontosimetrico.model.Servico;


public class ServicoResource {
	
	@GET
	@Produces("Application/json")
	@Path("/showList/{id_empresa}")
	public String listaServico(@PathParam("id_empresa")int id_empresa) throws SQLException, ClassNotFoundException{
		
		List<Servico>  servico     = new ArrayList<Servico>();
		Gson           gson        = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		ServicoDAO     servicoDAO  = new ServicoDAO();
		
		servico = servicoDAO.listar(id_empresa);
		
		return gson.toJson(servico);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/show/{id}")
	public String buscarServico(@PathParam("id") int id) throws SQLException, ClassNotFoundException{
		
		Servico         servico         = new Servico();
		Gson            gson            = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		ServicoDAO      servicoDAO      = new ServicoDAO();				
		servico = servicoDAO.buscar(id);
		
		return gson.toJson(servico);
	}
	
	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces("Application/json")
	 @Path("/create")
	 public String inserirServico(String json) throws SQLException, ClassNotFoundException{
		 Boolean    confirmar        = false;
		 Gson gson =  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		 ServicoDAO  servicoDAO      = new ServicoDAO();
		 
		 if (json != null){
			 Servico servico = gson.fromJson(json, Servico.class);
			 confirmar = servicoDAO.cadastrar(servico);			 
		 }
		 
		 if (confirmar == true){
			 return "Serviço cadastrado com sucesso!";
		 }else {
			 return "Erro: O serviço não foi cadastrado!";
		 }
			
	 }

	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces("Application/json")
	 @Path("/update")
	 public String alterarServico(String json) throws SQLException, ClassNotFoundException{
		 Boolean    confirmar  = false;
		 Gson gson =  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		 ServicoDAO servicoDAO     = new ServicoDAO();
		 
		 if (json != null){
			 Servico servico = gson.fromJson(json, Servico.class);
			 confirmar       = servicoDAO.editar(servico);			 
		 }
		 
		 if (confirmar == true){
			 return "Serviço editado com sucesso!";
		 }else {
			 return "Erro: O serviço não foi editado!";
		 }			
	 }

	 @GET
	 @Produces("Application/json")
	 @Path("/destroy/{id}")
	 public String deletarServico(@PathParam("id") int id) throws SQLException, ClassNotFoundException{	
		Boolean        confirmar      = false;
		ServicoDAO  servicoDAO  = new ServicoDAO();
		
		confirmar = servicoDAO.deletar(id);
		
		 if (confirmar == true){
			 return "Serviço deletado com sucesso!";
		 }else {
			 return "Erro: O serviço não pode ser deletado!";
		 }
	 }	
}
