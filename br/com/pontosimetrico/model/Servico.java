package br.com.pontosimetrico.model;

import java.util.Date;

public class Servico {
	
	private int     id;
	private String  descricao;
	private Date    dataDeCadastro;
	private int     id_preco;
	private int     id_empresa;
	private String  tipoCobranca;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDataDeCadastro() {
		return dataDeCadastro;
	}
	public void setDataDeCadastro(Date dataDeCadastro) {
		this.dataDeCadastro = dataDeCadastro;
	}
	public int getIdPreco() {
		return id_preco;
	}
	public void setIdPreco(int id_preco) {
		this.id_preco = id_preco;
	}
	public int getIdEmpresa() {
		return id_empresa;
	}
	public void setIdEmpresa(int id_empresa) {
		this.id_empresa = id_empresa;
	}
	public String getTipoCobranca() {
		return tipoCobranca;
	}
	public void setTipoCobranca(String tipoCobranca) {
		this.tipoCobranca = tipoCobranca;
	}

}
