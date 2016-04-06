package com.sarel.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sarel.web.dao.ExpedienteLaboratorioDao;
import com.sarel.web.model.ExpedienteLaboratorio;

@Service("expedienteLaboratorioService")
@Transactional
public class ExpedienteLaboratorioServiceImpl implements ExpedienteLaboratorioService{

	@Autowired
	private ExpedienteLaboratorioDao dao;
	
	public ExpedienteLaboratorio findById(int id) {
		return dao.findById(id);
	}
	
	public void saveExpedienteLaboratorio(ExpedienteLaboratorio expediente) {
		dao.saveExpedienteLaboratorio(expediente); 
	}
	
	public List<ExpedienteLaboratorio> findAll() {
		return dao.findAll();
	}
	
	public List<ExpedienteLaboratorio> findVeinte(){
		return dao.findVeinte();
	}

	public ExpedienteLaboratorio findByCarnet(Integer carne) {
		return dao.findByCarnet(carne);
	}
	
	public ExpedienteLaboratorio findByIdPaciente(Integer idPaciente) {
		return dao.findByIdPaciente(idPaciente);
	}
	
	public List<ExpedienteLaboratorio> findByCriteria(Map<String, Object> params){
		return dao.findByCriteria(params);
	}
	
}
