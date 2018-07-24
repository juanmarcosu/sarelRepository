package com.sarel.web.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sarel.web.dao.PruebaEmbarazoDao;
import com.sarel.web.model.PruebaEmbarazo;

@Service("pruebaEmbarazoService")
@Transactional
public class PruebaEmbarazoServiceImpl implements PruebaEmbarazoService {

	@Autowired
	private PruebaEmbarazoDao dao;
	
	public PruebaEmbarazo findById(int id){
		return dao.findById(id);
	}
	
	public void updatePruebaEmbarazo(PruebaEmbarazo pruebaEmbarazo){
		dao.updatePruebaEmbarazo(pruebaEmbarazo);
	}
	
	public void deletePruebaEmbarazo(PruebaEmbarazo pruebaEmbarazo){
		dao.deletePruebaEmbarazo(pruebaEmbarazo);
	}
	
	public void savePruebaEmbarazo(PruebaEmbarazo pruebaEmbarazo){
		dao.savePruebaEmbarazo(pruebaEmbarazo);
	}
	
	public List<PruebaEmbarazo> findAll(){
		return dao.findAll();
	}
	
	public List<PruebaEmbarazo> findByIdExpediente(Integer idExpediente){
		return dao.findByIdExpediente(idExpediente);
	}
	
	public List<PruebaEmbarazo> findByIdExpedienteAndDates(Integer idExpediente, Date fechaInicial, Date fechaFinal){
		return dao.findByIdExpedienteAndDates(idExpediente, fechaInicial, fechaFinal);
	}
}
