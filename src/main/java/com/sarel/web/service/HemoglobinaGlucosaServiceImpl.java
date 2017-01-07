package com.sarel.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sarel.web.dao.HemoglobinaGlucosaDao;
import com.sarel.web.model.HemoglobinaGlucosa;

@Service("hemoglobinaGlucosaService")
@Transactional
public class HemoglobinaGlucosaServiceImpl implements HemoglobinaGlucosaService {

	@Autowired
	private HemoglobinaGlucosaDao dao;
	
	public HemoglobinaGlucosa findById(int id){
		return dao.findById(id);
	}
	
	public void updateHemoglobinaGlucosa(HemoglobinaGlucosa hemoglobinaGlucosa){
		dao.updateHemoglobinaGlucosa(hemoglobinaGlucosa);
	}
	
	public void deleteHemoglobinaGlucosa(HemoglobinaGlucosa hemoglobinaGlucosa){
		dao.deleteHemoglobinaGlucosa(hemoglobinaGlucosa);
	}
	
	public void saveHemoglobinaGlucosa(HemoglobinaGlucosa hemoglobinaGlucosa){
		dao.saveHemoglobinaGlucosa(hemoglobinaGlucosa);
	}
	
	public List<HemoglobinaGlucosa> findAll(){
		return dao.findAll();
	}
	
	public List<HemoglobinaGlucosa> findByIdExpediente(Integer idExpediente){
		return dao.findByIdExpediente(idExpediente);
	}
}