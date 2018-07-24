package com.sarel.web.dao;

import java.util.Date;
import java.util.List;

import com.sarel.web.model.PruebaVDRL;

public interface PruebaVDRLDao {
	
	PruebaVDRL findById(int id);
	void savePruebaVDRL(PruebaVDRL pruebaVDRL);
	void updatePruebaVDRL(PruebaVDRL pruebaVDRL);
	void deletePruebaVDRL(PruebaVDRL pruebaVDRL);
	List<PruebaVDRL> findAll();
	List<PruebaVDRL> findByIdExpediente(Integer idExpediente);
	List<PruebaVDRL> findByIdExpedienteAndDates(Integer idExpediente, Date fechaInicial, Date fechaFinal);
}
