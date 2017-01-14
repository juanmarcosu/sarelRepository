package com.sarel.web.model;

public enum TipoDengue {
	
	IG_G("Ig G"),
	IG_M("Ig M");
	
	private String tipoDengue;
	
	private TipoDengue(final String tipoDengue){
		this.tipoDengue = tipoDengue;
	}
	
	public String getTipoDengue(){
		return this.tipoDengue;
	}
	
	@Override
	public String toString(){
		return this.tipoDengue;
	}

	public String getName(){
		return this.name();
	}

}
