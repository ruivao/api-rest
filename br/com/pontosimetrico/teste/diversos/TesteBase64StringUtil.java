package br.com.pontosimetrico.teste.diversos;

import java.io.UnsupportedEncodingException;

import br.com.pontosimetrico.util.Util;

public class TesteBase64StringUtil {
	
		
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		Util util = new Util();
		String textoCodificado;
		String textoDecodificado;
		
		textoCodificado   = util.stringToBase64Str("40422219216825511");
		textoDecodificado = util.base64StrToString(textoCodificado); 
			
		System.out.println(textoCodificado);
		System.out.println(textoDecodificado);
		
		
	}
}
