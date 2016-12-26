package br.com.pontosimetrico.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException{
	
		String conexao = "jdbc:mysql://localhost:3307/db_adm?autoReconnect=true&useSSL=false";
        String usuario = "root";
        String senha   = "admin";
        String driver  = "com.mysql.jdbc.Driver";

        Class.forName(driver);
        java.sql.Connection conn = DriverManager.getConnection(conexao, usuario, senha);
        System.out.println("Conexão - OK");
        return (Connection) conn;		
				
	}
}
