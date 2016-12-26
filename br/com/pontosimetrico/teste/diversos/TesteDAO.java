package br.com.pontosimetrico.teste.diversos;

import java.sql.SQLException;

//import br.com.pontosimetrico.DAO.EmpresaDAO;
import br.com.pontosimetrico.DAO.FornecedorDAO;
import br.com.pontosimetrico.model.Fornecedor;

public class TesteDAO {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		FornecedorDAO dao = new FornecedorDAO();
		//Empresa empresa = new Empresa();
		
		
		// TESTAR BUSCAR
		
		//empresa = dao.buscar(2);
		//System.out.println(empresa.getRazaoSocial());
		
		
		
		// TESTAR LISTAR
		/*
		List<Fornecedor> fornecedores = dao.listar(1);
		
		for(Fornecedor fornecedor: fornecedores){
			System.out.println(fornecedor.getRazaoSocial());
		}
		*/
		Fornecedor fornecedor = dao.buscar(1);
		
		System.out.println(fornecedor.getRazaoSocial());
		

		
		

	}

}
