package com.sarel.web.model;

public enum ColorOrina {

	
	AMARILLO("Amarillo"),
	CAFE("Café"),
	AMBAR("Ambar"),
	ROJIZA("Rojiza"),
	OTRO("Otro");
	
	private String colorOrina;
	
	private ColorOrina(final String colorOrina){
		this.colorOrina = colorOrina;
	}
	
	public String getColorOrina(){
		return this.colorOrina;
	}

	@Override
	public String toString(){
		return this.colorOrina;
	}

	public String getName(){
		return this.name();
	}
	
}
