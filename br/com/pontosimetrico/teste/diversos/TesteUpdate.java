package br.com.pontosimetrico.teste.diversos;

import java.sql.SQLException;
import java.text.ParseException;

import br.com.pontosimetrico.DAO.EmpresaDAO;
import br.com.pontosimetrico.model.Empresa;
import br.com.pontosimetrico.util.Util;

public class TesteUpdate {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
		Empresa empresa = new Empresa();
		EmpresaDAO empresaDAO = new EmpresaDAO();
		Util util = new Util();
		
		empresa = empresaDAO.buscar(3);		
		
		empresa.setDataAbertura(util.stringToDate("1997-11-22"));
		empresa.setDataExercicio(util.stringToDate("2016-01-01"));
		
		empresaDAO.editar(empresa);
		
	}
}
