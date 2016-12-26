package br.com.pontosimetrico.interfaces;

import java.sql.SQLException;
import java.util.List;

import br.com.pontosimetrico.model.Usuario;

public interface CadastroUsuario {
	
	public Boolean cadastrar(Usuario usuario) throws SQLException;
	public Boolean deletar(int id) throws SQLException;
	public Boolean editar(Usuario usuario) throws SQLException;
	public Usuario buscar(int id) throws SQLException;
	public Usuario buscar(String login) throws SQLException;
	public List<Usuario> listar(int idEmpresa) throws SQLException;

}
