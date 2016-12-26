package br.com.pontosimetrico.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.pontosimetrico.factory.ConnectionFactory;
import br.com.pontosimetrico.interfaces.CadastroContato;
import br.com.pontosimetrico.model.Contato;


public class ContatoDAO implements CadastroContato{
	
	
	private Connection connection;
	
	public ContatoDAO() throws SQLException, ClassNotFoundException {
		this.connection = ConnectionFactory.getConnection();
	}

	
	@Override
	public Boolean cadastrar(Contato contato) throws SQLException {		
		PreparedStatement statment = null;
		
		try {
			statment = this.connection.prepareStatement("insert into contato ("
					+ "nome_contato_contato,"
					+ "depto_contato,"
					+ "email_contato) values(?,?,?)");
			
			statment.setString(1,  contato.getNome());
			statment.setString(2,  contato.getDepartamento());			
			statment.setString(3,  contato.getEmail());
			
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
			statment = this.connection.prepareStatement("delete from contato where id_contato=" + id);
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
	public Boolean editar(Contato contato) throws SQLException {
		PreparedStatement statment = null;

		try {
			statment = this.connection.prepareStatement("update contato set "
					+ "nome_contato_contato=?,"
					+ "depto_contato=?,"
					+ "email_contato=? where id_contato=?");
			
			statment.setString(1,  contato.getNome());
			statment.setString(2,  contato.getDepartamento());
			statment.setString(3,  contato.getEmail());
			
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
	public Contato buscar(int id) throws SQLException {
		Contato contato            = new Contato(); 
		ResultSet resultSet        = null;
		PreparedStatement statment = null;
		
		try {
			statment  = this.connection.prepareStatement("select * from contato where id_contato=" + id);
			resultSet = statment.executeQuery();
			resultSet.first();
			
			contato.setId(resultSet.getInt("id_contato"));
			contato.setNome(resultSet.getString("nome_contato_contato"));
			contato.setDepartamento(resultSet.getString("depto_contato"));
			contato.setEmail(resultSet.getString("email_contato"));
			
			resultSet.close();
			statment.close();			
			return contato;
			
		} catch (Exception e) {
			e.printStackTrace();
			resultSet.close();
			statment.close();			
			return null;
		}
	}

	@Override
	public List<Contato> listar(int IdEmpresa) throws SQLException {
		List<Contato> contatos           = new ArrayList<Contato>();
		ResultSet resultSet              = null;
		PreparedStatement statment       = null;
		
		try {
			statment  = this.connection.prepareStatement("select * from contato where id_empresa=" + IdEmpresa);
			resultSet = statment.executeQuery();
			
			while (resultSet.next()){
				Contato contato = new Contato();
				contato.setId(resultSet.getInt("id_contato"));
				contato.setNome(resultSet.getString("nome_contato_contato"));
				contato.setDepartamento(resultSet.getString("depto_contato"));
				contato.setEmail(resultSet.getString("email_contato"));				
				contatos.add(contato);
			}
			
			resultSet.close();
			statment.close();			
			return contatos;
			
		} catch (Exception e) {
			e.printStackTrace();
			resultSet.close();
			statment.close();			
			return null;
		}
	}

}
