package com.sarel.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sarel.web.dao.PruebaVIHDao;
import com.sarel.web.model.PruebaVIH;

@Service("pruebaVIHService")
@Transactional
public class PruebaVIHServiceImpl implements PruebaVIHService {

	@Autowired
	private PruebaVIHDao dao;
	
	public PruebaVIH findById(int id){
		return dao.findById(id);
	}
	
	public void updatePruebaVIH(PruebaVIH pruebaVIH){
		dao.updatePruebaVIH(pruebaVIH);
	}
	
	public void deletePruebaVIH(PruebaVIH pruebaVIH){
		dao.deletePruebaVIH(pruebaVIH);
	}
	
	public void savePruebaVIH(PruebaVIH pruebaVIH){
		dao.savePruebaVIH(pruebaVIH);
	}
	
	public List<PruebaVIH> findAll(){
		return dao.findAll();
	}
	public List<PruebaVIH> findByCodigo(String pCodigo) {
		return dao.findByCodigo(pCodigo);
	}
}
