package com.sarel.web.service;

import java.util.List;

import com.sarel.web.model.PerfilLipidico;

public interface PerfilLipidicoService {
	
	PerfilLipidico findById(int id);
	void savePerfilLipidico(PerfilLipidico perfilLipidico);
	void updatePerfilLipidico(PerfilLipidico perfilLipidico);
	void deletePerfilLipidico(PerfilLipidico perfilLipidico);
	List<PerfilLipidico> findAll();
	List<PerfilLipidico> findByIdExpediente(Integer idExpediente);

}

