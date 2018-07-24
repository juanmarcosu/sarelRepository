package com.sarel.web.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sarel.web.dao.PruebaPCRDao;
import com.sarel.web.model.PruebaPCR;

@Service("pruebaPCRService")
@Transactional
public class PruebaPCRServiceImpl implements PruebaPCRService {

	@Autowired
	private PruebaPCRDao dao;
	
	public PruebaPCR findById(int id){
		return dao.findById(id);
	}
	
	public void updatePruebaPCR(PruebaPCR pruebaPCR){
		dao.updatePruebaPCR(pruebaPCR);
	}
	
	public void deletePruebaPCR(PruebaPCR pruebaPCR){
		dao.deletePruebaPCR(pruebaPCR);
	}
	
	public void savePruebaPCR(PruebaPCR pruebaPCR){
		dao.savePruebaPCR(pruebaPCR);
	}
	
	public List<PruebaPCR> findAll(){
		return dao.findAll();
	}
	
	public List<PruebaPCR> findByIdExpediente(Integer idExpediente){
		return dao.findByIdExpediente(idExpediente);
	}
	
	public List<PruebaPCR> findByIdExpedienteAndDates(Integer idExpediente, Date fechaInicial, Date fechaFinal){
		return dao.findByIdExpedienteAndDates(idExpediente, fechaInicial, fechaFinal);
	}
}