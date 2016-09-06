package com.sarel.web.service;

import java.util.List;
import java.util.Map;

import com.sarel.web.model.PruebaVIH;

public interface PruebaVIHService {
	
	PruebaVIH findById(int id);
	void savePruebaVIH(PruebaVIH pruebaVIH);
	void updatePruebaVIH(PruebaVIH pruebaVIH);
	void deletePruebaVIH(PruebaVIH pruebaVIH);
	List<PruebaVIH> findAll();
	List<PruebaVIH> findByCodigo(String pCodigo);
	List<PruebaVIH> findByCriteria(Map<String, Object> params);
}