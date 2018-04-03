package com.sarel.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sarel.web.dao.PruebaAlfafetoproteinaDao;
import com.sarel.web.model.PruebaAlfafetoproteina;

@Service("pruebaAlfafetoproteinaService")
@Transactional
public class PruebaAlfafetoproteinaServiceImpl implements PruebaAlfafetoproteinaService {

	@Autowired
	private PruebaAlfafetoproteinaDao dao;
	
	public PruebaAlfafetoproteina findById(int id){
		return dao.findById(id);
	}
	
	public void updatePruebaAlfafetoproteina(PruebaAlfafetoproteina pruebaAlfafetoproteina){
		dao.updatePruebaAlfafetoproteina(pruebaAlfafetoproteina);
	}
	
	public void deletePruebaAlfafetoproteina(PruebaAlfafetoproteina pruebaAlfafetoproteina){
		dao.deletePruebaAlfafetoproteina(pruebaAlfafetoproteina);
	}
	
	public void savePruebaAlfafetoproteina(PruebaAlfafetoproteina pruebaAlfafetoproteina){
		dao.savePruebaAlfafetoproteina(pruebaAlfafetoproteina);
	}
	
	public List<PruebaAlfafetoproteina> findAll(){
		return dao.findAll();
	}
	
	public List<PruebaAlfafetoproteina> findByIdExpediente(Integer idExpediente){
		return dao.findByIdExpediente(idExpediente);
	}
}