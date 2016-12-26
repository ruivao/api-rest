package br.com.pontosimetrico.interfaces;

import java.sql.SQLException;
import java.util.List;

import br.com.pontosimetrico.model.ClientePessoaJuridica;

public interface CadastroClientePJ {

	public Boolean cadastrar(ClientePessoaJuridica clientePessoaJuridica) throws SQLException;
	public Boolean deletar(int id) throws SQLException;
	public Boolean editar(ClientePessoaJuridica clientePessoaJuridica) throws SQLException;
	public ClientePessoaJuridica buscar(int id) throws SQLException;
	public List<ClientePessoaJuridica> listar(int idEmpresa) throws SQLException;

}
