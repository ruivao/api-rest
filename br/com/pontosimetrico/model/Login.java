package br.com.pontosimetrico.model;

import java.io.Serializable;
import java.util.Date;

public class Login implements Serializable {

	private static final long serialVersionUID = 1L;
	private int    id_login;
	private String usuario;
	private String senha;
	private String token;
	private Date   dataToken;
	

	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public Date getDataToken() {
		return dataToken;
	}
	
	public void setDataToken(Date dataToken) {
		this.dataToken = dataToken;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public int getId_login() {
		return id_login;
	}

	public void setId_login(int id_login) {
		this.id_login = id_login;
	}
		
}
