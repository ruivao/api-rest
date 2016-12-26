package br.com.pontosimetrico.interfaces;

import java.sql.SQLException;
import java.util.List;

import br.com.pontosimetrico.model.Login;

public interface CadastroLogin {
	public Boolean cadastrar(Login login) throws SQLException;
	public Boolean deletar(int id) throws SQLException;
	public Boolean editar(Login login) throws SQLException;
	public Login buscar(int id) throws SQLException;
	public Login buscar(String usuario) throws SQLException;
	public Login buscarToken(String token) throws SQLException;
	public List<Login> listar(int IdEmpresa) throws SQLException;
	public Login efetuarLogin(Login login) throws SQLException;
	public Boolean efetuarLogout(String token) throws SQLException;
}
