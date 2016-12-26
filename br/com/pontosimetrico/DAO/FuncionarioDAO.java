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
import br.com.pontosimetrico.interfaces.CadastroFuncionario;
import br.com.pontosimetrico.model.Funcionario;
import br.com.pontosimetrico.util.Util;

public class FuncionarioDAO implements CadastroFuncionario {
	
	private Connection connection;
	
	
	public FuncionarioDAO() throws SQLException, ClassNotFoundException {
		this.connection = ConnectionFactory.getConnection();
	}
	
	
	@Override
	public Boolean cadastrar(Funcionario funcionario) throws SQLException {
		Util util = new Util();
		PreparedStatement statment = null;
		
		try {
			statment = this.connection.prepareStatement("insert into funcionario ("
					+ "nome_funcionario,"
					+ "sobrenome_funcionario,"
					+ "foto_funcionario,"
					+ "cpf_funcionario,"
					+ "rg_funcionario,"
					+ "sexo_funcionario,"
					+ "data_nascimento_funcionario,"
					+ "email_funcionario,"
					+ "matricula_funcionario,"
					+ "data_contratacao_funcionario,"
					+ "data_demissao_funcionario,"
					+ "cargo_funcionario,"
					+ "depto_funcionario,"
					+ "salario_base_funcionario,"
					+ "status_funcionario,"
					+ "id_empresa_funcionario,"
					+ "carteira_profissinal_funcionario) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			statment.setString(1,      funcionario.getNome());
			statment.setString(2,      funcionario.getSobreNome());
			statment.setBlob(3, (Blob) funcionario.getFoto());
			statment.setString(4,      funcionario.getCpf());
			statment.setString(5,      funcionario.getRg());
			statment.setInt(6,         funcionario.getSexo());
			statment.setString(7,      util.dateToString(funcionario.getDataNascimento()));
			statment.setString(8,      funcionario.getMatricula());
			statment.setString(9,      util.dateToString(funcionario.getDataDeContratacao()));
			statment.setString(10,     util.dateToString(funcionario.getDataDeDemissao()));
			statment.setString(11,     funcionario.getCargo());
			statment.setString(12,     funcionario.getDepartamento());
			statment.setString(13,     funcionario.getEmail());
			statment.setDouble(14,     funcionario.getSalarioBase());
			statment.setBoolean(15,    funcionario.getStatus());
			statment.setInt(16,        funcionario.getId_empresa());
			statment.setString(17,     funcionario.getNumCarteiraProf());
			
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
			statment = this.connection.prepareStatement("delete from funcionario where id_funcionario=" + id);
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
	public Boolean editar(Funcionario funcionario) throws SQLException {
		Util util = new Util();
		PreparedStatement statment = null;

		try {
			statment = this.connection.prepareStatement("update funcionario set "
					+ "nome_funcionario=?,"
					+ "sobrenome_funcionario=?,"
					+ "foto_funcionario=?,"
					+ "cpf_funcionario=?,"
					+ "rg_funcionario=?,"
					+ "sexo_funcionario=?,"
					+ "data_nascimento_funcionario=?,"
					+ "email_funcionario=?,"
					+ "matricula_funcionario=?,"
					+ "data_contratacao_funcionario=?,"
					+ "data_demissao_funcionario=?,"
					+ "cargo_funcionario=?,"
					+ "depto_funcionario=?,"
					+ "salario_base_funcionario=?,"
					+ "status_funcionario=?,"
					+ "id_empresa_funcionario=?,"
					+ "carteira_profissinal_funcionario=? where id_funcionario=?");
			
			statment.setString(1,      funcionario.getNome());
			statment.setString(2,      funcionario.getSobreNome());
			statment.setBlob(3, (Blob) funcionario.getFoto());
			statment.setString(4,      funcionario.getCpf());
			statment.setString(5,      funcionario.getRg());
			statment.setInt(6,         funcionario.getSexo());
			statment.setString(7,      util.dateToString(funcionario.getDataNascimento()));
			statment.setString(8,      funcionario.getMatricula());
			statment.setString(9,      util.dateToString(funcionario.getDataDeContratacao()));
			statment.setString(10,     util.dateToString(funcionario.getDataDeDemissao()));
			statment.setString(11,     funcionario.getCargo());
			statment.setString(12,     funcionario.getDepartamento());
			statment.setString(13,     funcionario.getEmail());
			statment.setDouble(14,     funcionario.getSalarioBase());
			statment.setBoolean(15,    funcionario.getStatus());
			statment.setInt(16,        funcionario.getId_empresa());
			statment.setString(17,     funcionario.getNumCarteiraProf());
			
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
	public Funcionario buscar(int id) throws SQLException {
		Funcionario funcionario    = new Funcionario(); 
		ResultSet resultSet        = null;
		PreparedStatement statment = null;
		
		try {
			statment  = this.connection.prepareStatement("select * from funcionario where id_funcionario=" + id);
			resultSet = statment.executeQuery();
			resultSet.first();
			
			funcionario.setId(resultSet.getInt("id_funcionario"));
			funcionario.setNome(resultSet.getString("nome_funcionario"));
			funcionario.setSobreNome(resultSet.getString("sobrenome_funcionario"));
			funcionario.setFoto((Image) resultSet.getBlob("foto_funcionario"));
			funcionario.setCpf(resultSet.getString("cpf_funcionario"));
			funcionario.setRg(resultSet.getString("rg_funcionario"));
			funcionario.setSexo(resultSet.getInt("sexo_funcionario"));
			funcionario.setDataNascimento(resultSet.getDate("data_nascimento_funcionario"));
			funcionario.setEmail(resultSet.getString("email_funcionario"));
			funcionario.setMatricula(resultSet.getString("matricula_funcionario"));
			funcionario.setDataDeContratacao(resultSet.getDate("data_contratacao_funcionario"));
			funcionario.setDataDeDemissao(resultSet.getDate("data_demissao_funcionario"));
			funcionario.setCargo(resultSet.getString("cargo_funcionario"));
			funcionario.setDepartamento(resultSet.getString("depto_funcionario"));
			funcionario.setSalarioBase(resultSet.getDouble("salario_base_funcionario"));
			funcionario.setStatus(resultSet.getBoolean("status_funcionario"));
			funcionario.setId_empresa(resultSet.getInt("id_empresa_funcionario"));
			funcionario.setNumCarteiraProf(resultSet.getString("carteira_profissinal_funcionario"));
			
			resultSet.close();
			statment.close();			
			return funcionario;
			
		} catch (Exception e) {
			e.printStackTrace();
			resultSet.close();
			statment.close();			
			return null;
		}
	}

	@Override
	public List<Funcionario> listar(int IdEmpresa) throws SQLException {
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		ResultSet resultSet            = null;
		PreparedStatement statment     = null;
		
		try {
			statment  = this.connection.prepareStatement("select * from funcionario where id_empresa=" + IdEmpresa);
			resultSet = statment.executeQuery();
			
			while (resultSet.next()){
				Funcionario  funcionario = new Funcionario();
				funcionario.setId(resultSet.getInt("id_funcionario"));
				funcionario.setNome(resultSet.getString("nome_funcionario"));
				funcionario.setSobreNome(resultSet.getString("sobrenome_funcionario"));
				funcionario.setFoto((Image) resultSet.getBlob("foto_funcionario"));
				funcionario.setCpf(resultSet.getString("cpf_funcionario"));
				funcionario.setRg(resultSet.getString("rg_funcionario"));
				funcionario.setSexo(resultSet.getInt("sexo_funcionario"));
				funcionario.setDataNascimento(resultSet.getDate("data_nascimento_funcionario"));
				funcionario.setEmail(resultSet.getString("email_funcionario"));
				funcionario.setMatricula(resultSet.getString("matricula_funcionario"));
				funcionario.setDataDeContratacao(resultSet.getDate("data_contratacao_funcionario"));
				funcionario.setDataDeDemissao(resultSet.getDate("data_demissao_funcionario"));
				funcionario.setCargo(resultSet.getString("cargo_funcionario"));
				funcionario.setDepartamento(resultSet.getString("depto_funcionario"));
				funcionario.setSalarioBase(resultSet.getDouble("salario_base_funcionario"));
				funcionario.setStatus(resultSet.getBoolean("status_funcionario"));
				funcionario.setId_empresa(resultSet.getInt("id_empresa_funcionario"));
				funcionario.setNumCarteiraProf(resultSet.getString("carteira_profissinal_funcionario"));
				funcionarios.add(funcionario);
			}
			
			resultSet.close();
			statment.close();			
			return funcionarios;
			
		} catch (Exception e) {
			e.printStackTrace();
			resultSet.close();
			statment.close();			
			return null;
		}
	}

}
