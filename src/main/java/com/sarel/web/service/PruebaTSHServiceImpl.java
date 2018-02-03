package com.sarel.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sarel.web.dao.PruebaTSHDao;
import com.sarel.web.model.PruebaTSH;

@Service("pruebaTSHService")
@Transactional
public class PruebaTSHServiceImpl implements PruebaTSHService {

	@Autowired
	private PruebaTSHDao dao;
	
	public PruebaTSH findById(int id){
		return dao.findById(id);
	}
	
	public void updatePruebaTSH(PruebaTSH pruebaTSH){
		dao.updatePruebaTSH(pruebaTSH);
	}
	
	public void deletePruebaTSH(PruebaTSH pruebaTSH){
		dao.deletePruebaTSH(pruebaTSH);
	}
	
	public void savePruebaTSH(PruebaTSH pruebaTSH){
		dao.savePruebaTSH(pruebaTSH);
	}
	
	public List<PruebaTSH> findAll(){
		return dao.findAll();
	}
	
	public List<PruebaTSH> findByIdExpediente(Integer idExpediente){
		return dao.findByIdExpediente(idExpediente);
	}
}