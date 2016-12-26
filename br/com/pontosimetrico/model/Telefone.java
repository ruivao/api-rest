package br.com.pontosimetrico.model;

public class Telefone {
	
	private int    id;
	private String descricao;
	private int    codigoDDI;
	private int    codigoDDD;
	private String numeroTelefone;
	private String ramal;
	
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
	public int getCodigoDDI() {
		return codigoDDI;
	}
	public void setCodigoDDI(int codigoDDI) {
		this.codigoDDI = codigoDDI;
	}
	public int getCodigoDDD() {
		return codigoDDD;
	}
	public void setCodigoDDD(int codigoDDD) {
		this.codigoDDD = codigoDDD;
	}
	public String getNumeroTelefone() {
		return numeroTelefone;
	}
	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}
	public String getRamal() {
		return ramal;
	}
	public void setRamal(String ramal) {
		this.ramal = ramal;
	}
	
	
	
}
