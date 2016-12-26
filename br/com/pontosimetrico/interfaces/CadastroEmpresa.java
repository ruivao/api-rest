package br.com.pontosimetrico.interfaces;

import java.sql.SQLException;
import java.util.List;

import br.com.pontosimetrico.model.Empresa;

public interface CadastroEmpresa {
	
	public Boolean cadastrar(Empresa empresa) throws SQLException;
	public Boolean deletar(int id) throws SQLException;
	public Boolean editar(Empresa empresa) throws SQLException;
	public Empresa buscar(int id) throws SQLException;
	public List<Empresa> listar() throws SQLException;

}
