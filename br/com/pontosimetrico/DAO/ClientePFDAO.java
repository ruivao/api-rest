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
import br.com.pontosimetrico.interfaces.CadastroClientePF;
import br.com.pontosimetrico.model.ClientePessoaFisica;
import br.com.pontosimetrico.util.Util;

public class ClientePFDAO implements CadastroClientePF{
	
	private Connection connection;
	
		
	public ClientePFDAO() throws SQLException, ClassNotFoundException {
		this.connection = ConnectionFactory.getConnection();
	}

	
	@Override
	public Boolean cadastrar(ClientePessoaFisica clientePessoaFisica) throws SQLException {
		Util util = new Util();
		PreparedStatement statment = null;
		
		try {
			statment = this.connection.prepareStatement("insert into cliente_pf ("
					+ "nome_cliente_pf,"
					+ "sobrenome_cliente_pf,"
					+ "foto_cliente_pf,"
					+ "cpf_cliente_pf,"
					+ "rg_cliente_pf,"
					+ "sexo_cliente_pf,"
					+ "data_nascimento_cliente_pf,"
					+ "email_cliente_pf,"
					+ "data_cadastro_cliente_pf,"
					+ "credito_cliente_pf,"
					+ "bonus_cliente_pf,"
					+ "status_cliente_pf,"
					+ "id_empresa_cliente_pf) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			statment.setString(1,      clientePessoaFisica.getNome());
			statment.setString(2,      clientePessoaFisica.getSobreNome());
			statment.setBlob(3, (Blob) clientePessoaFisica.getFoto());
			statment.setString(4,      clientePessoaFisica.getCpf());
			statment.setString(5,      clientePessoaFisica.getRg());
			statment.setInt(6,         clientePessoaFisica.getSexo());
			statment.setString(7,      util.dateToString(clientePessoaFisica.getDataNascimento()));
			statment.setString(8,      clientePessoaFisica.getEmail());
			statment.setString(9,      util.dateToString(clientePessoaFisica.getDataDeCadastro()));
			statment.setDouble(10,     clientePessoaFisica.getCredito());
			statment.setDouble(11,     clientePessoaFisica.getBonus());
			statment.setBoolean(12,    clientePessoaFisica.getStatus());
			statment.setInt(13,        clientePessoaFisica.getId_Empresa());
			
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
			statment = this.connection.prepareStatement("delete from cliente_pf where id_cliente_pf=" + id);
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
	public Boolean editar(ClientePessoaFisica clientePessoaFisica) throws SQLException {
		Util util = new Util();
		PreparedStatement statment = null;

		try {
			statment = this.connection.prepareStatement("update cliente_pf set "
					+ "nome_cliente_pf=?,"
					+ "sobrenome_cliente_pf=?,"
					+ "foto_cliente_pf=?,"
					+ "cpf_cliente_pf=?,"
					+ "rg_cliente_pf=?,"
					+ "sexo_cliente_pf=?,"
					+ "data_nascimento_cliente_pf=?,"
					+ "email_cliente_pf=?,"
					+ "data_cadastro_cliente_pf=?,"
					+ "credito_cliente_pf=?,"
					+ "bonus_cliente_pf=?,"
					+ "status_cliente_pf=?,"
					+ "id_empresa_cliente_pf=? where id_cliente_pf=?");
			
			statment.setString(1,      clientePessoaFisica.getNome());
			statment.setString(2,      clientePessoaFisica.getSobreNome());
			statment.setBlob(3, (Blob) clientePessoaFisica.getFoto());
			statment.setString(4,      clientePessoaFisica.getCpf());
			statment.setString(5,      clientePessoaFisica.getRg());
			statment.setInt(6,         clientePessoaFisica.getSexo());
			statment.setString(7,      util.dateToString(clientePessoaFisica.getDataNascimento()));
			statment.setString(8,      clientePessoaFisica.getEmail());
			statment.setString(9,      util.dateToString(clientePessoaFisica.getDataDeCadastro()));
			statment.setDouble(10,     clientePessoaFisica.getCredito());
			statment.setDouble(11,     clientePessoaFisica.getBonus());
			statment.setBoolean(12,    clientePessoaFisica.getStatus());
			statment.setInt(13,        clientePessoaFisica.getId_Empresa());
			statment.setInt(14,        clientePessoaFisica.getId());
			
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
	public ClientePessoaFisica buscar(int id) throws SQLException {
		ClientePessoaFisica clientePessoaFisica   = new ClientePessoaFisica(); 
		ResultSet resultSet        = null;
		PreparedStatement statment = null;
		
		try {
			statment  = this.connection.prepareStatement("select * from cliente_pf where id_cliente_pf=" + id);
			resultSet = statment.executeQuery();
			resultSet.first();
			
			clientePessoaFisica.setId(resultSet.getInt("id_cliente_pf"));
			clientePessoaFisica.setNome(resultSet.getString("nome_cliente_pf"));
			clientePessoaFisica.setSobreNome(resultSet.getString("sobrenome_cliente_pf"));
			clientePessoaFisica.setFoto((Image) resultSet.getBlob("foto_cliente_pf"));
			clientePessoaFisica.setCpf(resultSet.getString("cpf_cliente_pf"));
			clientePessoaFisica.setRg(resultSet.getString("rg_cliente_pf"));
			clientePessoaFisica.setSexo(resultSet.getInt("sexo_cliente_pf"));
			clientePessoaFisica.setDataNascimento(resultSet.getDate("data_nascimento_cliente_pf"));
			clientePessoaFisica.setEmail(resultSet.getString("email_cliente_pf"));
			clientePessoaFisica.setDataDeCadastro(resultSet.getDate("data_cadastro_cliente_pf"));
			clientePessoaFisica.setCredito(resultSet.getDouble("credito_cliente_pf"));
			clientePessoaFisica.setBonus(resultSet.getDouble("bonus_cliente_pf"));
			clientePessoaFisica.setStatus(resultSet.getBoolean("status_cliente_pf"));
			clientePessoaFisica.setId_Empresa(resultSet.getInt("id_empresa_cliente_pf"));
			
			resultSet.close();
			statment.close();			
			return clientePessoaFisica;
			
		} catch (Exception e) {
			e.printStackTrace();
			resultSet.close();
			statment.close();			
			return null;
		}
	}

	@Override
	public List<ClientePessoaFisica> listar(int IdEmpresa) throws SQLException {
		List<ClientePessoaFisica> clientesPessoaFisica = new ArrayList<ClientePessoaFisica>();
		ResultSet resultSet            = null;
		PreparedStatement statment     = null;
		
		try {
			statment  = this.connection.prepareStatement("select * from cliente_pf where id_empresa=" + IdEmpresa);
			resultSet = statment.executeQuery();
			
			while (resultSet.next()){
				ClientePessoaFisica  clientePessoaFisica = new ClientePessoaFisica();
				clientePessoaFisica.setId(resultSet.getInt("id_cliente_pf"));
				clientePessoaFisica.setNome(resultSet.getString("nome_cliente_pf"));
				clientePessoaFisica.setSobreNome(resultSet.getString("sobrenome_cliente_pf"));
				clientePessoaFisica.setFoto((Image) resultSet.getBlob("foto_cliente_pf"));
				clientePessoaFisica.setCpf(resultSet.getString("cpf_cliente_pf"));
				clientePessoaFisica.setRg(resultSet.getString("rg_cliente_pf"));
				clientePessoaFisica.setSexo(resultSet.getInt("sexo_cliente_pf"));
				clientePessoaFisica.setDataNascimento(resultSet.getDate("data_nascimento_cliente_pf"));
				clientePessoaFisica.setEmail(resultSet.getString("email_cliente_pf"));
				clientePessoaFisica.setDataDeCadastro(resultSet.getDate("data_cadastro_cliente_pf"));
				clientePessoaFisica.setCredito(resultSet.getDouble("credito_cliente_pf"));
				clientePessoaFisica.setBonus(resultSet.getDouble("bonus_cliente_pf"));
				clientePessoaFisica.setStatus(resultSet.getBoolean("status_cliente_pf"));
				clientePessoaFisica.setId_Empresa(resultSet.getInt("id_empresa_cliente_pf"));
				clientesPessoaFisica.add(clientePessoaFisica);
			}
			
			resultSet.close();
			statment.close();			
			return clientesPessoaFisica;
			
		} catch (Exception e) {
			e.printStackTrace();
			resultSet.close();
			statment.close();			
			return null;
		}
	}

}
