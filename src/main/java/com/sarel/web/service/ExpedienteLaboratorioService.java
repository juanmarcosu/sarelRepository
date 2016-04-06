package com.sarel.web.service;

import java.util.List;
import java.util.Map;

import com.sarel.web.model.ExpedienteLaboratorio;

public interface ExpedienteLaboratorioService {

	ExpedienteLaboratorio findById(int id);
	
	void saveExpedienteLaboratorio(ExpedienteLaboratorio expediente);
	
	List<ExpedienteLaboratorio> findAll();
	
	List<ExpedienteLaboratorio> findVeinte();

	ExpedienteLaboratorio findByCarnet(Integer carne);
	
	ExpedienteLaboratorio findByIdPaciente(Integer idPaciente);
	
	List<ExpedienteLaboratorio> findByCriteria(Map<String, Object> params);
}
