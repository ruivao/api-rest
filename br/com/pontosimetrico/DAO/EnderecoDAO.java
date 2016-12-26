package br.com.pontosimetrico.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.pontosimetrico.factory.ConnectionFactory;
import br.com.pontosimetrico.interfaces.CadastroEndereco;
import br.com.pontosimetrico.model.Endereco;

public class EnderecoDAO implements CadastroEndereco {
	
	private Connection connection;
	

	public EnderecoDAO()throws SQLException, ClassNotFoundException {
		this.connection = ConnectionFactory.getConnection();
	}

	@Override
	public Boolean cadastrar(Endereco endereco) throws SQLException {
		PreparedStatement statment = null;

		try {
			statment = this.connection.prepareStatement("insert into endereco ("
					+ "descricao_endereco,"
					+ "logradouro_endereco,"
					+ "endereco_endereco,"
					+ "numero_endereco,"
					+ "complemento_endereco,"
					+ "bairro_endereco,"
					+ "cidade_endereco,"
					+ "estado_endereco,"
					+ "cep_endereco) values(?,?,?,?,?,?,?,?,?)");
			
			statment.setString(1,  endereco.getDescricao());
			statment.setString(2,  endereco.getLogradouro());			
			statment.setString(3,  endereco.getNomeDaVia());
			statment.setInt(4,     endereco.getNumero());
			statment.setString(5,  endereco.getComplemento());
			statment.setString(6,  endereco.getBairro());
			statment.setString(7,  endereco.getCidade());
			statment.setString(8,  endereco.getEstado());
			statment.setString(9,  endereco.getCep());
			
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
			statment = this.connection.prepareStatement("delete from endereco where id_endereco=" + id);
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
	public Boolean editar(Endereco endereco) throws SQLException {
		PreparedStatement statment = null;

		try {
			statment = this.connection.prepareStatement("update endereco set "
					+ "descricao_endereco=?,"
					+ "logradouro_endereco=?,"
					+ "endereco_endereco=?,"
					+ "numero_endereco=?,"
					+ "complemento_endereco=?,"
					+ "bairro_endereco=?,"
					+ "cidade_endereco=?,"
					+ "estado_endereco=?,"
					+ "cep_endereco=? where id_endereco=?");
			
			statment.setString(1,  endereco.getDescricao());
			statment.setString(2,  endereco.getLogradouro());			
			statment.setString(3,  endereco.getNomeDaVia());
			statment.setInt(4,     endereco.getNumero());
			statment.setString(5,  endereco.getComplemento());
			statment.setString(6,  endereco.getBairro());
			statment.setString(7,  endereco.getCidade());
			statment.setString(8,  endereco.getEstado());
			statment.setString(9,  endereco.getCep());
			statment.setInt(10,    endereco.getId());
			
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
	public Endereco buscar(int id) throws SQLException {
		Endereco  endereco         = new Endereco(); 
		ResultSet resultSet        = null;
		PreparedStatement statment = null;

		try {
			statment  = this.connection.prepareStatement("select * from endereco where id_endereco=" + id);
			resultSet = statment.executeQuery();
			resultSet.first();
			
			endereco.setId(resultSet.getInt("id_endereco"));
			endereco.setDescricao(resultSet.getString("descricao_endereco"));
			endereco.setLogradouro(resultSet.getString("logradouro_endereco"));
			endereco.setNomeDaVia(resultSet.getString("endereco_endereco"));
			endereco.setNumero(resultSet.getInt("numero_endereco"));
			endereco.setComplemento(resultSet.getString("complemento_endereco"));
			endereco.setBairro(resultSet.getString("bairro_endereco"));
			endereco.setCidade(resultSet.getString("cidade_endereco"));
			endereco.setEstado(resultSet.getString("estado_endereco"));
			endereco.setCep(resultSet.getString("cep_endereco"));
			
			resultSet.close();
			statment.close();			
			return endereco;
			
		} catch (Exception e) {
			e.printStackTrace();
			resultSet.close();
			statment.close();			
			return null;
		}
	}

	@Override
	public List<Endereco> listar(int idEmpresa) throws SQLException {
		List<Endereco> enderecos         = new ArrayList<Endereco>();
		ResultSet resultSet              = null;
		PreparedStatement statment       = null;
		
		try {
			statment  = this.connection.prepareStatement("select * from endereco where id_endereco=" + idEmpresa);
			resultSet = statment.executeQuery();
			
			while (resultSet.next()){
				Endereco endereco = new Endereco();
				endereco.setId(resultSet.getInt("id_endereco"));
				endereco.setDescricao(resultSet.getString("descricao_endereco"));
				endereco.setLogradouro(resultSet.getString("logradouro_endereco"));
				endereco.setNomeDaVia(resultSet.getString("endereco_endereco"));
				endereco.setNumero(resultSet.getInt("numero_endereco"));
				endereco.setComplemento(resultSet.getString("complemento_endereco"));
				endereco.setBairro(resultSet.getString("bairro_endereco"));
				endereco.setCidade(resultSet.getString("cidade_endereco"));
				endereco.setEstado(resultSet.getString("estado_endereco"));
				endereco.setCep(resultSet.getString("cep_endereco"));
				enderecos.add(endereco);
			}
			
			resultSet.close();
			statment.close();			
			return enderecos;
			
		} catch (Exception e) {
			e.printStackTrace();
			resultSet.close();
			statment.close();			
			return null;
		}
	}

}
