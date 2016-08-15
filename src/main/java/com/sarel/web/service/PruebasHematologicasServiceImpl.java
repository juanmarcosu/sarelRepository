package com.sarel.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sarel.web.dao.PruebasHematologicasDao;
import com.sarel.web.model.PruebasHematologicas;

@Service("pruebasHematologicasService")
@Transactional
public class PruebasHematologicasServiceImpl implements PruebasHematologicasService {

	@Autowired
	private PruebasHematologicasDao dao;
	
	public PruebasHematologicas findById(int id){
		return dao.findById(id);
	}
	
	public void updatePruebasHematologicas(PruebasHematologicas pruebasHematologicas){
		dao.updatePruebasHematologicas(pruebasHematologicas);
	}
	
	public void deletePruebasHematologicas(PruebasHematologicas pruebasHematologicas){
		dao.deletePruebasHematologicas(pruebasHematologicas);
	}
	
	public void savePruebasHematologicas(PruebasHematologicas pruebasHematologicas){
		dao.savePruebasHematologicas(pruebasHematologicas);
	}
	
	public List<PruebasHematologicas> findAll(){
		return dao.findAll();
	}
	
	public List<PruebasHematologicas> findByIdExpediente(Integer idExpediente){
		return dao.findByIdExpediente(idExpediente);
	}
}
