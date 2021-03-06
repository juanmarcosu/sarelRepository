package com.sarel.web.service;

import java.util.Date;
import java.util.List;

import com.sarel.web.model.PruebaTSH;

public interface PruebaTSHService {
	
	PruebaTSH findById(int id);
	void savePruebaTSH(PruebaTSH pruebaTSH);
	void updatePruebaTSH(PruebaTSH pruebaTSH);
	void deletePruebaTSH(PruebaTSH pruebaTSH);
	List<PruebaTSH> findAll();
	List<PruebaTSH> findByIdExpediente(Integer idExpediente);
	List<PruebaTSH> findByIdExpedienteAndDates(Integer idExpediente, Date fechaInicial, Date fechaFinal);
}