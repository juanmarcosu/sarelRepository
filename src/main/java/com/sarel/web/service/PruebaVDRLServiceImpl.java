package com.sarel.web.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sarel.web.dao.PruebaVDRLDao;
import com.sarel.web.model.PruebaVDRL;

@Service("pruebaVDRLService")
@Transactional
public class PruebaVDRLServiceImpl implements PruebaVDRLService {

	@Autowired
	private PruebaVDRLDao dao;
	
	public PruebaVDRL findById(int id){
		return dao.findById(id);
	}
	
	public void updatePruebaVDRL(PruebaVDRL pruebaVDRL){
		dao.updatePruebaVDRL(pruebaVDRL);
	}
	
	public void deletePruebaVDRL(PruebaVDRL pruebaVDRL){
		dao.deletePruebaVDRL(pruebaVDRL);
	}
	
	public void savePruebaVDRL(PruebaVDRL pruebaVDRL){
		dao.savePruebaVDRL(pruebaVDRL);
	}
	
	public List<PruebaVDRL> findAll(){
		return dao.findAll();
	}
	
	public List<PruebaVDRL> findByIdExpediente(Integer idExpediente){
		return dao.findByIdExpediente(idExpediente);
	}
	
	public List<PruebaVDRL> findByIdExpedienteAndDates(Integer idExpediente, Date fechaInicial, Date fechaFinal){
		return dao.findByIdExpedienteAndDates(idExpediente, fechaInicial, fechaFinal);
	}
}
