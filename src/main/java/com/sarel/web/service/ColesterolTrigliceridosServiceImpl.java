package com.sarel.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sarel.web.dao.ColesterolTrigliceridosDao;
import com.sarel.web.model.ColesterolTrigliceridos;

@Service("colesterolTrigliceridosService")
@Transactional
public class ColesterolTrigliceridosServiceImpl implements ColesterolTrigliceridosService {

	@Autowired
	private ColesterolTrigliceridosDao dao;
	
	public ColesterolTrigliceridos findById(int id){
		return dao.findById(id);
	}
	
	public void updateColesterolTrigliceridos(ColesterolTrigliceridos colesterolTrigliceridos){
		dao.updateColesterolTrigliceridos(colesterolTrigliceridos);
	}
	
	public void deleteColesterolTrigliceridos(ColesterolTrigliceridos colesterolTrigliceridos){
		dao.deleteColesterolTrigliceridos(colesterolTrigliceridos);
	}
	
	public void saveColesterolTrigliceridos(ColesterolTrigliceridos colesterolTrigliceridos){
		dao.saveColesterolTrigliceridos(colesterolTrigliceridos);
	}
	
	public List<ColesterolTrigliceridos> findAll(){
		return dao.findAll();
	}
	
	public List<ColesterolTrigliceridos> findByIdExpediente(Integer idExpediente){
		return dao.findByIdExpediente(idExpediente);
	}
}
