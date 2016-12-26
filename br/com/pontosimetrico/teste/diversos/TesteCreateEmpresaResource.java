package br.com.pontosimetrico.teste.diversos;

import java.sql.SQLException;
//import java.text.DateFormat;
import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;

import com.google.gson.Gson;

import br.com.pontosimetrico.model.Empresa;
import br.com.pontosimetrico.resources.EmpresaResource;

public class TesteCreateEmpresaResource {

	public static void main(String[] args) throws ParseException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		EmpresaResource empresaResource = new EmpresaResource();
		Empresa empresa = new Empresa();
		//Date  dataAbertura = new Date();
		//Date  dataExercicio = new Date();
		String json;
		/*
		DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		dataAbertura  = (Date)formatter.parse("1996-03-12");
		dataExercicio = (Date)formatter.parse("2016-01-01");
		
		empresa.setDataAbertura(dataAbertura);
		empresa.setDataExercicio(dataExercicio);*/
		empresa.setRazaoSocial("Plotagem Industrias S/A.");
		empresa.setCnpj("11261665000175");
		empresa.setInscricaoEstadual("isento");
		empresa.setRamoAtividade("Industria Grafica");
		empresa.setWebsite("www.plotagem.com.br");
		empresa.setEmailPrincipal("contato@plotagem.com.br");
		empresa.setNome("Plotagem");
		
		Gson g = new Gson();
		json = g.toJson(empresa,Empresa.class);
		
		//System.out.println(json);
		empresaResource.inserirEmpresa(json);
		

	}

}
