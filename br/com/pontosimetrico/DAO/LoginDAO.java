package br.com.pontosimetrico.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.pontosimetrico.factory.ConnectionFactory;
import br.com.pontosimetrico.interfaces.CadastroLogin;
import br.com.pontosimetrico.model.Login;
import br.com.pontosimetrico.model.Usuario;
import br.com.pontosimetrico.util.Util;

public class LoginDAO implements CadastroLogin{
	
	private Connection connection;
	

	public LoginDAO() throws ClassNotFoundException, SQLException {
		this.connection = ConnectionFactory.getConnection();
	}

	@Override
	public Boolean cadastrar(Login login) throws SQLException {
		Util util = new Util();
		PreparedStatement statment = null;

		try {
			statment = this.connection.prepareStatement("insert into login ("
					+ "usuario_login,"
					+ "senha_login,"
					+ "token,"
					+ "data_token) values(?,?,?,?)");
			
			statment.setString(1,  login.getUsuario());
			statment.setString(2,  login.getSenha());
			statment.setString(3,  login.getToken());
			statment.setString(4,  util.dateToString(login.getDataToken()));

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
			statment = this.connection.prepareStatement("delete from login where id_login=" + id);
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
	public Boolean editar(Login login) throws SQLException {
		Util util = new Util();
		PreparedStatement statment = null;
		/*
		SELECT `login`.`id_login`,
	    	`login`.`usuario_login`,
	    	`login`.`senha_login`,
	    	`login`.`token`,
	    	`login`.`data_token`
		FROM `db_adm`.`login`;
		 */		
		try {
			statment = this.connection.prepareStatement("update login set "
					+ "usuario_login=?,"
					+ "senha_login=?,"
					+ "token=?,"
					+ "data_token=? where id_login=?");
			
			statment.setString(1,  login.getUsuario());
			statment.setString(2,  login.getSenha());
			statment.setString(3,  login.getToken());
			statment.setString(4,  util.dateToString(login.getDataToken()));
			
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
	public Login buscar(int id) throws SQLException {
		Login login                = new Login(); 
		ResultSet resultSet        = null;
		PreparedStatement statment = null;
		
		try {
			statment  = this.connection.prepareStatement("select * from login where id_login=" + id);
			resultSet = statment.executeQuery();
			resultSet.first();
			
			login.setId_login(resultSet.getInt("id_login"));
			login.setUsuario(resultSet.getString("usuario_login"));
			login.setSenha(resultSet.getString("senha_login"));
			login.setToken(resultSet.getString("peso_produto"));
			login.setDataToken(resultSet.getDate("data_token"));
						
			resultSet.close();
			statment.close();			
			return login;
			
		} catch (Exception e) {
			e.printStackTrace();
			resultSet.close();
			statment.close();			
			return null;
		}
	}

	@Override
	public Login buscar(String usuario) throws SQLException {
		Login login                = new Login(); 
		ResultSet resultSet        = null;
		PreparedStatement statment = null;
		
		try {
			statment  = this.connection.prepareStatement("select * from login where usuario_login=" + usuario);
			resultSet = statment.executeQuery();
			resultSet.first();
			
			login.setId_login(resultSet.getInt("id_login"));
			login.setUsuario(resultSet.getString("usuario_login"));
			login.setSenha(resultSet.getString("senha_login"));
			login.setToken(resultSet.getString("peso_produto"));
			login.setDataToken(resultSet.getDate("data_token"));
						
			resultSet.close();
			statment.close();			
			return login;
			
		} catch (Exception e) {
			e.printStackTrace();
			resultSet.close();
			statment.close();			
			return null;
		}
	}

	@Override
	public Login buscarToken(String token) throws SQLException {
		Login login                = new Login(); 
		ResultSet resultSet        = null;
		PreparedStatement statment = null;
		
		try {
			statment  = this.connection.prepareStatement("select * from login where token=" + token);
			resultSet = statment.executeQuery();
			resultSet.first();
			
			login.setId_login(resultSet.getInt("id_login"));
			login.setUsuario(resultSet.getString("usuario_login"));
			login.setSenha(resultSet.getString("senha_login"));
			login.setToken(resultSet.getString("peso_produto"));
			login.setDataToken(resultSet.getDate("data_token"));
						
			resultSet.close();
			statment.close();			
			return login;
			
		} catch (Exception e) {
			e.printStackTrace();
			resultSet.close();
			statment.close();			
			return null;
		}
	}

	
	@Override
	public List<Login> listar(int IdEmpresa) throws SQLException {
		List<Login> logins        = new ArrayList<Login>();
		ResultSet resultSet           = null;
		PreparedStatement statment    = null;
		
		try {
			statment  = this.connection.prepareStatement("select * from preco where id_empresa=" + IdEmpresa);
			resultSet = statment.executeQuery();
			
			while (resultSet.next()){
				Login login = new Login();
				login.setId_login(resultSet.getInt("id_login"));
				login.setUsuario(resultSet.getString("usuario_login"));
				login.setSenha(resultSet.getString("senha_login"));
				login.setToken(resultSet.getString("peso_produto"));
				login.setDataToken(resultSet.getDate("data_token"));
				logins.add(login);
			}
			
			resultSet.close();
			statment.close();			
			return logins;
			
		} catch (Exception e) {
			e.printStackTrace();
			resultSet.close();
			statment.close();			
			return null;
		}
	}

	@Override
	public Login efetuarLogin(Login login) {		
		try {
			Login      loginRetorno  =  new Login();
			UsuarioDAO usuarioDAO    =  new UsuarioDAO();
			LoginDAO   loginDAO      =  new LoginDAO();
			Date       dataAtual     =  new Date();
			Util       util          =  new Util();
			
			Usuario usuario = usuarioDAO.buscar(login.getUsuario());
			
			if (usuario != null){
				if(login.getSenha() == usuario.getSenha()){
					
					loginRetorno.setUsuario(usuario.getLogin());
					loginRetorno.setSenha("**********");
					loginRetorno.setDataToken(dataAtual);
					loginRetorno.setToken(util.geradorToken(usuario.getLogin()));
					loginDAO.cadastrar(loginRetorno);
					
					return loginRetorno;
				}else{
					return null;
				}				
			}else{
				return null;
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
				
	}

	@Override
	public Boolean efetuarLogout(String token) throws SQLException {
		try {
			LoginDAO loginDAO = new LoginDAO();
			Login login = loginDAO.buscarToken(token);
			loginDAO.deletar(login.getId_login());
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}				
	}

}
