package br.com.pontosimetrico.teste.diversos;

import java.text.ParseException;
import java.util.Date;

import com.google.gson.GsonBuilder;

import br.com.pontosimetrico.util.Util;

public class TesteDateDeserialization {
	
	public static void main(String[] args) throws ParseException{
		Util util = new Util();
		Date data = util.stringToDate("2005-02-22");
	
		 String json = new GsonBuilder()
	               .setDateFormat("yyyy-MM-dd hh:mm:ss.S")
	               .create()
	               .toJson(data);
		
		
		System.out.println(json);
	}
	
	
	public String dataDeserialization(Date data){
		
		 String json = new GsonBuilder()
	               .setDateFormat("yyyy-MM-dd hh:mm:ss.S")
	               .create()
	               .toJson(data);

		return json;
	}

}
