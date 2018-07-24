package com.sarel.web.dao;

import java.util.Date;
import java.util.List;

import com.sarel.web.model.ColesterolTrigliceridos;

public interface ColesterolTrigliceridosDao {
	
	ColesterolTrigliceridos findById(int id);
	void saveColesterolTrigliceridos(ColesterolTrigliceridos colesterolTrigliceridos);
	void updateColesterolTrigliceridos(ColesterolTrigliceridos colesterolTrigliceridos);
	void deleteColesterolTrigliceridos(ColesterolTrigliceridos colesterolTrigliceridos);
	List<ColesterolTrigliceridos> findAll();
	List<ColesterolTrigliceridos> findByIdExpediente(Integer idExpediente);
	List<ColesterolTrigliceridos> findByIdExpedienteAndDates(Integer idExpediente, Date fechaInicial, Date fechaFinal);
}
