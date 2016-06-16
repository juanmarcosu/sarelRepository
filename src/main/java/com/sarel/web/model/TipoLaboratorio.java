package com.sarel.web.model;

public enum TipoLaboratorio {
	
	PERFIL_LIPIDICO("Perfil Lipidico"),
	PRUEBA_EMBARAZO("Prueba de Embarazo"),
	ACIDO_URICO("Acido Urico"),
	PRUEBA_VDRL("Prueba VDRL"),
	COLESTEROL_TRIGLICERIDOS("Cho-Tri"),
	GLUCOSA_PRE_Y_POST("Gluco pre-pp"),
	PRUEBA_SEROLOGICA("Pruebas Serológicas");
	
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
