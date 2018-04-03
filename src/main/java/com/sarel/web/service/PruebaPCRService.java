package com.sarel.web.service;

import java.util.List;

import com.sarel.web.model.PruebaPCR;

public interface PruebaPCRService {
	
	PruebaPCR findById(int id);
	void savePruebaPCR(PruebaPCR pruebaPCR);
	void updatePruebaPCR(PruebaPCR pruebaPCR);
	void deletePruebaPCR(PruebaPCR pruebaPCR);
	List<PruebaPCR> findAll();
	List<PruebaPCR> findByIdExpediente(Integer idExpediente);

}