package com.sarel.web.dao;

import java.util.List;

import com.sarel.web.model.ExamenHeces;

public interface ExamenHecesDao {
	
	ExamenHeces findById(int id);
	void saveExamenHeces(ExamenHeces examenHeces);
	void updateExamenHeces(ExamenHeces examenHeces);
	void deleteExamenHeces(ExamenHeces examenHeces);
	List<ExamenHeces> findAll();
	List<ExamenHeces> findByIdExpediente(Integer idExpediente);

}
