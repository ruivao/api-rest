package br.com.pontosimetrico.interfaces;

import java.sql.SQLException;
import java.util.List;

import br.com.pontosimetrico.model.Endereco;

public interface CadastroEndereco {
	
	public Boolean cadastrar(Endereco endereco) throws SQLException;
	public Boolean deletar(int id) throws SQLException;
	public Boolean editar(Endereco endereco) throws SQLException;
	public Endereco buscar(int id) throws SQLException;
	public List<Endereco> listar(int idEmpresa) throws SQLException;

}
