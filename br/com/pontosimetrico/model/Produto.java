package br.com.pontosimetrico.model;

import java.util.Date;

public class Produto {
	
	private int     id;
	private String  descricao;
	private Double  peso;
	private Double  volume;
	private int     id_preco;
	private Date    dataDeCadastro;
	private int     id_empresa;
	
	
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
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	public Double getVolume() {
		return volume;
	}
	public void setVolume(Double volume) {
		this.volume = volume;
	}
	public int getIdPreco() {
		return id_preco;
	}
	public void setIdPreco(int id_preco) {
		this.id_preco = id_preco;
	}
	public Date getDataDeCadastro() {
		return dataDeCadastro;
	}
	public void setDataDeCadastro(Date dataDeCadastro) {
		this.dataDeCadastro = dataDeCadastro;
	}
	public int getIdEmpresa() {
		return id_empresa;
	}
	public void setIdEmpresa(int id_empresa) {
		this.id_empresa = id_empresa;
	}
	
	

}
