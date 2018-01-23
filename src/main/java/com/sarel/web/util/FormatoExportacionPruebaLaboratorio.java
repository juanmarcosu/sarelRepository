package com.sarel.web.util;

import java.util.HashMap;

public class FormatoExportacionPruebaLaboratorio {
	
	HashMap<String, Object> params;
	ParametrosOmitidos omitidos;
	
	public FormatoExportacionPruebaLaboratorio(HashMap<String, Object> sParams) {
		params = sParams;
		omitidos = new ParametrosOmitidos();
	}
	
	public HashMap<String, Object> getParametrosConFormato(){
		for(String key : params.keySet()){
			if(!omitidos.getExcepciones().contains(key)){
				String nuevoTexto = UtilsSarel.capitalizarSoloPrimerLetra(params.get(key).toString());
				
				if(nuevoTexto.length() > 1){
					nuevoTexto = nuevoTexto.replace("_", " ");
				}
				
				params.put(key, nuevoTexto);
			}
		}
		return params;
	}

	public ParametrosOmitidos getOmitidos() {
		return omitidos;
	}

	public void setOmitidos(ParametrosOmitidos omitidos) {
		this.omitidos = omitidos;
	}

}
