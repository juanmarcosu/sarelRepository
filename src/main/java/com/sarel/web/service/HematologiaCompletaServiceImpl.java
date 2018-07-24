package com.sarel.web.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sarel.web.dao.HematologiaCompletaDao;
import com.sarel.web.model.HematologiaCompleta;

@Service("hematologiaCompletaService")
@Transactional
public class HematologiaCompletaServiceImpl implements HematologiaCompletaService {

	@Autowired
	private HematologiaCompletaDao dao;
	
	public HematologiaCompleta findById(int id){
		return dao.findById(id);
	}
	
	public void updateHematologiaCompleta(HematologiaCompleta hematologiaCompleta){
		dao.updateHematologiaCompleta(hematologiaCompleta);
	}
	
	public void deleteHematologiaCompleta(HematologiaCompleta hematologiaCompleta){
		dao.deleteHematologiaCompleta(hematologiaCompleta);
	}
	
	public void saveHematologiaCompleta(HematologiaCompleta hematologiaCompleta){
		dao.saveHematologiaCompleta(hematologiaCompleta);
	}
	
	public List<HematologiaCompleta> findAll(){
		return dao.findAll();
	}
	
	public List<HematologiaCompleta> findByIdExpediente(Integer idExpediente){
		return dao.findByIdExpediente(idExpediente);
	}
	
	public List<HematologiaCompleta> findByIdExpedienteAndDates(Integer idExpediente, Date fechaInicial, Date fechaFinal){
		return dao.findByIdExpedienteAndDates(idExpediente, fechaInicial, fechaFinal);
	}
}
