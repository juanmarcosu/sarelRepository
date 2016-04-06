package com.sarel.web.model;

public enum Sexo {

	HOMBRE("Hombre"),
	MUJER("Mujer");
	
	private String sexo;
	
	private Sexo(final String sexo){
		this.sexo = sexo;
	}
	
	public String getSexo(){
		return this.sexo;
	}

	@Override
	public String toString(){
		return this.sexo;
	}

	public String getName(){
		return this.name();
	}

}
