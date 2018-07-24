package com.sarel.web.service;

import java.util.Date;
import java.util.List;

import com.sarel.web.model.HematologiaCompleta;

public interface HematologiaCompletaService {
	
	HematologiaCompleta findById(int id);
	void saveHematologiaCompleta(HematologiaCompleta hematologiaCompleta);
	void updateHematologiaCompleta(HematologiaCompleta hematologiaCompleta);
	void deleteHematologiaCompleta(HematologiaCompleta hematologiaCompleta);
	List<HematologiaCompleta> findAll();
	List<HematologiaCompleta> findByIdExpediente(Integer idExpediente);
	List<HematologiaCompleta> findByIdExpedienteAndDates(Integer idExpediente, Date fechaInicial, Date fechaFinal);
}
