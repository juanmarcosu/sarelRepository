package com.sarel.web.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.google.zxing.FormatException;

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
}
