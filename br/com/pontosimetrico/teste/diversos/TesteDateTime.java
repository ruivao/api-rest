package br.com.pontosimetrico.teste.diversos;

import java.util.Date;

import br.com.pontosimetrico.util.Util;

public class TesteDateTime {

	public static void main(String[] args) {
		Util util = new Util();		
		Date data = new Date();
		
		System.out.println(util.timeToString(data));

	}

}
