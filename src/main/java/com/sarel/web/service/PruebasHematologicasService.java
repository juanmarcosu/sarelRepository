package com.sarel.web.service;

import java.util.List;

import com.sarel.web.model.PruebasHematologicas;

public interface PruebasHematologicasService {
	
	PruebasHematologicas findById(int id);
	void savePruebasHematologicas(PruebasHematologicas pruebasHematologicas);
	void updatePruebasHematologicas(PruebasHematologicas pruebasHematologicas);
	void deletePruebasHematologicas(PruebasHematologicas pruebasHematologicas);
	List<PruebasHematologicas> findAll();
	List<PruebasHematologicas> findByIdExpediente(Integer idExpediente);

}
