package com.sarel.web.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sarel.web.dao.PruebaTiroideaDao;
import com.sarel.web.model.PruebaTiroidea;

@Service("pruebaTiroideaService")
@Transactional
public class PruebaTiroideaServiceImpl implements PruebaTiroideaService {

	@Autowired
	private PruebaTiroideaDao dao;
	
	public PruebaTiroidea findById(int id){
		return dao.findById(id);
	}
	
	public void updatePruebaTiroidea(PruebaTiroidea pruebaTiroidea){
		dao.updatePruebaTiroidea(pruebaTiroidea);
	}
	
	public void deletePruebaTiroidea(PruebaTiroidea pruebaTiroidea){
		dao.deletePruebaTiroidea(pruebaTiroidea);
	}
	
	public void savePruebaTiroidea(PruebaTiroidea pruebaTiroidea){
		dao.savePruebaTiroidea(pruebaTiroidea);
	}
	
	public List<PruebaTiroidea> findAll(){
		return dao.findAll();
	}
	
	public List<PruebaTiroidea> findByIdExpediente(Integer idExpediente){
		return dao.findByIdExpediente(idExpediente);
	}
	
	public List<PruebaTiroidea> findByIdExpedienteAndDates(Integer idExpediente, Date fechaInicial, Date fechaFinal){
		return dao.findByIdExpedienteAndDates(idExpediente, fechaInicial, fechaFinal);
	}
}
