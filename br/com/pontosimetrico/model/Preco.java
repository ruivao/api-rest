package br.com.pontosimetrico.model;

import java.util.Date;

public class Preco {
	
	private int     id;
	private Double  valor;
	private Double  desconto;
	private Date    validadeDescontoInicio;
	private Date    validadeDescontoFim;
	private Date    validadePrecoInicio;
	private Date    validadePrecoFim;
	private int     id_empresa;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Double getDesconto() {
		return desconto;
	}
	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}
	public Date getValidadePrecoInicio() {
		return validadePrecoInicio;
	}
	public void setValidadePrecoInicio(Date validadePrecoInicio) {
		this.validadePrecoInicio = validadePrecoInicio;
	}
	public Date getValidadePrecoFim() {
		return validadePrecoFim;
	}
	public void setValidadePrecoFim(Date validadePrecoFim) {
		this.validadePrecoFim = validadePrecoFim;
	}
	public Date getValidadeDescontoInicio() {
		return validadeDescontoInicio;
	}
	public void setValidadeDescontoInicio(Date validadeDescontoInicio) {
		this.validadeDescontoInicio = validadeDescontoInicio;
	}
	public Date getValidadeDescontoFim() {
		return validadeDescontoFim;
	}
	public void setValidadeDescontoFim(Date validadeDescontoFim) {
		this.validadeDescontoFim = validadeDescontoFim;
	}
	public int getId_empresa() {
		return id_empresa;
	}
	public void setId_empresa(int id_empresa) {
		this.id_empresa = id_empresa;
	}

}
