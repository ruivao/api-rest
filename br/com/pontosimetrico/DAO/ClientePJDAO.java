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
import br.com.pontosimetrico.interfaces.CadastroClientePJ;
import br.com.pontosimetrico.model.ClientePessoaJuridica;
import br.com.pontosimetrico.util.Util;

public class ClientePJDAO implements CadastroClientePJ{
	
	private Connection connection;

	
	public ClientePJDAO() throws SQLException, ClassNotFoundException {
		this.connection = ConnectionFactory.getConnection();
	}

	@Override
	public Boolean cadastrar(ClientePessoaJuridica clientePessoaJuridica) throws SQLException {
		Util util = new Util();
		PreparedStatement statment = null;

		try {
			statment = this.connection.prepareStatement("insert into cliente_pj ("
					+ "nome_cliente_pj,"
					+ "razao_social_cliente_pj,"
					+ "cnpj_cliente_pj,"
					+ "inscr_estadual_cliente_pj,"
					+ "logomarca_cliente_pj,"
					+ "ramo_atividade_cliente_pj,"
					+ "website_cliente_pj,"
					+ "email_cliente_pj,"
					+ "data_cadastro_cliente_pj,"
					+ "credito_cliente_pj,"
					+ "bonus_cliente_pj,"
					+ "status_cliente_pj,"
					+ "id_empresa_cliente_pj) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			statment.setString(1,  clientePessoaJuridica.getNome());
			statment.setString(2,  clientePessoaJuridica.getRazaoSocial());
			statment.setString(3,  clientePessoaJuridica.getCnpj());
			statment.setString(4,  clientePessoaJuridica.getInscricaoEstadual());
			statment.setBlob(5, (Blob) clientePessoaJuridica.getLogomarca());
			statment.setString(6,  clientePessoaJuridica.getRamoAtividade());
			statment.setString(7,  clientePessoaJuridica.getWebsite());
			statment.setString(8,  clientePessoaJuridica.getEmailPrincipal());
			statment.setString(9,  util.dateToString(clientePessoaJuridica.getDataDeCadastro()));
			statment.setDouble(10, clientePessoaJuridica.getCredito());
			statment.setDouble(11, clientePessoaJuridica.getBonus());
			statment.setBoolean(12,clientePessoaJuridica.getStatus());
			statment.setInt(13,    clientePessoaJuridica.getId_empresa());
			
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
			statment = this.connection.prepareStatement("delete from cliente_pj where id_cliente_pj=" + id);
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
	public Boolean editar(ClientePessoaJuridica clientePessoaJuridica) throws SQLException {
		Util util = new Util();
		PreparedStatement statment = null;
		
		try {
			statment = this.connection.prepareStatement("update cliente_pj set "
					+ "nome_cliente_pj=?,"
					+ "razao_social_cliente_pj=?,"
					+ "cnpj_cliente_pj=?,"
					+ "inscr_estadual_cliente_pj=?,"
					+ "logomarca_cliente_pj=?,"
					+ "ramo_atividade_cliente_pj=?,"
					+ "website_cliente_pj=?,"
					+ "email_cliente_pj=?,"
					+ "data_cadastro_cliente_pj=?,"
					+ "credito_cliente_pj=?,"
					+ "bonus_cliente_pj=?,"
					+ "status_cliente_pj=?,"
					+ "id_empresa_cliente_pj=? where id_cliente_pj=?");
			
			statment.setString(1,  clientePessoaJuridica.getNome());
			statment.setString(2,  clientePessoaJuridica.getRazaoSocial());
			statment.setString(3,  clientePessoaJuridica.getCnpj());
			statment.setString(4,  clientePessoaJuridica.getInscricaoEstadual());
			statment.setBlob(5, (Blob) clientePessoaJuridica.getLogomarca());
			statment.setString(6,  clientePessoaJuridica.getRamoAtividade());
			statment.setString(7,  clientePessoaJuridica.getWebsite());
			statment.setString(8,  clientePessoaJuridica.getEmailPrincipal());
			statment.setString(9,  util.dateToString(clientePessoaJuridica.getDataDeCadastro()));
			statment.setDouble(10, clientePessoaJuridica.getCredito());
			statment.setDouble(11, clientePessoaJuridica.getBonus());
			statment.setBoolean(12,clientePessoaJuridica.getStatus());
			statment.setInt(13,    clientePessoaJuridica.getId_empresa());
			statment.setInt(14,    clientePessoaJuridica.getId());
			
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
	public ClientePessoaJuridica buscar(int id) throws SQLException {
		ClientePessoaJuridica clientePessoaJuridica  = new ClientePessoaJuridica(); 
		ResultSet resultSet        = null;
		PreparedStatement statment = null;
		
		try {
			statment  = this.connection.prepareStatement("select * from cliente_pj where id_cliente_pj=" + id);
			resultSet = statment.executeQuery();
			resultSet.first();
			
			clientePessoaJuridica.setId(resultSet.getInt("id_cliente_pj"));
			clientePessoaJuridica.setNome(resultSet.getString("nome_cliente_pj"));
			clientePessoaJuridica.setRazaoSocial(resultSet.getString("razao_social_cliente_pj"));
			clientePessoaJuridica.setCnpj(resultSet.getString("cnpj_cliente_pj"));
			clientePessoaJuridica.setInscricaoEstadual(resultSet.getString("inscr_estadual_cliente_pj"));
			clientePessoaJuridica.setLogomarca((Image) resultSet.getBlob("logomarca_cliente_pj"));
			clientePessoaJuridica.setRamoAtividade(resultSet.getString("ramo_atividade_cliente_pj"));
			clientePessoaJuridica.setWebsite(resultSet.getString("website_cliente_pj"));
			clientePessoaJuridica.setEmailPrincipal(resultSet.getString("email_cliente_pj"));
			clientePessoaJuridica.setDataDeCadastro(resultSet.getDate("data_cadastro_cliente_pj"));
			clientePessoaJuridica.setCredito(resultSet.getDouble("credito_cliente_pj"));
			clientePessoaJuridica.setBonus(resultSet.getDouble("bonus_cliente_pj"));
			clientePessoaJuridica.setStatus(resultSet.getBoolean("status_cliente_pj"));
			clientePessoaJuridica.setId_empresa(resultSet.getInt("id_empresa_cliente_pj"));
			
			resultSet.close();
			statment.close();			
			return clientePessoaJuridica;
			
		} catch (Exception e) {
			e.printStackTrace();
			resultSet.close();
			statment.close();			
			return null;
		}
	}

	@Override
	public List<ClientePessoaJuridica> listar(int idEmpresa) throws SQLException {
		List<ClientePessoaJuridica> clientesPessoaJuridica  = new ArrayList<ClientePessoaJuridica>();
		ResultSet resultSet           = null;
		PreparedStatement statment    = null;
		
		try {
			statment  = this.connection.prepareStatement("select * from cliente_pj where id_empresa=" + idEmpresa);
			resultSet = statment.executeQuery();
			
			while (resultSet.next()){
				ClientePessoaJuridica clientePessoaJuridica = new ClientePessoaJuridica();
				clientePessoaJuridica.setId(resultSet.getInt("id_cliente_pj"));
				clientePessoaJuridica.setNome(resultSet.getString("nome_cliente_pj"));
				clientePessoaJuridica.setRazaoSocial(resultSet.getString("razao_social_cliente_pj"));
				clientePessoaJuridica.setCnpj(resultSet.getString("cnpj_cliente_pj"));
				clientePessoaJuridica.setInscricaoEstadual(resultSet.getString("inscr_estadual_cliente_pj"));
				clientePessoaJuridica.setLogomarca((Image) resultSet.getBlob("logomarca_cliente_pj"));
				clientePessoaJuridica.setRamoAtividade(resultSet.getString("ramo_atividade_cliente_pj"));
				clientePessoaJuridica.setWebsite(resultSet.getString("website_cliente_pj"));
				clientePessoaJuridica.setEmailPrincipal(resultSet.getString("email_cliente_pj"));
				clientePessoaJuridica.setDataDeCadastro(resultSet.getDate("data_cadastro_cliente_pj"));
				clientePessoaJuridica.setCredito(resultSet.getDouble("credito_cliente_pj"));
				clientePessoaJuridica.setBonus(resultSet.getDouble("bonus_cliente_pj"));
				clientePessoaJuridica.setStatus(resultSet.getBoolean("status_cliente_pj"));
				clientePessoaJuridica.setId_empresa(resultSet.getInt("id_empresa_cliente_pj"));
				clientesPessoaJuridica.add(clientePessoaJuridica);
			}
			
			resultSet.close();
			statment.close();			
			return clientesPessoaJuridica;
			
		} catch (Exception e) {
			e.printStackTrace();
			resultSet.close();
			statment.close();			
			return null;
		}
	}

}
