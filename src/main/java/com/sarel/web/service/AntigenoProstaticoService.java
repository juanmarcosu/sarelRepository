package com.sarel.web.service;

import java.util.List;

import com.sarel.web.model.AntigenoProstatico;

public interface AntigenoProstaticoService {
	
	AntigenoProstatico findById(int id);
	void saveAntigenoProstatico(AntigenoProstatico antigenoProstatico);
	void updateAntigenoProstatico(AntigenoProstatico antigenoProstatico);
	void deleteAntigenoProstatico(AntigenoProstatico antigenoProstatico);
	List<AntigenoProstatico> findAll();
	List<AntigenoProstatico> findByIdExpediente(Integer idExpediente);

}