package br.com.pontosimetrico.interfaces;

import java.sql.SQLException;
import java.util.List;

import br.com.pontosimetrico.model.Preco;

public interface CadastroPreco {

	public Boolean cadastrar(Preco preco) throws SQLException;
	public Boolean deletar(int id) throws SQLException;
	public Boolean editar(Preco preco) throws SQLException;
	public Preco buscar(int id) throws SQLException;
	public List<Preco> listar(int idEmpresa) throws SQLException;

}
