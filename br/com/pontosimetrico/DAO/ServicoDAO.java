package br.com.pontosimetrico.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.pontosimetrico.factory.ConnectionFactory;
import br.com.pontosimetrico.interfaces.CadastroServico;
import br.com.pontosimetrico.model.Servico;
import br.com.pontosimetrico.util.Util;

public class ServicoDAO implements CadastroServico{
	
	private Connection connection;

	public ServicoDAO() throws SQLException, ClassNotFoundException {
		this.connection = ConnectionFactory.getConnection();
	}
	
	
	@Override
	public Boolean cadastrar(Servico servico) throws SQLException {
		Util util = new Util();
		PreparedStatement statment = null;
		
		try {
			statment = this.connection.prepareStatement("insert into servico ("
					+ "descricao_servico,"
					+ "data_cadastro_servico,"
					+ "id_preco_servico,"
					+ "id_empresa_servico,"
					+ "tipo_cobranca_servico) values(?,?,?,?,?)");
			
			statment.setString(1,  servico.getDescricao());
			statment.setString(2,    util.dateToString(servico.getDataDeCadastro()));
			statment.setInt(3,     servico.getIdPreco());
			statment.setInt(4,     servico.getIdEmpresa());
			statment.setString(5,  servico.getTipoCobranca());

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
			statment = this.connection.prepareStatement("delete from servico where id_servico=" + id);
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
	public Boolean editar(Servico servico) throws SQLException {
		Util util = new Util();
		PreparedStatement statment = null;
		
		try {
			statment = this.connection.prepareStatement("update servico set "
					+ "descricao_servico=?,"
					+ "data_cadastro_servico=?,"
					+ "id_preco_servico=?,"
					+ "id_empresa_servico=?,"
					+ "tipo_cobranca_servico=? where id_servico=?");
			
			statment.setString(1,  servico.getDescricao());
			statment.setString(2,  util.dateToString(servico.getDataDeCadastro()));
			statment.setInt(3,     servico.getIdPreco());
			statment.setInt(4,     servico.getIdEmpresa());
			statment.setString(5,  servico.getTipoCobranca());
			
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
	public Servico buscar(int id) throws SQLException {
		Servico servico            = new Servico(); 
		ResultSet resultSet        = null;
		PreparedStatement statment = null;
		
		try {
			statment  = this.connection.prepareStatement("select * from servico where id_servico=" + id);
			resultSet = statment.executeQuery();
			resultSet.first();
			
			servico.setId(resultSet.getInt("id_servico"));
			servico.setDescricao(resultSet.getString("descricao_servico"));
			servico.setDataDeCadastro(resultSet.getDate("data_cadastro_servico"));
			servico.setIdPreco(resultSet.getInt("id_preco_servico"));
			servico.setIdEmpresa(resultSet.getInt("id_empresa_servico"));
			servico.setTipoCobranca(resultSet.getString("tipo_cobranca_servico"));
									
			resultSet.close();
			statment.close();			
			return servico;
			
		} catch (Exception e) {
			e.printStackTrace();
			resultSet.close();
			statment.close();			
			return null;
		}
	}

	@Override
	public List<Servico> listar(int idEmpresa) throws SQLException {
		List<Servico> servicos        = new ArrayList<Servico>();
		ResultSet resultSet           = null;
		PreparedStatement statment    = null;
		
		try {
			statment  = this.connection.prepareStatement("select * from servico where id_servico=" + idEmpresa);
			resultSet = statment.executeQuery();
			
			while (resultSet.next()){
				Servico servico  = new Servico();
				servico.setId(resultSet.getInt("id_servico"));
				servico.setDescricao(resultSet.getString("descricao_servico"));
				servico.setDataDeCadastro(resultSet.getDate("data_cadastro_servico"));
				servico.setIdPreco(resultSet.getInt("id_preco_servico"));
				servico.setIdEmpresa(resultSet.getInt("id_empresa_servico"));
				servico.setTipoCobranca(resultSet.getString("tipo_cobranca_servico"));
				servicos.add(servico);
			}
			
			resultSet.close();
			statment.close();			
			return servicos;
			
		} catch (Exception e) {
			e.printStackTrace();
			resultSet.close();
			statment.close();			
			return null;
		}
	}

}
