package br.com.pontosimetrico.interfaces;

import java.sql.SQLException;
import java.util.List;

import br.com.pontosimetrico.model.Socios;

public interface CadastroSocios {
	
	public Boolean cadastrar(Socios socios) throws SQLException;
	public Boolean deletar(int id) throws SQLException;
	public Boolean editar(Socios socios) throws SQLException;
	public Socios buscar(int id) throws SQLException;
	public List<Socios> listar(int idEmpresa) throws SQLException;

}
