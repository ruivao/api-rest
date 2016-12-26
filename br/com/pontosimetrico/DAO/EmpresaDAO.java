package br.com.pontosimetrico.DAO;

import java.awt.Image;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.pontosimetrico.factory.ConnectionFactory;
import br.com.pontosimetrico.interfaces.CadastroEmpresa;
import br.com.pontosimetrico.model.Empresa;
import br.com.pontosimetrico.util.Util;


public class EmpresaDAO implements CadastroEmpresa {
	
	private Connection connection;
	
		
	public EmpresaDAO() throws SQLException, ClassNotFoundException  {
		this.connection = ConnectionFactory.getConnection();
	}


	@Override
	public Boolean cadastrar(Empresa empresa) throws SQLException {
		Util util = new Util();
		PreparedStatement statment = null;
		
		try {
			statment = this.connection.prepareStatement("insert into empresa ("
					+ "nome_empresa,"
					+ "razao_social_empresa,"
					+ "cnpj_empresa,"
					+ "inscri_estadual_empresa,"
					+ "logomarca_empresa,"
					+ "ramo_atividade_empresa,"
					+ "website_empresa,"
					+ "email_principal_empresa,"
					+ "data_abertura_empresa,"
					+ "data_exercicio_empresa) values(?,?,?,?,?,?,?,?,?,?)");
			
			statment.setString(1,  empresa.getNome());
			statment.setString(2,  empresa.getRazaoSocial());
			statment.setString(3,  empresa.getCnpj());
			statment.setString(4,  empresa.getInscricaoEstadual());
			statment.setBlob(5, (Blob) empresa.getLogomarca());
			statment.setString(6,  empresa.getRamoAtividade());
			statment.setString(7,  empresa.getWebsite());
			statment.setString(8,  empresa.getEmailPrincipal());
			statment.setString(9,  util.dateToString(empresa.getDataAbertura()));
			statment.setString(10, util.dateToString(empresa.getDataExercicio()));
			
			statment.execute();
			statment.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			statment.close();
			return false;
		}		
	}


	@Override
	public Boolean deletar(int id) throws SQLException {
		PreparedStatement statment = null;
		
		try {
			statment = this.connection.prepareStatement("delete from empresa where id_empresa= " + id);
			statment.execute();
			statment.close();			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			statment.close();
			return false;
		}
	}


	@Override
	public Boolean editar(Empresa empresa) throws SQLException {		
		Util util = new Util();
		PreparedStatement statment = null;
		
		try {
			statment = this.connection.prepareStatement("update empresa set "
					+ "nome_empresa=?,"
					+ "razao_social_empresa=?,"
					+ "cnpj_empresa=?,"
					+ "inscri_estadual_empresa=?,"
					+ "logomarca_empresa=?,"
					+ "ramo_atividade_empresa=?,"
					+ "website_empresa=?,"
					+ "email_principal_empresa=?,"
					+ "data_abertura_empresa=?,"
					+ "data_exercicio_empresa=? where id_empresa=?");
			
			statment.setString(1,  empresa.getNome());
			statment.setString(2,  empresa.getRazaoSocial());
			statment.setString(3,  empresa.getCnpj());
			statment.setString(4,  empresa.getInscricaoEstadual());
			statment.setBlob(5, (Blob) empresa.getLogomarca());
			statment.setString(6,  empresa.getRamoAtividade());
			statment.setString(7,  empresa.getWebsite());
			statment.setString(8,  empresa.getEmailPrincipal());
			statment.setString(9,  util.dateToString(empresa.getDataAbertura()));
			statment.setString(10, util.dateToString(empresa.getDataExercicio()));
			statment.setInt(11,    empresa.getId());
			
			statment.execute();
			statment.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			statment.close();
			return false;
		}		
	}


	@Override
	public Empresa buscar(int id) throws SQLException {		
		Empresa   empresa          = new Empresa(); 
		ResultSet resultSet        = null;
		PreparedStatement statment = null;
		
		try {
			statment  = this.connection.prepareStatement("select * from empresa where id_empresa = " + id);
			resultSet = statment.executeQuery();
			resultSet.first();
					
			empresa.setId(resultSet.getInt("id_empresa"));
			empresa.setNome(resultSet.getString("nome_empresa"));
			empresa.setRazaoSocial(resultSet.getString("razao_social_empresa"));
			empresa.setCnpj(resultSet.getString("cnpj_empresa"));
			empresa.setInscricaoEstadual(resultSet.getString("inscri_estadual_empresa"));
			empresa.setLogomarca((Image) resultSet.getBlob("logomarca_empresa"));
			empresa.setRamoAtividade(resultSet.getString("ramo_atividade_empresa"));
			empresa.setWebsite(resultSet.getString("website_empresa"));
			empresa.setEmailPrincipal(resultSet.getString("email_principal_empresa"));
			empresa.setDataAbertura(resultSet.getDate("data_abertura_empresa"));
			empresa.setDataExercicio(resultSet.getDate("data_exercicio_empresa"));
			
			resultSet.close();
			statment.close();			
			return empresa;
			
		} catch (Exception e) {
			e.printStackTrace();
			resultSet.close();
			statment.close();			
			return null;
		}
	}


	@Override
	public List<Empresa> listar() throws SQLException {
		List<Empresa> listaEmpresas = new ArrayList<Empresa>();		
		ResultSet resultSet        = null;
		PreparedStatement statment = null;
			
		try {
			statment  = this.connection.prepareStatement("select * from empresa");
			resultSet = statment.executeQuery();
			
			while (resultSet.next()){
				Empresa empresa = new Empresa();
				empresa.setId(resultSet.getInt("id_empresa"));
				empresa.setNome(resultSet.getString("nome_empresa"));
				empresa.setRazaoSocial(resultSet.getString("razao_social_empresa"));
				empresa.setCnpj(resultSet.getString("cnpj_empresa"));
				empresa.setInscricaoEstadual(resultSet.getString("inscri_estadual_empresa"));
				empresa.setLogomarca((Image) resultSet.getBlob("logomarca_empresa"));
				empresa.setRamoAtividade(resultSet.getString("ramo_atividade_empresa"));
				empresa.setWebsite(resultSet.getString("website_empresa"));
				empresa.setEmailPrincipal(resultSet.getString("email_principal_empresa"));
				empresa.setDataAbertura(resultSet.getDate("data_abertura_empresa"));
				empresa.setDataExercicio(resultSet.getDate("data_exercicio_empresa"));
				listaEmpresas.add(empresa);    
			}
						
			resultSet.close();
			statment.close();			
			return listaEmpresas;
			
		} catch (Exception e) {
			e.printStackTrace();
			resultSet.close();
			statment.close();			
			return null;
		}
	}

}
