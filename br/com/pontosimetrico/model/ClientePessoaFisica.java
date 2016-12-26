package br.com.pontosimetrico.model;

import java.util.Date;

public class ClientePessoaFisica extends PessoaFisica{
	
	private Date  dataDeCadastro;
	private Double  credito;
	private Double  bonus;
	private Boolean status;
	private int     id_Empresa;
	
	
	public Date getDataDeCadastro() {
		return dataDeCadastro;
	}
	public void setDataDeCadastro(Date dataDeCadastro) {
		this.dataDeCadastro = dataDeCadastro;
	}
	public Double getCredito() {
		return credito;
	}
	public void setCredito(Double credito) {
		this.credito = credito;
	}
	public Double getBonus() {
		return bonus;
	}
	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public int getId_Empresa() {
		return id_Empresa;
	}
	public void setId_Empresa(int id_Empresa) {
		this.id_Empresa = id_Empresa;
	}
	
	

}
