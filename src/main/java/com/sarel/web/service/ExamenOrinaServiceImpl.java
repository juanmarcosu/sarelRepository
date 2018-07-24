package com.sarel.web.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sarel.web.dao.ExamenOrinaDao;
import com.sarel.web.model.ExamenOrina;

@Service("examenOrinaService")
@Transactional
public class ExamenOrinaServiceImpl implements ExamenOrinaService {

	@Autowired
	private ExamenOrinaDao dao;
	
	public ExamenOrina findById(int id){
		return dao.findById(id);
	}
	
	public void updateExamenOrina(ExamenOrina examenOrina){
		dao.updateExamenOrina(examenOrina);
	}
	
	public void deleteExamenOrina(ExamenOrina examenOrina){
		dao.deleteExamenOrina(examenOrina);
	}
	
	public void saveExamenOrina(ExamenOrina examenOrina){
		dao.saveExamenOrina(examenOrina);
	}
	
	public List<ExamenOrina> findAll(){
		return dao.findAll();
	}
	
	public List<ExamenOrina> findByIdExpediente(Integer idExpediente){
		return dao.findByIdExpediente(idExpediente);
	}
	
	public List<ExamenOrina> findByIdExpedienteAndDates(Integer idExpediente, Date fechaInicial, Date fechaFinal){
		return dao.findByIdExpedienteAndDates(idExpediente, fechaInicial, fechaFinal);
	}
}
