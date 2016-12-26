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
import br.com.pontosimetrico.interfaces.CadastroFornecedor;
import br.com.pontosimetrico.model.Fornecedor;
import br.com.pontosimetrico.util.Util;

public class FornecedorDAO implements CadastroFornecedor {
	
	private Connection connection;
		
	public FornecedorDAO() throws SQLException, ClassNotFoundException {
		this.connection = ConnectionFactory.getConnection();
	}

	@Override
	public Boolean cadastrar(Fornecedor fornecedor) throws SQLException {
		Util util = new Util();
		PreparedStatement statment = null;
		
		try {
			statment = this.connection.prepareStatement("insert into fornecedor ("
					+ "nome_fornecedor,"
					+ "razao_social_fornecedor,"
					+ "cnpj_fornecedor,"
					+ "inscr_estadual_fornecedor,"
					+ "logomarca_fornecedor,"
					+ "ramo_atividade_fornecedor,"
					+ "website_fornecedor,"
					+ "email_principal_fornecedor,"
					+ "data_cadastro_fornecedor,"
					+ "data_primeira_compra_fornecedor,"
					+ "data_ultima_compra_fornecedor,"
					+ "id_empresa_fornecedor) values(?,?,?,?,?,?,?,?,?,?,?,?)");
			
			statment.setString(1,  fornecedor.getNome());
			statment.setString(2,  fornecedor.getRazaoSocial());
			statment.setString(3,  fornecedor.getCnpj());
			statment.setString(4,  fornecedor.getInscricaoEstadual());
			statment.setBlob(5, (Blob) fornecedor.getLogomarca());
			statment.setString(6,  fornecedor.getRamoAtividade());
			statment.setString(7,  fornecedor.getWebsite());
			statment.setString(8,  fornecedor.getEmailPrincipal());
			statment.setString(9,  util.dateToString(fornecedor.getDataDeCadastro()));
			statment.setString(10, util.dateToString(fornecedor.getPrimeiraCompra()));
			statment.setString(11, util.dateToString(fornecedor.getUltimaCompra()));
			statment.setInt(12,    fornecedor.getIdEmpresa());
			
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
			statment = this.connection.prepareStatement("delete from fornecedor where id_fornecedor=" + id);
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
	public Boolean editar(Fornecedor fornecedor) throws SQLException {
		Util util = new Util();
		PreparedStatement statment = null;
		
		try {
			statment = this.connection.prepareStatement("update fornecedor set "
					+ "nome_fornecedor=?,"
					+ "razao_social_fornecedor=?,"
					+ "cnpj_fornecedor=?,"
					+ "inscr_estadual_fornecedor=?,"
					+ "logomarca_fornecedor=?,"
					+ "ramo_atividade_fornecedor=?,"
					+ "website_fornecedor=?,"
					+ "email_principal_fornecedor=?,"
					+ "data_cadastro_fornecedor=?,"
					+ "data_primeira_compra_fornecedor=?,"
					+ "data_ultima_compra_fornecedor=?,"
					+ "id_empresa_fornecedor=? where id_fornecedor=?");
			
			statment.setString(1,  fornecedor.getNome());
			statment.setString(2,  fornecedor.getRazaoSocial());
			statment.setString(3,  fornecedor.getCnpj());
			statment.setString(4,  fornecedor.getInscricaoEstadual());
			statment.setBlob(5, (Blob) fornecedor.getLogomarca());
			statment.setString(6,  fornecedor.getRamoAtividade());
			statment.setString(7,  fornecedor.getWebsite());
			statment.setString(8,  fornecedor.getEmailPrincipal());
			statment.setString(9,  util.dateToString(fornecedor.getDataDeCadastro()));
			statment.setString(10, util.dateToString(fornecedor.getPrimeiraCompra()));
			statment.setString(11, util.dateToString(fornecedor.getUltimaCompra()));
			statment.setInt(12,    fornecedor.getIdEmpresa());
			statment.setInt(13,    fornecedor.getId());
			
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
	public Fornecedor buscar(int id) throws SQLException {
		Fornecedor fornecedor      = new Fornecedor(); 
		ResultSet resultSet        = null;
		PreparedStatement statment = null;
		
		try {
			statment  = this.connection.prepareStatement("select * from fornecedor where id_fornecedor=" + id);
			resultSet = statment.executeQuery();
			resultSet.first();
			
			fornecedor.setId(resultSet.getInt("id_fornecedor"));
			fornecedor.setNome(resultSet.getString("nome_fornecedor"));
			fornecedor.setRazaoSocial(resultSet.getString("razao_social_fornecedor"));
			fornecedor.setCnpj(resultSet.getString("cnpj_fornecedor"));
			fornecedor.setInscricaoEstadual(resultSet.getString("inscr_estadual_fornecedor"));
			fornecedor.setLogomarca((Image) resultSet.getBlob("logomarca_fornecedor"));
			fornecedor.setRamoAtividade(resultSet.getString("ramo_atividade_fornecedor"));
			fornecedor.setWebsite(resultSet.getString("website_fornecedor"));
			fornecedor.setEmailPrincipal(resultSet.getString("email_principal_fornecedor"));
			fornecedor.setDataDeCadastro(resultSet.getDate("data_cadastro_fornecedor"));
			fornecedor.setPrimeiraCompra(resultSet.getDate("data_primeira_compra_fornecedor"));
			fornecedor.setUltimaCompra(resultSet.getDate("data_ultima_compra_fornecedor"));
			fornecedor.setIdEmpresa(resultSet.getInt("id_empresa_fornecedor"));
			
			resultSet.close();
			statment.close();			
			return fornecedor;
			
		} catch (Exception e) {
			e.printStackTrace();
			resultSet.close();
			statment.close();			
			return null;
		}
	}

	@Override
	public List<Fornecedor> listar(int IdEmpresa) throws SQLException {
		List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
		ResultSet resultSet           = null;
		PreparedStatement statment    = null;
		
		try {
			statment  = this.connection.prepareStatement("select * from fornecedor where id_empresa_fornecedor=" + IdEmpresa);
			resultSet = statment.executeQuery();
			
			while (resultSet.next()){
				Fornecedor fornecedor = new Fornecedor();
				fornecedor.setId(resultSet.getInt("id_fornecedor"));
				fornecedor.setNome(resultSet.getString("nome_fornecedor"));
				fornecedor.setRazaoSocial(resultSet.getString("razao_social_fornecedor"));
				fornecedor.setCnpj(resultSet.getString("cnpj_fornecedor"));
				fornecedor.setInscricaoEstadual(resultSet.getString("inscr_estadual_fornecedor"));
				fornecedor.setLogomarca((Image) resultSet.getBlob("logomarca_fornecedor"));
				fornecedor.setRamoAtividade(resultSet.getString("ramo_atividade_fornecedor"));
				fornecedor.setWebsite(resultSet.getString("website_fornecedor"));
				fornecedor.setEmailPrincipal(resultSet.getString("email_principal_fornecedor"));
				fornecedor.setDataDeCadastro(resultSet.getDate("data_cadastro_fornecedor"));
				fornecedor.setPrimeiraCompra(resultSet.getDate("data_primeira_compra_fornecedor"));
				fornecedor.setUltimaCompra(resultSet.getDate("data_ultima_compra_fornecedor"));
				fornecedor.setIdEmpresa(resultSet.getInt("id_empresa_fornecedor"));
				fornecedores.add(fornecedor);
			}
			
			resultSet.close();
			statment.close();			
			return fornecedores;
			
		} catch (Exception e) {
			e.printStackTrace();
			resultSet.close();
			statment.close();			
			return null;
		}
	}

}
