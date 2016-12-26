package br.com.pontosimetrico.interfaces;

import java.sql.SQLException;
import java.util.List;

import br.com.pontosimetrico.model.Contato;


public interface CadastroContato {
	public Boolean cadastrar(Contato contato) throws SQLException;
	public Boolean deletar(int id) throws SQLException;
	public Boolean editar(Contato contato) throws SQLException;
	public Contato buscar(int id) throws SQLException;
	public List<Contato> listar(int IdEmpresa) throws SQLException;

}
