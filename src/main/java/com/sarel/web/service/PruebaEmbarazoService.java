package com.sarel.web.service;

import java.util.List;

import com.sarel.web.model.PruebaEmbarazo;

public interface PruebaEmbarazoService {
	
	PruebaEmbarazo findById(int id);
	void savePruebaEmbarazo(PruebaEmbarazo pruebaEmbarazo);
	void updatePruebaEmbarazo(PruebaEmbarazo pruebaEmbarazo);
	void deletePruebaEmbarazo(PruebaEmbarazo pruebaEmbarazo);
	List<PruebaEmbarazo> findAll();
	List<PruebaEmbarazo> findByIdExpediente(Integer idExpediente);

}
