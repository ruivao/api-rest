package br.com.pontosimetrico.interfaces;

import java.sql.SQLException;
import java.util.List;

import br.com.pontosimetrico.model.Fornecedor;

public interface CadastroFornecedor {

	public Boolean cadastrar(Fornecedor fornecedor) throws SQLException;
	public Boolean deletar(int id) throws SQLException;
	public Boolean editar(Fornecedor fornecedor) throws SQLException;
	public Fornecedor buscar(int id) throws SQLException;
	public List<Fornecedor> listar(int IdEmpresa) throws SQLException;
	
}
