package com.sarel.web.model;

public enum TipoLaboratorio {
	
	PERFIL_LIPIDICO("Perfil Lipidico"),
	PRUEBA_EMBARAZO("Prueba de Embarazo");
	
	private String tipoLaboratorio;
	
	private TipoLaboratorio(final String tipoLaboratorio){
		this.tipoLaboratorio = tipoLaboratorio;
	}
	
	public String getTipoLaboratorio(){
		return this.tipoLaboratorio;
	}

	@Override
	public String toString(){
		return this.tipoLaboratorio;
	}

	public String getName(){
		return this.name();
	}

}
