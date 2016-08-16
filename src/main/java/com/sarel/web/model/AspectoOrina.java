package com.sarel.web.model;

public enum AspectoOrina {

	LIMPIDO("Limpido"),
	LIGERAMENTE_TURBIA("Ligeramente Turbia"),
	TURBIA("Turbia");
	
	private String aspectoOrina;
	
	private AspectoOrina(final String aspectoOrina){
		this.aspectoOrina = aspectoOrina;
	}
	
	public String getAspectoOrina(){
		return this.aspectoOrina;
	}

	@Override
	public String toString(){
		return this.aspectoOrina;
	}

	public String getName(){
		return this.name();
	}
}
