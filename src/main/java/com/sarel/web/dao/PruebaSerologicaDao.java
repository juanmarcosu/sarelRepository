package com.sarel.web.dao;

import java.util.List;

import com.sarel.web.model.PruebaSerologica;

public interface PruebaSerologicaDao {
	
	PruebaSerologica findById(int id);
	void savePruebaSerologica(PruebaSerologica pruebaSerologica);
	void updatePruebaSerologica(PruebaSerologica pruebaSerologica);
	void deletePruebaSerologica(PruebaSerologica pruebaSerologica);
	List<PruebaSerologica> findAll();
	List<PruebaSerologica> findByIdExpediente(Integer idExpediente);

}
