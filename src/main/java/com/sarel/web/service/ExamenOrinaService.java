package com.sarel.web.service;

import java.util.List;

import com.sarel.web.model.ExamenOrina;

public interface ExamenOrinaService {
	
	ExamenOrina findById(int id);
	void saveExamenOrina(ExamenOrina examenOrina);
	void updateExamenOrina(ExamenOrina examenOrina);
	void deleteExamenOrina(ExamenOrina examenOrina);
	List<ExamenOrina> findAll();
	List<ExamenOrina> findByIdExpediente(Integer idExpediente);

}
