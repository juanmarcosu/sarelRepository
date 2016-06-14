package com.sarel.web.service;

import java.util.List;

import com.sarel.web.model.ColesterolTrigliceridos;

public interface ColesterolTrigliceridosService {
	
	ColesterolTrigliceridos findById(int id);
	void saveColesterolTrigliceridos(ColesterolTrigliceridos colesterolTrigliceridos);
	void updateColesterolTrigliceridos(ColesterolTrigliceridos colesterolTrigliceridos);
	void deleteColesterolTrigliceridos(ColesterolTrigliceridos colesterolTrigliceridos);
	List<ColesterolTrigliceridos> findAll();
	List<ColesterolTrigliceridos> findByIdExpediente(Integer idExpediente);

}
