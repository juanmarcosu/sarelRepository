package com.sarel.web.service;

import java.util.List;

import com.sarel.web.model.PerfilLipido;

public interface PerfilLipidoService {
	
	PerfilLipido findById(int id);
	void savePerfilLipido(PerfilLipido perfilLipido);
	void updatePerfilLipido(PerfilLipido perfilLipido);
	void deletePerfilLipido(PerfilLipido perfilLipido);
	List<PerfilLipido> findAll();
	List<PerfilLipido> findByIdExpediente(Integer idExpediente);

}
