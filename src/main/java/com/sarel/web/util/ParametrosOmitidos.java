package com.sarel.web.util;

import java.util.ArrayList;

public class ParametrosOmitidos {
	ArrayList<String> excepciones;
	
	public ParametrosOmitidos(){
		excepciones = new ArrayList<String>();
		excepciones.add("logoUSALUD");
		excepciones.add("logoUSAC");
		excepciones.add("titulo");
		excepciones.add("nombrePaciente");
		excepciones.add("fecha");
		excepciones.add("codigoPaciente");
		excepciones.add("quimicoBiologo");
		excepciones.add("sign");
		excepciones.add("sello");
	}
	
	public void addParametroOmitido(String omitir){
		excepciones.add(omitir);
	}

	public ArrayList<String> getExcepciones() {
		return excepciones;
	}

	public void setExcepciones(ArrayList<String> excepciones) {
		this.excepciones = excepciones;
	}
	
}
