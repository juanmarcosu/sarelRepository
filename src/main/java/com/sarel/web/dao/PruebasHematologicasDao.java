package com.sarel.web.dao;

import java.util.Date;
import java.util.List;

import com.sarel.web.model.PruebasHematologicas;

public interface PruebasHematologicasDao {
	
	PruebasHematologicas findById(int id);
	void savePruebasHematologicas(PruebasHematologicas pruebasHematologicas);
	void updatePruebasHematologicas(PruebasHematologicas pruebasHematologicas);
	void deletePruebasHematologicas(PruebasHematologicas pruebasHematologicas);
	List<PruebasHematologicas> findAll();
	List<PruebasHematologicas> findByIdExpediente(Integer idExpediente);
	List<PruebasHematologicas> findByIdExpedienteAndDates(Integer idExpediente, Date fechaInicial, Date fechaFinal);
}
