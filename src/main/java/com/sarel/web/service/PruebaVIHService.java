package com.sarel.web.service;

import java.util.List;

import com.sarel.web.model.PruebaVIH;

public interface PruebaVIHService {
	
	PruebaVIH findById(int id);
	void savePruebaVIH(PruebaVIH pruebaVIH);
	void updatePruebaVIH(PruebaVIH pruebaVIH);
	void deletePruebaVIH(PruebaVIH pruebaVIH);
	List<PruebaVIH> findAll();
	List<PruebaVIH> findByCodigo(String pCodigo);
}