package com.sarel.web.model;

public enum CantidadPresente {

	NINGUNA("Ninguna"),
	ESCASA("Escasa"),
	REGULAR_CANTIDAD("Regular Cantidad"),
	ABUNDANTE("Abundante");
	
	private String cantidadPresente;
	
	private CantidadPresente(final String cantidadPresente){
		this.cantidadPresente = cantidadPresente;
	}
	
	public String getCantidadPresente(){
		return this.cantidadPresente;
	}

	@Override
	public String toString(){
		return this.cantidadPresente;
	}

	public String getName(){
		return this.name();
	}
}
