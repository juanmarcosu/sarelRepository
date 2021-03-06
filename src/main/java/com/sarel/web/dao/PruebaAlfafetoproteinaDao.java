package com.sarel.web.dao;

import java.util.Date;
import java.util.List;

import com.sarel.web.model.PruebaAlfafetoproteina;

public interface PruebaAlfafetoproteinaDao {
	
	PruebaAlfafetoproteina findById(int id);
	void savePruebaAlfafetoproteina(PruebaAlfafetoproteina pruebaAlfafetoproteina);
	void updatePruebaAlfafetoproteina(PruebaAlfafetoproteina pruebaAlfafetoproteina);
	void deletePruebaAlfafetoproteina(PruebaAlfafetoproteina pruebaAlfafetoproteina);
	List<PruebaAlfafetoproteina> findAll();
	List<PruebaAlfafetoproteina> findByIdExpediente(Integer idExpediente);
	List<PruebaAlfafetoproteina> findByIdExpedienteAndDates(Integer idExpediente, Date fechaInicial, Date fechaFinal);
}