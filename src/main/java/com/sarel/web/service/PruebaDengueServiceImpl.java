package com.sarel.web.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sarel.web.dao.PruebaDengueDao;
import com.sarel.web.model.PruebaDengue;

@Service("pruebaDengueService")
@Transactional
public class PruebaDengueServiceImpl implements PruebaDengueService {

	@Autowired
	private PruebaDengueDao dao;
	
	public PruebaDengue findById(int id){
		return dao.findById(id);
	}
	
	public void updatePruebaDengue(PruebaDengue pruebaDengue){
		dao.updatePruebaDengue(pruebaDengue);
	}
	
	public void deletePruebaDengue(PruebaDengue pruebaDengue){
		dao.deletePruebaDengue(pruebaDengue);
	}
	
	public void savePruebaDengue(PruebaDengue pruebaDengue){
		dao.savePruebaDengue(pruebaDengue);
	}
	
	public List<PruebaDengue> findAll(){
		return dao.findAll();
	}
	
	public List<PruebaDengue> findByIdExpediente(Integer idExpediente){
		return dao.findByIdExpediente(idExpediente);
	}
	
	public List<PruebaDengue> findByIdExpedienteAndDates(Integer idExpediente, Date fechaInicial, Date fechaFinal){
		return dao.findByIdExpedienteAndDates(idExpediente, fechaInicial, fechaFinal);
	}
}