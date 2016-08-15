package com.sarel.web.model;

public enum AspectoHeces {

	PASTOSA("Pastosa"),
	SEMI_DIARREICA("Semidiarreica"),
	DIARREICA("Diarreica");
	
	private String AspectoHeces;
	
	private AspectoHeces(final String AspectoHeces){
		this.AspectoHeces = AspectoHeces;
	}
	
	public String getAspectoHeces(){
		return this.AspectoHeces;
	}

	@Override
	public String toString(){
		return this.AspectoHeces;
	}

	public String getName(){
		return this.name();
	}
}
