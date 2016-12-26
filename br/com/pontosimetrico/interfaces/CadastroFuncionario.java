package br.com.pontosimetrico.interfaces;

import java.sql.SQLException;
import java.util.List;

import br.com.pontosimetrico.model.Funcionario;

public interface CadastroFuncionario {

	public Boolean cadastrar(Funcionario funcionario) throws SQLException;
	public Boolean deletar(int id) throws SQLException;
	public Boolean editar(Funcionario funcionario) throws SQLException;
	public Funcionario buscar(int id) throws SQLException;
	public List<Funcionario> listar(int IdEmpresa) throws SQLException;

}
