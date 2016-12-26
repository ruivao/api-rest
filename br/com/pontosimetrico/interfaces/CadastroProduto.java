package br.com.pontosimetrico.interfaces;

import java.sql.SQLException;
import java.util.List;

import br.com.pontosimetrico.model.Produto;

public interface CadastroProduto {

	public Boolean cadastrar(Produto produto) throws SQLException;
	public Boolean deletar(int id) throws SQLException;
	public Boolean editar(Produto produto) throws SQLException;
	public Produto buscar(int id) throws SQLException;
	public List<Produto> listar(int IdEmpresa) throws SQLException;
	
}
