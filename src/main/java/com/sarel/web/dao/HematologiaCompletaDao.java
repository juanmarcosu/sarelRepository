package com.sarel.web.dao;

import java.util.List;

import com.sarel.web.model.HematologiaCompleta;

public interface HematologiaCompletaDao {
	
	HematologiaCompleta findById(int id);
	void saveHematologiaCompleta(HematologiaCompleta hematologiaCompleta);
	void updateHematologiaCompleta(HematologiaCompleta hematologiaCompleta);
	void deleteHematologiaCompleta(HematologiaCompleta hematologiaCompleta);
	List<HematologiaCompleta> findAll();
	List<HematologiaCompleta> findByIdExpediente(Integer idExpediente);

}
