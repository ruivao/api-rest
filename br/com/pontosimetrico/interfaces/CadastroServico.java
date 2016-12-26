package br.com.pontosimetrico.interfaces;

import java.sql.SQLException;
import java.util.List;

import br.com.pontosimetrico.model.Servico;

public interface CadastroServico {

	public Boolean cadastrar(Servico servico) throws SQLException;
	public Boolean deletar(int id) throws SQLException;
	public Boolean editar(Servico servico) throws SQLException;
	public Servico buscar(int id) throws SQLException;
	public List<Servico> listar(int idEmpresa) throws SQLException;

}
