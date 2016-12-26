package br.com.pontosimetrico.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.pontosimetrico.factory.ConnectionFactory;
import br.com.pontosimetrico.interfaces.CadastroTelefone;
import br.com.pontosimetrico.model.Telefone;

public class TelefoneDAO implements CadastroTelefone{
	
	
	private Connection connection;
	

	public TelefoneDAO()throws SQLException, ClassNotFoundException {
		this.connection = ConnectionFactory.getConnection();
	}

	
	
	@Override
	public Boolean cadastrar(Telefone telefone) throws SQLException {
		PreparedStatement statment = null;

		try {
			statment = this.connection.prepareStatement("insert into telefone ("
					+ "descricao_telefone,"
					+ "codigo_ddi_telefone,"
					+ "codigo_ddd_telefone,"
					+ "numero_telefone,"
					+ "ramal_telefone) values(?,?,?,?,?)");
			
			statment.setString(1,  telefone.getDescricao());
			statment.setInt(2,     telefone.getCodigoDDI());			
			statment.setInt(3,     telefone.getCodigoDDD());
			statment.setString(4,  telefone.getNumeroTelefone());
			statment.setString(5,  telefone.getRamal());
			
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
			statment = this.connection.prepareStatement("delete from telefone where id_contato=" + id);
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
	public Boolean editar(Telefone telefone) throws SQLException {
		PreparedStatement statment = null;

		try {
			statment = this.connection.prepareStatement("update telefone set "
					+ "descricao_telefone=?,"
					+ "codigo_ddi_telefone=?,"
					+ "codigo_ddd_telefone=?,"
					+ "numero_telefone=?,"
					+ "ramal_telefone=? where id_telefone=?");
			
			statment.setString(1,  	telefone.getDescricao());
			statment.setInt(2,   	telefone.getCodigoDDI());
			statment.setInt(3,  	telefone.getCodigoDDD());
			statment.setString(4,  	telefone.getNumeroTelefone());
			statment.setString(5,  	telefone.getRamal());
			
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
	public Telefone buscar(int id) throws SQLException {
		Telefone  telefone         = new Telefone(); 
		ResultSet resultSet        = null;
		PreparedStatement statment = null;
		
		try {
			statment  = this.connection.prepareStatement("select * from telefone where id_telefone=" + id);
			resultSet = statment.executeQuery();
			resultSet.first();
			
			telefone.setId(resultSet.getInt("id_telefone"));
			telefone.setDescricao(resultSet.getString("descricao_telefone"));
			telefone.setCodigoDDI(resultSet.getInt("codigo_ddi_telefone"));
			telefone.setCodigoDDD(resultSet.getInt("codigo_ddd_telefone"));
			telefone.setNumeroTelefone(resultSet.getString("numero_telefone"));
			telefone.setRamal(resultSet.getString("ramal_telefone"));
			
			resultSet.close();
			statment.close();			
			return telefone;
			
		} catch (Exception e) {
			e.printStackTrace();
			resultSet.close();
			statment.close();			
			return null;
		}
	}

	@Override
	public List<Telefone> listar(int IdEmpresa) throws SQLException {
		List<Telefone> telefones         = new ArrayList<Telefone>();
		ResultSet resultSet              = null;
		PreparedStatement statment       = null;
		
		try {
			statment  = this.connection.prepareStatement("select * from telefone where id_telefone=" + IdEmpresa);
			resultSet = statment.executeQuery();
			
			while (resultSet.next()){
				Telefone telefone  = new Telefone();
				telefone.setId(resultSet.getInt("id_telefone"));
				telefone.setDescricao(resultSet.getString("descricao_telefone"));
				telefone.setCodigoDDI(resultSet.getInt("codigo_ddi_telefone"));
				telefone.setCodigoDDD(resultSet.getInt("codigo_ddd_telefone"));
				telefone.setNumeroTelefone(resultSet.getString("numero_telefone"));
				telefone.setRamal(resultSet.getString("ramal_telefone"));
				telefones.add(telefone);
			}
			
			resultSet.close();
			statment.close();			
			return telefones;
			
		} catch (Exception e) {
			e.printStackTrace();
			resultSet.close();
			statment.close();			
			return null;
		}
	}

}
