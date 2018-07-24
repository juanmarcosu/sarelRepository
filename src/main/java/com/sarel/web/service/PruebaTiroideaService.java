package com.sarel.web.service;

import java.util.Date;
import java.util.List;

import com.sarel.web.model.PruebaTiroidea;

public interface PruebaTiroideaService {
	
	PruebaTiroidea findById(int id);
	void savePruebaTiroidea(PruebaTiroidea pruebaTiroidea);
	void updatePruebaTiroidea(PruebaTiroidea pruebaTiroidea);
	void deletePruebaTiroidea(PruebaTiroidea pruebaTiroidea);
	List<PruebaTiroidea> findAll();
	List<PruebaTiroidea> findByIdExpediente(Integer idExpediente);
	List<PruebaTiroidea> findByIdExpedienteAndDates(Integer idExpediente, Date fechaInicial, Date fechaFinal);
}