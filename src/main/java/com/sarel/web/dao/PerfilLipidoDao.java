package com.sarel.web.dao;

import java.util.List;

import com.sarel.web.model.PerfilLipido;

public interface PerfilLipidoDao {
	
	PerfilLipido findById(int id);
	void savePerfilLipido(PerfilLipido perfilLipido);
	void updatePerfilLipido(PerfilLipido perfilLipido);
	void deletePerfilLipido(PerfilLipido perfilLipido);
	List<PerfilLipido> findAll();
	List<PerfilLipido> findByIdExpediente(Integer idExpediente);

}
