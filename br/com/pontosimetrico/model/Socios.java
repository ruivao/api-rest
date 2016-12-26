package br.com.pontosimetrico.model;

import java.util.Date;

public class Socios extends PessoaFisica {
	
	private Double  valorDeCota;
	private Double  valorProlabore;
	private int     id_empresa;
	private Date    dataDeAssociacao;
		
	
	public Double getValorDeCota() {
		return valorDeCota;
	}
	public void setValorDeCota(Double valorDeCota) {
		this.valorDeCota = valorDeCota;
	}
	public Double getValorProlabore() {
		return valorProlabore;
	}
	public void setValorProlabore(Double valorProlabore) {
		this.valorProlabore = valorProlabore;
	}
	public int getEmpresa() {
		return id_empresa;
	}
	public void setEmpresa(int id_empresa) {
		this.id_empresa = id_empresa;
	}
	public Date getDataDeAssociacao() {
		return dataDeAssociacao;
	}
	public void setDataDeAssociacao(Date dataDeAssociacao) {
		this.dataDeAssociacao = dataDeAssociacao;
	}
	
	
}
