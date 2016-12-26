package br.com.pontosimetrico.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.pontosimetrico.factory.ConnectionFactory;
import br.com.pontosimetrico.interfaces.CadastroProduto;
import br.com.pontosimetrico.model.Produto;
import br.com.pontosimetrico.util.Util;

public class ProdutoDAO implements CadastroProduto {
	
	private Connection connection;
	

	public ProdutoDAO() throws SQLException, ClassNotFoundException{
		this.connection = ConnectionFactory.getConnection();
	}

	
	@Override
	public Boolean cadastrar(Produto produto) throws SQLException {
		Util util = new Util();
		PreparedStatement statment = null;

		try {
			statment = this.connection.prepareStatement("insert into produto ("
					+ "descricao_produto,"
					+ "peso_produto,"
					+ "volume_produto,"
					+ "id_preco_produto,"
					+ "data_cadastro_produto,"
					+ "id_empresa_produto) values(?,?,?,?,?,?)");
			
			statment.setString(1,  produto.getDescricao());
			statment.setDouble(2,  produto.getPeso());
			statment.setDouble(3,  produto.getVolume());
			statment.setInt(4,     produto.getIdPreco());
			statment.setString(5,  util.dateToString(produto.getDataDeCadastro()));
			statment.setInt(6,     produto.getIdEmpresa());

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
			statment = this.connection.prepareStatement("delete from produto where id_produto=" + id);
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
	public Boolean editar(Produto produto) throws SQLException {
		Util util = new Util();
		PreparedStatement statment = null;
		
		try {
			statment = this.connection.prepareStatement("update produto set "
					+ "descricao_produto=?,"
					+ "peso_produto=?,"
					+ "volume_produto=?,"
					+ "id_preco_produto=?,"
					+ "data_cadastro_produto=?,"
					+ "id_empresa_produto=? where id_produto=?");
			
			statment.setString(1,  produto.getDescricao());
			statment.setDouble(2,  produto.getPeso());
			statment.setDouble(3,  produto.getVolume());
			statment.setInt(4,     produto.getIdPreco());
			statment.setString(5,  util.dateToString(produto.getDataDeCadastro()));
			statment.setInt(6,     produto.getIdEmpresa());
			statment.setInt(7,     produto.getId());
			
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
	public Produto buscar(int id) throws SQLException {
		Produto produto            = new Produto(); 
		ResultSet resultSet        = null;
		PreparedStatement statment = null;
		
		try {
			statment  = this.connection.prepareStatement("select * from preco where id_produto=" + id);
			resultSet = statment.executeQuery();
			resultSet.first();
			
			produto.setId(resultSet.getInt("id_produto"));
			produto.setDescricao(resultSet.getString("descricao_produto"));
			produto.setPeso(resultSet.getDouble("peso_produto"));
			produto.setVolume(resultSet.getDouble("volume_produto"));
			produto.setIdPreco(resultSet.getInt("id_preco_produto"));
			produto.setDataDeCadastro(resultSet.getDate("data_cadastro_produto"));
			produto.setIdEmpresa(resultSet.getInt("id_empresa_produto"));
						
			resultSet.close();
			statment.close();			
			return produto;
			
		} catch (Exception e) {
			e.printStackTrace();
			resultSet.close();
			statment.close();			
			return null;
		}
	}

	@Override
	public List<Produto> listar(int IdEmpresa) throws SQLException {
		List<Produto> produtos        = new ArrayList<Produto>();
		ResultSet resultSet           = null;
		PreparedStatement statment    = null;
		
		try {
			statment  = this.connection.prepareStatement("select * from preco where id_empresa=" + IdEmpresa);
			resultSet = statment.executeQuery();
			
			while (resultSet.next()){
				Produto produto = new Produto();
				produto.setId(resultSet.getInt("id_produto"));
				produto.setDescricao(resultSet.getString("descricao_produto"));
				produto.setPeso(resultSet.getDouble("peso_produto"));
				produto.setVolume(resultSet.getDouble("volume_produto"));
				produto.setIdPreco(resultSet.getInt("id_preco_produto"));
				produto.setDataDeCadastro(resultSet.getDate("data_cadastro_produto"));
				produto.setIdEmpresa(resultSet.getInt("id_empresa_produto"));
				produtos.add(produto);
			}
			
			resultSet.close();
			statment.close();			
			return produtos;
			
		} catch (Exception e) {
			e.printStackTrace();
			resultSet.close();
			statment.close();			
			return null;
		}
	}

}
