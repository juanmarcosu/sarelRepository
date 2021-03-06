package com.sarel.web.service;

import java.util.Date;
import java.util.List;

import com.sarel.web.model.PruebaSerologica;

public interface PruebaSerologicaService {
	
	PruebaSerologica findById(int id);
	void savePruebaSerologica(PruebaSerologica pruebaSerologica);
	void updatePruebaSerologica(PruebaSerologica pruebaSerologica);
	void deletePruebaSerologica(PruebaSerologica pruebaSerologica);
	List<PruebaSerologica> findAll();
	List<PruebaSerologica> findByIdExpediente(Integer idExpediente);
	List<PruebaSerologica> findByIdExpedienteAndDates(Integer idExpediente, Date fechaInicial, Date fechaFinal);
}
