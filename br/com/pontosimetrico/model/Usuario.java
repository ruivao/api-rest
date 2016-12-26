package br.com.pontosimetrico.model;

import java.util.Date;

public class Usuario {
    
	private int     id;
	private int     id_empresa;
	private String  nome;
	private String  foto_usuario;
	private String  login;
	private String  senha;
	private String  confirma_senha;
	private Date    dataDeCadastro;
	private String  email_usuario;
	private Boolean online;
	private int     id_facebook;
	private int     id_google;
	private int     id_tweeter;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getConfirma_senha() {
		return confirma_senha;
	}
	public void setConfirma_senha(String confirma_senha) {
		this.confirma_senha = confirma_senha;
	}
	public Boolean getOnline() {
		return online;
	}
	public void setOnline(Boolean online) {
		this.online = online;
	}
	public Date getDataDeCadastro() {
		return dataDeCadastro;
	}
	public void setDataDeCadastro(Date dataDeCadastro) {
		this.dataDeCadastro = dataDeCadastro;
	}
	public int getId_empresa() {
		return id_empresa;
	}
	public void setId_empresa(int id_empresa) {
		this.id_empresa = id_empresa;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getFoto_usuario() {
		return foto_usuario;
	}
	public void setFoto_usuario(String foto_usuario) {
		this.foto_usuario = foto_usuario;
	}
	public String getEmail_usuario() {
		return email_usuario;
	}
	public void setEmail_usuario(String email_usuario) {
		this.email_usuario = email_usuario;
	}
	public int getId_facebook() {
		return id_facebook;
	}
	public void setId_facebook(int id_facebook) {
		this.id_facebook = id_facebook;
	}
	public int getId_google() {
		return id_google;
	}
	public void setId_google(int id_google) {
		this.id_google = id_google;
	}
	public int getId_tweeter() {
		return id_tweeter;
	}
	public void setId_tweeter(int id_tweeter) {
		this.id_tweeter = id_tweeter;
	}
	
}
