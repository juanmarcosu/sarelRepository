package com.sarel.web.service;

import java.util.List;

import com.sarel.web.model.PruebaDengue;

public interface PruebaDengueService {
	
	PruebaDengue findById(int id);
	void savePruebaDengue(PruebaDengue pruebaDengue);
	void updatePruebaDengue(PruebaDengue pruebaDengue);
	void deletePruebaDengue(PruebaDengue pruebaDengue);
	List<PruebaDengue> findAll();
	List<PruebaDengue> findByIdExpediente(Integer idExpediente);

}