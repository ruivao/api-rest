package br.com.pontosimetrico.model;

import java.awt.Image;

public abstract class PessoaJuridica extends Pessoa{
	
	private Image    logomarca;
	private String   razaoSocial;
	private String   cnpj;
	private String   inscricaoEstadual;
	private String   ramoAtividade;
	private String   website;
	private String   emailPrincipal;
	
	
	public Image getLogomarca() {
		return logomarca;
	}
	public void setLogomarca(Image logomarca) {
		this.logomarca = logomarca;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}
	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}
	public String getRamoAtividade() {
		return ramoAtividade;
	}
	public void setRamoAtividade(String ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getEmailPrincipal() {
		return emailPrincipal;
	}
	public void setEmailPrincipal(String emailPrincipal) {
		this.emailPrincipal = emailPrincipal;
	}
	
	
	
	
}
