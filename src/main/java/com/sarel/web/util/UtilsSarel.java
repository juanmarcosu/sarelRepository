package com.sarel.web.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

import org.joda.time.LocalDate;

public class UtilsSarel {

	public static String darFormatoANumero(Integer numero, Integer decimales) {
		String resultado = "";
		
		String formato = "";
		
		for(int i = 0 ; i<= decimales;i++){
			formato =+ i==0?"0.":"0";
		}
		
		try{
			DecimalFormat decim = new DecimalFormat(formato);
			resultado = decim.format(numero);
		}catch(Exception e){
			return numero.toString();
		}
		
		return resultado;
	}
	
	public static String darFormatoANumero(BigDecimal numero, Integer decimales) {
		String resultado = "";
		
		try{
			resultado = numero.setScale(decimales).toString();
		}catch(Exception e){
			return numero.toString();
		}
		
		return resultado;
	}
	
	public static String darFormatoANumero(Double numero, Integer decimales){
		String resultado = "";
		String formato = "";
		
		for(int i = 0 ; i<= decimales;i++){
			formato =+ i==0?"0.":"0";
		}
		
		try{
			DecimalFormat decim = new DecimalFormat(formato);
			resultado = decim.format(numero);
		}catch(Exception e){
			return numero.toString();
		}
		return resultado;
	}
	
	public static String capitalizarSoloPrimerLetra(String texto){
		String resultado = "";
		
		try{
			resultado = texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();;
		}catch(Exception e){
			return texto != null? texto : "";
		}
		
		return resultado;
	}
	
	public static LocalDate convertToLocalDate(Date date) {
        if(date == null) return null;
        return new LocalDate(date);
    }
}
