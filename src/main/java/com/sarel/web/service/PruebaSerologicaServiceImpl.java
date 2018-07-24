package com.sarel.web.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sarel.web.dao.PruebaSerologicaDao;
import com.sarel.web.model.PruebaSerologica;

@Service("pruebaSerologicaService")
@Transactional
public class PruebaSerologicaServiceImpl implements PruebaSerologicaService {

	@Autowired
	private PruebaSerologicaDao dao;
	
	public PruebaSerologica findById(int id){
		return dao.findById(id);
	}
	
	public void updatePruebaSerologica(PruebaSerologica pruebaSerologica){
		dao.updatePruebaSerologica(pruebaSerologica);
	}
	
	public void deletePruebaSerologica(PruebaSerologica pruebaSerologica){
		dao.deletePruebaSerologica(pruebaSerologica);
	}
	
	public void savePruebaSerologica(PruebaSerologica pruebaSerologica){
		dao.savePruebaSerologica(pruebaSerologica);
	}
	
	public List<PruebaSerologica> findAll(){
		return dao.findAll();
	}
	
	public List<PruebaSerologica> findByIdExpediente(Integer idExpediente){
		return dao.findByIdExpediente(idExpediente);
	}
	
	public List<PruebaSerologica> findByIdExpedienteAndDates(Integer idExpediente, Date fechaInicial, Date fechaFinal){
		return dao.findByIdExpedienteAndDates(idExpediente, fechaInicial, fechaFinal);
	}
}
