package br.com.pontosimetrico.interfaces;

import java.sql.SQLException;
import java.util.List;

import br.com.pontosimetrico.model.ClientePessoaFisica;


public interface CadastroClientePF {

	public Boolean cadastrar(ClientePessoaFisica clientePessoaFisica) throws SQLException;
	public Boolean deletar(int id) throws SQLException;
	public Boolean editar(ClientePessoaFisica clientePessoaFisica) throws SQLException;
	public ClientePessoaFisica buscar(int id) throws SQLException;
	public List<ClientePessoaFisica> listar(int IdEmpresa) throws SQLException;
	
}
