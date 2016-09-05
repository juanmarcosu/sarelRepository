package com.sarel.web.dao;

import java.util.List;
import java.util.Map;

import com.sarel.web.model.ExpedienteLaboratorio;

public interface ExpedienteLaboratorioDao {

	ExpedienteLaboratorio findById(int id);
	
	void saveExpedienteLaboratorio(ExpedienteLaboratorio expediente);
	
	void updateExpedienteLaboratorio(ExpedienteLaboratorio expediente);
	
	void deleteExpedienteLaboratorio(ExpedienteLaboratorio expediente);
	
	List<ExpedienteLaboratorio> findAll();
	
	List<ExpedienteLaboratorio> findVeinte();

	ExpedienteLaboratorio findByCarnet(Integer carne);
	
	ExpedienteLaboratorio findByIdPaciente(Integer idPaciente);
	
	List<ExpedienteLaboratorio> findByCriteria(Map<String, Object> params);
	
}
