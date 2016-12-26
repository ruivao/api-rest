package br.com.pontosimetrico.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.pontosimetrico.factory.ConnectionFactory;
import br.com.pontosimetrico.interfaces.CadastroUsuario;
import br.com.pontosimetrico.model.Usuario;
import br.com.pontosimetrico.util.Util;

public class UsuarioDAO implements CadastroUsuario {
	
	private Connection connection;
	
	public UsuarioDAO() throws ClassNotFoundException, SQLException {
		this.connection = ConnectionFactory.getConnection();
	}

	@Override
	public Boolean cadastrar(Usuario usuario) throws SQLException {
		Util util = new Util();
		PreparedStatement statment = null;

		try {
			statment = this.connection.prepareStatement("insert into usuario ("
					+ "id_empresa,"
					+ "nome_usuario,"
					+ "foto_usuario,"
					+ "login_usuario,"
					+ "senha_usuario,"
					+ "confirma_senha_usuario,"
					+ "online_usuario,"
					+ "data_cadastro_usuario,"
					+ "email_usuario,"
					+ "id_facebook,"
					+ "id_google,"
					+ "id_tweeter) values(?,?,?,?,?,?,?,?,?,?,?,?)");
			
			statment.setInt(1,     usuario.getId_empresa());
			statment.setString(2,  usuario.getNome());
			statment.setString(3,  usuario.getFoto_usuario());
			statment.setString(4,  usuario.getLogin());
			statment.setString(5,  usuario.getSenha());
			statment.setString(6,  usuario.getConfirma_senha());
			statment.setString(7,  util.dateToString(usuario.getDataDeCadastro()));
			statment.setString(8,  usuario.getEmail_usuario());
			statment.setBoolean(9, usuario.getOnline());
			statment.setInt(10,    usuario.getId_facebook());
			statment.setInt(11,    usuario.getId_google());
			statment.setInt(12,    usuario.getId_tweeter());
			
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
			statment = this.connection.prepareStatement("delete from usuario where id_usuario=" + id);
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
	public Boolean editar(Usuario usuario) throws SQLException {
		Util util = new Util();
		PreparedStatement statment = null;

		try {
			statment = this.connection.prepareStatement("update usuario set "
					+ "id_empresa=?,"
					+ "nome_usuario=?,"
					+ "foto_usuario=?,"
					+ "login_usuario=?,"
					+ "senha_usuario=?,"
					+ "confirma_senha_usuario=?,"
					+ "online_usuario=?,"
					+ "email_usuario=?,"
					+ "id_facebook=?,"
					+ "id_google=?,"
					+ "id_tweeter=?,"
					+ "data_cadastro_usuario=? where id_usuario=?");
			
			statment.setInt(1,     usuario.getId_empresa());
			statment.setString(2,  usuario.getNome());
			statment.setString(3,  usuario.getFoto_usuario());
			statment.setString(4,  usuario.getLogin());
			statment.setString(5,  usuario.getSenha());
			statment.setString(6,  usuario.getConfirma_senha());
			statment.setString(7,  util.dateToString(usuario.getDataDeCadastro()));
			statment.setString(8,  usuario.getEmail_usuario());
			statment.setBoolean(9, usuario.getOnline());
			statment.setInt(10,    usuario.getId_facebook());
			statment.setInt(11,    usuario.getId_google());
			statment.setInt(12,    usuario.getId_tweeter());
			statment.setInt(13,    usuario.getId());
			
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
	public Usuario buscar(int id) throws SQLException {
		Usuario usuario            = new Usuario(); 
		ResultSet resultSet        = null;
		PreparedStatement statment = null;

		try {
			statment  = this.connection.prepareStatement("select * from usuario where id_usuario=" + id);
			resultSet = statment.executeQuery();
			resultSet.first();
			
			usuario.setId(resultSet.getInt("id_usuario"));
			usuario.setId_empresa(resultSet.getInt("id_empresa"));
			usuario.setNome(resultSet.getString("nome_usuario"));
			usuario.setFoto_usuario(resultSet.getString("foto_usuario"));
			usuario.setLogin(resultSet.getString("login_usuario"));
			usuario.setSenha(resultSet.getString("senha_usuario"));
			usuario.setConfirma_senha(resultSet.getString("confirma_senha_usuario"));
			usuario.setDataDeCadastro(resultSet.getDate("data_cadastro_usuario"));
			usuario.setEmail_usuario(resultSet.getString("email_usuario"));
			usuario.setOnline(resultSet.getBoolean("online_usuario"));
			usuario.setId_facebook(resultSet.getInt("id_facebook"));
			usuario.setId_google(resultSet.getInt("id_google"));
			usuario.setId_tweeter(resultSet.getInt("id_tweeter"));
			
			resultSet.close();
			statment.close();			
			return usuario;
			
		} catch (Exception e) {
			e.printStackTrace();
			resultSet.close();
			statment.close();			
			return null;
		}
	}

	@Override
	public Usuario buscar(String login) throws SQLException {
		Usuario usuario            = new Usuario(); 
		ResultSet resultSet        = null;
		PreparedStatement statment = null;

		try {
			statment  = this.connection.prepareStatement("select * from usuario where login_usuario=" + login);
			resultSet = statment.executeQuery();
			resultSet.first();
			
			usuario.setId(resultSet.getInt("id_usuario"));
			usuario.setId_empresa(resultSet.getInt("id_empresa"));
			usuario.setNome(resultSet.getString("nome_usuario"));
			usuario.setFoto_usuario(resultSet.getString("foto_usuario"));
			usuario.setLogin(resultSet.getString("login_usuario"));
			usuario.setSenha(resultSet.getString("senha_usuario"));
			usuario.setConfirma_senha(resultSet.getString("confirma_senha_usuario"));
			usuario.setDataDeCadastro(resultSet.getDate("data_cadastro_usuario"));
			usuario.setEmail_usuario(resultSet.getString("email_usuario"));
			usuario.setOnline(resultSet.getBoolean("online_usuario"));
			usuario.setId_facebook(resultSet.getInt("id_facebook"));
			usuario.setId_google(resultSet.getInt("id_google"));
			usuario.setId_tweeter(resultSet.getInt("id_tweeter"));
			
			resultSet.close();
			statment.close();			
			return usuario;
			
		} catch (Exception e) {
			e.printStackTrace();
			resultSet.close();
			statment.close();			
			return null;
		}
	}
	
	
	@Override
	public List<Usuario> listar(int idEmpresa) throws SQLException {
		List<Usuario> usuarios           = new ArrayList<Usuario>();
		ResultSet resultSet           = null;
		PreparedStatement statment    = null;
		
		try {
			statment  = this.connection.prepareStatement("select * from usuario where id_empresa=" + idEmpresa);
			resultSet = statment.executeQuery();
			
			while (resultSet.next()){
				Usuario usuario = new Usuario();
				usuario.setId(resultSet.getInt("id_usuario"));
				usuario.setId_empresa(resultSet.getInt("id_empresa"));
				usuario.setNome(resultSet.getString("nome_usuario"));
				usuario.setFoto_usuario(resultSet.getString("foto_usuario"));
				usuario.setLogin(resultSet.getString("login_usuario"));
				usuario.setSenha(resultSet.getString("senha_usuario"));
				usuario.setConfirma_senha(resultSet.getString("confirma_senha_usuario"));
				usuario.setDataDeCadastro(resultSet.getDate("data_cadastro_usuario"));
				usuario.setEmail_usuario(resultSet.getString("email_usuario"));
				usuario.setOnline(resultSet.getBoolean("online_usuario"));
				usuario.setId_facebook(resultSet.getInt("id_facebook"));
				usuario.setId_google(resultSet.getInt("id_google"));
				usuario.setId_tweeter(resultSet.getInt("id_tweeter"));
				usuarios.add(usuario);
			}
			
			resultSet.close();
			statment.close();			
			return usuarios;
			
		} catch (Exception e) {
			e.printStackTrace();
			resultSet.close();
			statment.close();			
			return null;
		}
	}


}
