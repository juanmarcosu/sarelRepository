package com.sarel.web.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sarel.web.dao.AntigenoProstaticoDao;
import com.sarel.web.model.AntigenoProstatico;

@Service("antigenoProstaticoService")
@Transactional
public class AntigenoProstaticoServiceImpl implements AntigenoProstaticoService {

	@Autowired
	private AntigenoProstaticoDao dao;
	
	public AntigenoProstatico findById(int id){
		return dao.findById(id);
	}
	
	public void updateAntigenoProstatico(AntigenoProstatico antigenoProstatico){
		dao.updateAntigenoProstatico(antigenoProstatico);
	}
	
	public void deleteAntigenoProstatico(AntigenoProstatico antigenoProstatico){
		dao.deleteAntigenoProstatico(antigenoProstatico);
	}
	
	public void saveAntigenoProstatico(AntigenoProstatico antigenoProstatico){
		dao.saveAntigenoProstatico(antigenoProstatico);
	}
	
	public List<AntigenoProstatico> findAll(){
		return dao.findAll();
	}
	
	public List<AntigenoProstatico> findByIdExpediente(Integer idExpediente){
		return dao.findByIdExpediente(idExpediente);
	}
	
	public List<AntigenoProstatico> findByIdExpedienteAndDates(Integer idExpediente, Date fechaInicial, Date fechaFinal){
		return dao.findByIdExpedienteAndDates(idExpediente, fechaInicial, fechaFinal);
	}
}