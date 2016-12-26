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

import br.com.pontosimetrico.DAO.EmpresaDAO;
import br.com.pontosimetrico.model.Empresa;

@Path("/empresa")
public class EmpresaResource {
	
	@GET
	@Produces("Application/json")
	@Path("/showList")
	public String listaEmpresa() throws SQLException, ClassNotFoundException{
		
		List<Empresa>  empresas    = new ArrayList<Empresa>();
		Gson           gson        =  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		EmpresaDAO     empresaDAO  = new EmpresaDAO();
		
		empresas = empresaDAO.listar();
		
		return gson.toJson(empresas);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/show/{id}")
	public String buscarEmpresa(@PathParam("id") int id) throws SQLException, ClassNotFoundException{	
		Empresa        empresa     = new Empresa();
		Gson           gson        =  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		EmpresaDAO     empresaDAO  = new EmpresaDAO();				
		empresa = empresaDAO.buscar(id);
		
		return gson.toJson(empresa);
	}
	
	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces("Application/json")
	 @Path("/create")
	 public String inserirEmpresa(String json) throws SQLException, ClassNotFoundException{
		 Boolean    confirmar  = false;
		 Gson gson =  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		 EmpresaDAO empresaDAO = new EmpresaDAO();
		 
		 if (json != null){
			 Empresa empresa = gson.fromJson(json, Empresa.class);
			 confirmar       = empresaDAO.cadastrar(empresa);			 
		 }
		 
		 if (confirmar == true){
			 return "Empresa cadastrado com sucesso!";
		 }else {
			 return "Erro: A empresa não foi cadastrada!";
		 }
			
	 }

	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces("Application/json")
	 @Path("/update")
	 public String alterarEmpresa(String json) throws SQLException, ClassNotFoundException{
		 Boolean    confirmar  = false;
		 Gson gson =  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		 EmpresaDAO empresaDAO = new EmpresaDAO();
		 
		 if (json != null){
			 Empresa empresa = gson.fromJson(json, Empresa.class);
			 confirmar       = empresaDAO.editar(empresa);			 
		 }
		 
		 if (confirmar == true){
			 return "Empresa editada com sucesso!";
		 }else {
			 return "Erro: A empresa não foi editada!";
		 }
			
	 }

		@GET
		@Produces("Application/json")
		@Path("/destroy/{id}")
		public String deletarEmpresa(@PathParam("id") int id) throws SQLException, ClassNotFoundException{	
			Boolean        confirmar   = false;
			EmpresaDAO     empresaDAO  = new EmpresaDAO();
			
			confirmar     = empresaDAO.deletar(id);
			
			 if (confirmar == true){
				 return "Empresa deletada com sucesso!";
			 }else {
				 return "Erro: A empresa não pode ser deletada!";
			 }
		}
	 
}
