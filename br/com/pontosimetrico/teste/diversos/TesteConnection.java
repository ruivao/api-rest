/**
 * 
 */
package br.com.pontosimetrico.teste.diversos;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.pontosimetrico.factory.ConnectionFactory;

/**
 * @author User
 *
 */
public class TesteConnection {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Connection con = ConnectionFactory.getConnection();
		con.close();		

	}

}
