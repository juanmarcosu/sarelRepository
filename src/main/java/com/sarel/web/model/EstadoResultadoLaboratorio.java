package com.sarel.web.model;

public enum EstadoResultadoLaboratorio {

	ACTIVO("Activo"),
	ELIMINADO("Eliminado");
	
private String EstadoResultadoLaboratorio;
	
	private EstadoResultadoLaboratorio(final String EstadoResultadoLaboratorio){
		this.EstadoResultadoLaboratorio = EstadoResultadoLaboratorio;
	}
	
	public String getEstadoResultadoLaboratorio(){
		return this.EstadoResultadoLaboratorio;
	}

	@Override
	public String toString(){
		return this.EstadoResultadoLaboratorio;
	}

	public String getName(){
		return this.name();
	}
}
