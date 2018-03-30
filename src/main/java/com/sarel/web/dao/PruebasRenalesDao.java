package com.sarel.web.dao;

import java.util.List;

import com.sarel.web.model.PruebasRenales;

public interface PruebasRenalesDao {
	
	PruebasRenales findById(int id);
	void savePruebasRenales(PruebasRenales pruebasRenales);
	void updatePruebasRenales(PruebasRenales pruebasRenales);
	void deletePruebasRenales(PruebasRenales pruebasRenales);
	List<PruebasRenales> findAll();
	List<PruebasRenales> findByIdExpediente(Integer idExpediente);

}