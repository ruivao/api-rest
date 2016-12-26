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
import br.com.pontosimetrico.interfaces.CadastroSocios;
import br.com.pontosimetrico.model.Socios;
import br.com.pontosimetrico.util.Util;

public class SociosDAO implements CadastroSocios{

	private Connection connection;
	
	public SociosDAO() throws SQLException, ClassNotFoundException {
		this.connection = ConnectionFactory.getConnection();
	}

	
	@Override
	public Boolean cadastrar(Socios socios) throws SQLException {
		Util util = new Util();
		PreparedStatement statment = null;
		
		try {
			statment = this.connection.prepareStatement("insert into socios ("
					+ "nome_socios,"
					+ "sobrenome_socios,"
					+ "foto_socios,"
					+ "cpf_socios,"
					+ "rg_socios,"
					+ "sexo_socios,"
					+ "data_nasc_socios,"
					+ "email_socios,"
					+ "valor_cota_socios,"
					+ "valor_prolabore_socios,"
					+ "id_empresa_socios,"
					+ "data_associacao_socios) values(?,?,?,?,?,?,?,?,?,?,?)");
			
			statment.setString(1,  socios.getNome());
			statment.setString(2,  socios.getSobreNome());
			statment.setBlob(3,  (Blob) socios.getFoto());
			statment.setString(4,  socios.getCpf());
			statment.setString(5,  socios.getRg());
			statment.setInt(6,     socios.getSexo());
			statment.setString(7,  util.dateToString(socios.getDataNascimento()));
			statment.setString(8,  socios.getEmail());
			statment.setDouble(9,  socios.getValorDeCota());
			statment.setDouble(10, socios.getValorProlabore());
			statment.setInt(11,    socios.getEmpresa());
			statment.setString(12, util.dateToString(socios.getDataDeAssociacao()));
			
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
			statment = this.connection.prepareStatement("delete from socios where id_socios=" + id);
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
	public Boolean editar(Socios socios) throws SQLException {
		Util util = new Util();
		PreparedStatement statment = null;
		
		try {
			statment = this.connection.prepareStatement("update socios set "
					+ "nome_socios=?,"
					+ "sobrenome_socios=?,"
					+ "foto_socios=?,"
					+ "cpf_socios=?,"
					+ "rg_socios=?,"
					+ "sexo_socios=?,"
					+ "data_nasc_socios=?,"
					+ "email_socios=?,"
					+ "valor_cota_socios=?,"
					+ "valor_prolabore_socios=?,"
					+ "id_empresa_socios=?,"
					+ "data_associacao_socios=? where id_socios=?");
			
			statment.setString(1,  socios.getNome());
			statment.setString(2,  socios.getSobreNome());
			statment.setBlob(3,  (Blob) socios.getFoto());
			statment.setString(4,  socios.getCpf());
			statment.setString(5,  socios.getRg());
			statment.setInt(6,     socios.getSexo());
			statment.setString(7,  util.dateToString(socios.getDataNascimento()));
			statment.setString(8,  socios.getEmail());
			statment.setDouble(9,(Double) socios.getValorDeCota());
			statment.setDouble(10,(Double) socios.getValorProlabore());
			statment.setInt(11,    socios.getEmpresa());
			statment.setString(12, util.dateToString(socios.getDataDeAssociacao()));
			statment.setInt(13,    socios.getId());
			
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
	public Socios buscar(int id) throws SQLException {
		Socios socios              = new Socios(); 
		ResultSet resultSet        = null;
		PreparedStatement statment = null;
		
		try {
			statment  = this.connection.prepareStatement("select * from socios where id_socios=" + id);
			resultSet = statment.executeQuery();
			resultSet.first();
			
			socios.setId(resultSet.getInt("id_socios"));
			socios.setNome(resultSet.getString("nome_socios"));
			socios.setSobreNome(resultSet.getString("sobrenome_socios"));
			socios.setFoto((Image) resultSet.getBlob("foto_socios"));
			socios.setCpf(resultSet.getString("cpf_socios"));
			socios.setRg(resultSet.getString("rg_socios"));
			socios.setSexo(resultSet.getInt("sexo_socios"));
			socios.setDataNascimento(resultSet.getDate("data_nasc_socios"));
			socios.setEmail(resultSet.getString("email_socios"));
			socios.setValorDeCota(resultSet.getDouble("valor_cota_socios"));
			socios.setValorProlabore(resultSet.getDouble("valor_prolabore_socios"));
			socios.setEmpresa(resultSet.getInt("id_empresa_socios"));
			socios.setDataDeAssociacao(resultSet.getDate("data_associacao_socios"));
			
			resultSet.close();
			statment.close();			
			return socios;
			
		} catch (Exception e) {
			e.printStackTrace();
			resultSet.close();
			statment.close();			
			return null;
		}
	}

	@Override
	public List<Socios> listar(int idEmpresa) throws SQLException {
		List<Socios> socios           = new ArrayList<Socios>();
		ResultSet resultSet           = null;
		PreparedStatement statment    = null;
		
		try {
			statment  = this.connection.prepareStatement("select * from socios where id_empresa_socios=" + idEmpresa);
			resultSet = statment.executeQuery();
			
			while (resultSet.next()){
				Socios socio  = new Socios();
				socio.setId(resultSet.getInt("id_socios"));
				socio.setNome(resultSet.getString("nome_socios"));
				socio.setSobreNome(resultSet.getString("sobrenome_socios"));
				socio.setFoto((Image) resultSet.getBlob("foto_socios"));
				socio.setCpf(resultSet.getString("cpf_socios"));
				socio.setRg(resultSet.getString("rg_socios"));
				socio.setSexo(resultSet.getInt("sexo_socios"));
				socio.setDataNascimento(resultSet.getDate("data_nasc_socios"));
				socio.setEmail(resultSet.getString("email_socios"));
				socio.setValorDeCota(resultSet.getDouble("valor_cota_socios"));
				socio.setValorProlabore(resultSet.getDouble("valor_prolabore_socios"));
				socio.setEmpresa(resultSet.getInt("id_empresa_socios"));
				socio.setDataDeAssociacao(resultSet.getDate("data_associacao_socios"));
				socios.add(socio);
			}
			
			resultSet.close();
			statment.close();			
			return socios;
			
		} catch (Exception e) {
			e.printStackTrace();
			resultSet.close();
			statment.close();			
			return null;
		}
	}

}
