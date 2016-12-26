package br.com.pontosimetrico.interfaces;

import java.sql.SQLException;
import java.util.List;

import br.com.pontosimetrico.model.Telefone;

public interface CadastroTelefone {

	public Boolean cadastrar(Telefone telefone) throws SQLException;
	public Boolean deletar(int id) throws SQLException;
	public Boolean editar(Telefone telefone) throws SQLException;
	public Telefone buscar(int id) throws SQLException;
	public List<Telefone> listar(int IdEmpresa) throws SQLException;
	
}
