package com.sarel.web.dao;

import java.util.Date;
import java.util.List;

import com.sarel.web.model.PruebaDengue;

public interface PruebaDengueDao {
	
	PruebaDengue findById(int id);
	void savePruebaDengue(PruebaDengue pruebaDengue);
	void updatePruebaDengue(PruebaDengue pruebaDengue);
	void deletePruebaDengue(PruebaDengue pruebaDengue);
	List<PruebaDengue> findAll();
	List<PruebaDengue> findByIdExpediente(Integer idExpediente);
	List<PruebaDengue> findByIdExpedienteAndDates(Integer idExpediente, Date fechaInicial, Date fechaFinal);
}