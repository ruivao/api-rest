package br.com.pontosimetrico.model;

import java.util.Date;


public class Fornecedor extends PessoaJuridica {
	
	private Date           dataDeCadastro;
	private Date           primeiraCompra;
	private Date           ultimaCompra;
	private int            id_empresa;
	
	public int getIdEmpresa() {
		return id_empresa;
	}
	public void setIdEmpresa(int empresa) {
		this.id_empresa = empresa;
	}
	public Date getDataDeCadastro() {
		return dataDeCadastro;
	}
	public void setDataDeCadastro(Date dataDeCadastro) {
		this.dataDeCadastro = dataDeCadastro;
	}
	public Date getPrimeiraCompra() {
		return primeiraCompra;
	}
	public void setPrimeiraCompra(Date primeiraCompra) {
		this.primeiraCompra = primeiraCompra;
	}
	public Date getUltimaCompra() {
		return ultimaCompra;
	}
	public void setUltimaCompra(Date ultimaCompra) {
		this.ultimaCompra = ultimaCompra;
	}
	
	

}
