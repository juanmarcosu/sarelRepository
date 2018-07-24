package com.sarel.web.service;

import java.util.Date;
import java.util.List;

import com.sarel.web.model.ExamenHeces;

public interface ExamenHecesService {
	
	ExamenHeces findById(int id);
	void saveExamenHeces(ExamenHeces examenHeces);
	void updateExamenHeces(ExamenHeces examenHeces);
	void deleteExamenHeces(ExamenHeces examenHeces);
	List<ExamenHeces> findAll();
	List<ExamenHeces> findByIdExpediente(Integer idExpediente);
	List<ExamenHeces> findByIdExpedienteAndDates(Integer idExpediente, Date fechaInicial, Date fechaFinal);
}
