package com.sarel.web.dao;

import java.util.List;

import com.sarel.web.model.PruebaTiroidea;

public interface PruebaTiroideaDao {
	
	PruebaTiroidea findById(int id);
	void savePruebaTiroidea(PruebaTiroidea pruebaTiroidea);
	void updatePruebaTiroidea(PruebaTiroidea pruebaTiroidea);
	void deletePruebaTiroidea(PruebaTiroidea pruebaTiroidea);
	List<PruebaTiroidea> findAll();
	List<PruebaTiroidea> findByIdExpediente(Integer idExpediente);

}