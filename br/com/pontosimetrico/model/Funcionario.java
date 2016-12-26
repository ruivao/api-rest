package br.com.pontosimetrico.model;

import java.util.Date;

public class Funcionario extends PessoaFisica {
	
	private String  matricula;
	private Date    dataDeContratacao;
	private Date    dataDeDemissao;
	private String  cargo;
	private String  departamento;
	private Double  salarioBase;
	private Boolean status;
	private int     id_Empresa;
	private String  numCarteiraProf;
	
	
	public String getNumCarteiraProf() {
		return numCarteiraProf;
	}
	public void setNumCarteiraProf(String numCarteiraProf) {
		this.numCarteiraProf = numCarteiraProf;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public Date getDataDeContratacao() {
		return dataDeContratacao;
	}
	public void setDataDeContratacao(Date dataDeContratacao) {
		this.dataDeContratacao = dataDeContratacao;
	}
	public Date getDataDeDemissao() {
		return dataDeDemissao;
	}
	public void setDataDeDemissao(Date dataDeDemissao) {
		this.dataDeDemissao = dataDeDemissao;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public int getId_empresa() {
		return id_Empresa;
	}
	public void setId_empresa(int id_Empresa) {
		this.id_Empresa = id_Empresa;
	}
	public Double getSalarioBase() {
		return salarioBase;
	}
	public void setSalarioBase(Double salarioBase) {
		this.salarioBase = salarioBase;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}

}
