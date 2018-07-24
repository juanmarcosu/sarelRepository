package com.sarel.web.dao;

import java.util.Date;
import java.util.List;

import com.sarel.web.model.AntigenoProstatico;

public interface AntigenoProstaticoDao {
	
	AntigenoProstatico findById(int id);
	void saveAntigenoProstatico(AntigenoProstatico antigenoProstatico);
	void updateAntigenoProstatico(AntigenoProstatico antigenoProstatico);
	void deleteAntigenoProstatico(AntigenoProstatico antigenoProstatico);
	List<AntigenoProstatico> findAll();
	List<AntigenoProstatico> findByIdExpediente(Integer idExpediente);
	List<AntigenoProstatico> findByIdExpedienteAndDates(Integer idExpediente, Date fechaInicial, Date fechaFinal);
}
