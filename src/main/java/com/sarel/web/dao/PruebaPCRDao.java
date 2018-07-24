package com.sarel.web.dao;

import java.util.Date;
import java.util.List;

import com.sarel.web.model.PruebaPCR;

public interface PruebaPCRDao {
	
	PruebaPCR findById(int id);
	void savePruebaPCR(PruebaPCR pruebaPCR);
	void updatePruebaPCR(PruebaPCR pruebaPCR);
	void deletePruebaPCR(PruebaPCR pruebaPCR);
	List<PruebaPCR> findAll();
	List<PruebaPCR> findByIdExpediente(Integer idExpediente);
	List<PruebaPCR> findByIdExpedienteAndDates(Integer idExpediente, Date fechaInicial, Date fechaFinal);
}