package com.sarel.web.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.sarel.web.model.ExpedienteLaboratorio;

public interface ExpedienteLaboratorioService {

	ExpedienteLaboratorio findById(int id);
	
	void saveExpedienteLaboratorio(ExpedienteLaboratorio expediente);
	
	void updateExpedienteLaboratorio(ExpedienteLaboratorio expediente);
	
	void deleteExpedienteLaboratorio(ExpedienteLaboratorio expediente);
	
	List<ExpedienteLaboratorio> findAll();
	
	List<ExpedienteLaboratorio> findVeinte();

	ExpedienteLaboratorio findByCarnet(BigInteger carne);
	
	ExpedienteLaboratorio findByIdPaciente(Integer idPaciente);
	
	List<ExpedienteLaboratorio> findByCriteria(Map<String, Object> params);
}
