package br.com.pontosimetrico.model;

import java.util.Date;

public class Empresa extends PessoaJuridica{
	
	private Date dataAbertura;
	private Date dataExercicio;
	
	
	public Date getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	public Date getDataExercicio() {
		return dataExercicio;
	}
	public void setDataExercicio(Date dataExercicio) {
		this.dataExercicio = dataExercicio;
	}
	
	

}
