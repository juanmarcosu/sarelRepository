package com.sarel.web.model;

public enum Resultado {

	
	NEGATIVO("Negativo"),
	POSITIVO("Positivo");
	
	
	private String resultado;
	
	private Resultado(final String resultado){
		this.resultado = resultado;
	}
	
	public String getResultado(){
		return this.resultado;
	}

	@Override
	public String toString(){
		return this.resultado;
	}

	public String getName(){
		return this.name();
	}

}