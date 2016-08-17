package com.sarel.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sarel.web.dao.ExamenHecesDao;
import com.sarel.web.model.ExamenHeces;

@Service("examenHecesService")
@Transactional
public class ExamenHecesServiceImpl implements ExamenHecesService {

	@Autowired
	private ExamenHecesDao dao;
	
	public ExamenHeces findById(int id){
		return dao.findById(id);
	}
	
	public void updateExamenHeces(ExamenHeces examenHeces){
		dao.updateExamenHeces(examenHeces);
	}
	
	public void deleteExamenHeces(ExamenHeces examenHeces){
		dao.deleteExamenHeces(examenHeces);
	}
	
	public void saveExamenHeces(ExamenHeces examenHeces){
		dao.saveExamenHeces(examenHeces);
	}
	
	public List<ExamenHeces> findAll(){
		return dao.findAll();
	}
	
	public List<ExamenHeces> findByIdExpediente(Integer idExpediente){
		return dao.findByIdExpediente(idExpediente);
	}
}
