package br.com.pontosimetrico.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.pontosimetrico.factory.ConnectionFactory;
import br.com.pontosimetrico.interfaces.CadastroPreco;
import br.com.pontosimetrico.model.Preco;
import br.com.pontosimetrico.util.Util;


public class PrecoDAO implements CadastroPreco{
	
	private Connection connection;


	public PrecoDAO() throws SQLException, ClassNotFoundException {
		this.connection = ConnectionFactory.getConnection();
	}

	@Override
	public Boolean cadastrar(Preco preco) throws SQLException {
		Util util = new Util();
		PreparedStatement statment = null;

		try {
			statment = this.connection.prepareStatement("insert into preco ("
					+ "valor_preco,"
					+ "desconto_preco,"
					+ "validade_desconto_ini_preco,"
					+ "validade_desconto_fim_preco,"
					+ "validade_preco_ini_preco,"
					+ "validade_preco_fim_preco,"
					+ "id_empresa_preco,) values(?,?,?,?,?,?,?)");
			
			statment.setDouble(1,  preco.getValor());
			statment.setDouble(2,  preco.getDesconto());
			statment.setString(3,    util.dateToString(preco.getValidadeDescontoInicio()));
			statment.setString(4,    util.dateToString(preco.getValidadeDescontoFim()));
			statment.setString(5,    util.dateToString(preco.getValidadePrecoInicio()));
			statment.setString(6,    util.dateToString(preco.getValidadePrecoFim()));
			statment.setInt(7,     preco.getId_empresa());

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
			statment = this.connection.prepareStatement("delete from preco where id_preco=" + id);
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
	public Boolean editar(Preco preco) throws SQLException {
		Util util = new Util();
		PreparedStatement statment = null;
		
		try {
			statment = this.connection.prepareStatement("update preco set "
					+ "valor_preco=?,"
					+ "desconto_preco=?,"
					+ "validade_desconto_ini_preco=?,"
					+ "validade_desconto_fim_preco=?,"
					+ "validade_preco_ini_preco=?,"
					+ "validade_preco_fim_preco=?,"
					+ "id_empresa_preco=? where id_preco=?");
			
			statment.setDouble(1,  preco.getValor());
			statment.setDouble(2,  preco.getDesconto());
			statment.setString(3,    util.dateToString(preco.getValidadeDescontoInicio()));
			statment.setString(4,    util.dateToString(preco.getValidadeDescontoFim()));
			statment.setString(5,    util.dateToString(preco.getValidadePrecoInicio()));
			statment.setString(6,    util.dateToString(preco.getValidadePrecoFim()));
			statment.setInt(7,     preco.getId_empresa());
			statment.setInt(8,     preco.getId());
			
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
	public Preco buscar(int id) throws SQLException {
		Preco preco                = new Preco(); 
		ResultSet resultSet        = null;
		PreparedStatement statment = null;

		try {
			statment  = this.connection.prepareStatement("select * from preco where id_preco=" + id);
			resultSet = statment.executeQuery();
			resultSet.first();
			
			preco.setId(resultSet.getInt("id_preco"));
			preco.setValor(resultSet.getDouble("valor_preco"));
			preco.setDesconto(resultSet.getDouble("desconto_preco"));
			preco.setValidadeDescontoInicio(resultSet.getDate("validade_desconto_ini_preco"));
			preco.setValidadeDescontoFim(resultSet.getDate("validade_desconto_fim_preco"));
			preco.setValidadePrecoInicio(resultSet.getDate("validade_preco_ini_preco"));
			preco.setValidadePrecoFim(resultSet.getDate("validade_preco_fim_preco"));
			preco.setId_empresa(resultSet.getInt("id_empresa_preco"));
			
			resultSet.close();
			statment.close();			
			return preco;
			
		} catch (Exception e) {
			e.printStackTrace();
			resultSet.close();
			statment.close();			
			return null;
		}
	}

	@Override
	public List<Preco> listar(int IdEmpresa) throws SQLException {
		List<Preco>  precos           = new ArrayList<Preco>();
		ResultSet resultSet           = null;
		PreparedStatement statment    = null;
		
		try {
			statment  = this.connection.prepareStatement("select * from preco where id_empresa=" + IdEmpresa);
			resultSet = statment.executeQuery();
			
			while (resultSet.next()){
				Preco preco = new Preco();
				preco.setId(resultSet.getInt("id_preco"));
				preco.setValor(resultSet.getDouble("valor_preco"));
				preco.setDesconto(resultSet.getDouble("desconto_preco"));
				preco.setValidadeDescontoInicio(resultSet.getDate("validade_desconto_ini_preco"));
				preco.setValidadeDescontoFim(resultSet.getDate("validade_desconto_fim_preco"));
				preco.setValidadePrecoInicio(resultSet.getDate("validade_preco_ini_preco"));
				preco.setValidadePrecoFim(resultSet.getDate("validade_preco_fim_preco"));
				preco.setId_empresa(resultSet.getInt("id_empresa_preco"));
				precos.add(preco);
			}
			
			resultSet.close();
			statment.close();			
			return precos;
			
		} catch (Exception e) {
			e.printStackTrace();
			resultSet.close();
			statment.close();			
			return null;
		}
	}

}
