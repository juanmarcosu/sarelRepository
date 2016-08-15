package com.sarel.web.model;

public enum ColorOrina {

	LIMPIDO("Limpido"),
	LIGERAMENTE_TURBIA("Ligeramente Turbia"),
	TURBIA("Turbia");
	
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
