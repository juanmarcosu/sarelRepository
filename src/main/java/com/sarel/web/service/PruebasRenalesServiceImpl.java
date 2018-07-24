package com.sarel.web.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sarel.web.dao.PruebasRenalesDao;
import com.sarel.web.model.PruebasRenales;

@Service("pruebasRenalesService")
@Transactional
public class PruebasRenalesServiceImpl implements PruebasRenalesService {

	@Autowired
	private PruebasRenalesDao dao;
	
	public PruebasRenales findById(int id){
		return dao.findById(id);
	}
	
	public void updatePruebasRenales(PruebasRenales pruebasRenales){
		dao.updatePruebasRenales(pruebasRenales);
	}
	
	public void deletePruebasRenales(PruebasRenales pruebasRenales){
		dao.deletePruebasRenales(pruebasRenales);
	}
	
	public void savePruebasRenales(PruebasRenales pruebasRenales){
		dao.savePruebasRenales(pruebasRenales);
	}
	
	public List<PruebasRenales> findAll(){
		return dao.findAll();
	}
	
	public List<PruebasRenales> findByIdExpediente(Integer idExpediente){
		return dao.findByIdExpediente(idExpediente);
	}
	
	public List<PruebasRenales> findByIdExpedienteAndDates(Integer idExpediente, Date fechaInicial, Date fechaFinal){
		return dao.findByIdExpedienteAndDates(idExpediente, fechaInicial, fechaFinal);
	}
}