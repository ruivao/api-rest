package br.com.pontosimetrico.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import br.com.pontosimetrico.DAO.LoginDAO;
import br.com.pontosimetrico.model.Login;

public class Util {

	// CONVERTE DATE PARA STRING
	public Date stringToDate(String date) throws ParseException{
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dataSaida = (Date)formatter.parse(date);		

		return dataSaida;
	}//--
	
	// CONVERTE DATETIME PARA STRING
	public Date stringToDateTime(String date) throws ParseException{
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dataSaida = (Date)formatter.parse(date);		

		return dataSaida;
	}//--

	// CONVERTE TIME PARA STRING
	public Date stringToTime(String time) throws ParseException{
		
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date dataSaida = (Date)formatter.parse(time);		

		return dataSaida;
	}//--

	// CONVERTE STRING PARA DATE 
	public String dateToString(Date data){
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");	
		
		
		return formatter.format(data);
	}//--

	// CONVERTE STRING PARA DATETIME 
	public String dateTimeToString(Date data){
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
		
		return formatter.format(data);
	}//--

	// CONVERTE STRING PARA TIME 
	public String timeToString(Date time){
		
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");	
		
		return formatter.format(time);
	}//--
	
	// CONVERTE STRING PARA STRING BASE64 
	public String stringToBase64Str(String text){
		byte[] textoByte;
		textoByte = text.getBytes();
		
		return DatatypeConverter.printBase64Binary(textoByte);
	}//--
	
	// CONVERTE BASE64 STRING PARA STRING
	public String base64StrToString(String base64Str) throws UnsupportedEncodingException{
		byte[] textoByte;
		
		textoByte = DatatypeConverter.parseBase64Binary(base64Str);
		String textoSaida  = new String(textoByte, "UTF-8"); 
		return textoSaida;
	}//---
	
	// CONVERTE BYTE[] PARA STRING 
	public String byteArrayToString(byte[] byteArray){
		String textoSaida;
		textoSaida = DatatypeConverter.printHexBinary(byteArray);
		return textoSaida;
	}//--
	
	// CONVERTE STRING PARA BYTE[]
	public byte[] stringToByteArray(String bytesEntrada){
		byte[] byteSaida;
		
		byteSaida = bytesEntrada.getBytes(); 
		
		return byteSaida;
	}//---
	
	// CONVERTE BYTE[] PARA BASE64 STRING
	public String byteArrayToBase64Str(byte[] byteArray){
		
		return DatatypeConverter.printBase64Binary(byteArray);
	}//---
	
	// CONVERTE BASE64 STRING PARA BYTE[]
	public byte[] base64StrToByteArray(String base64Str){
				
		return DatatypeConverter.parseBase64Binary(base64Str);
	}
	
	// CONVERTE IMAGEM PARA STRING BASE64
	public String imageToBase64Str(String filePath){
		 File file = new File(filePath);
		 
	        try {            
	            // Reading a Image file from file system
	            FileInputStream imageInFile = new FileInputStream(file);
	            byte imageData[] = new byte[(int) file.length()];
	            imageInFile.read(imageData);
	 
	            // Converting Image byte array into Base64 String
	            String imageDataString = byteArrayToBase64Str(imageData);
	 
	            imageInFile.close();
	            System.out.println("Imagem convertida!");
	            
	            return imageDataString;
	            
	        } catch (FileNotFoundException e) {
	            System.out.println("Imagem não encontrada: " + e);
	            return null;
	        } catch (IOException ioe) {
	            System.out.println("Erro ao tentar ler aquivo imagem: " + ioe);
	            return null;
	        }
	}//--
	
	// CONVERTE BASE64 STRING PARA IMAGEM
	public FileOutputStream base64StrToImage(String base64Str, String path){

		try {            
            // Converting a Base64 String into Image byte array
            byte[] imageByteArray = base64StrToByteArray(base64Str);
 
            // Write a image byte array into file system
            FileOutputStream imageOutFile = new FileOutputStream(path);
 
            imageOutFile.write(imageByteArray);
            imageOutFile.close();
            
            System.out.println("Imagem convertida com sucesso!");
            
            return imageOutFile;
 
        } catch (FileNotFoundException e) {
            System.out.println("Imagem não encontrada: " + e);
            return null;
        } catch (IOException ioe) {
            System.out.println("Erro ao tentar ler aquivo de imagem:  " + ioe);
            return null;
        }
		
	}//---
	
	// GERADOR DE TOKEN
	public String geradorToken(String entrada){
		String saida;
		String segundo;
		String minuto;
		String hora;
		
		Date       data      = new Date(); 
		DateFormat formatSeg = new SimpleDateFormat("ss");
		DateFormat formatMin = new SimpleDateFormat("mm");
		DateFormat formatHor = new SimpleDateFormat("HH");
		
		segundo = formatSeg.format(data);
		minuto  = formatMin.format(data);
		hora    = formatHor.format(data);
		
		saida   =  segundo + minuto + hora + entrada + segundo + hora + segundo;
		
		return stringToBase64Str(saida);				
	}
	//---
	
	// ROTINA DE VALIDAÇÃO DO TOKEN
	public Boolean validaToken(String token){
		try {
			Date data         = new Date();
			LoginDAO loginDAO = new LoginDAO();
								
			Login login       = loginDAO.buscarToken(token);
			
			if(login != null){
				String dataAtual = dateToString(data);
				String dataToken = dateToString(login.getDataToken());				

				if(dataAtual == dataToken){
					return true;
				}else{
					return false;
				}

			}else{
				return false;
			}
									
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return false;
		} 		
	}
	//---
}
