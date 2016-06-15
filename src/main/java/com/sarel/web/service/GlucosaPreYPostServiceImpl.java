package com.sarel.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sarel.web.dao.GlucosaPreYPostDao;
import com.sarel.web.model.GlucosaPreYPost;

@Service("glucosaPreYPostService")
@Transactional
public class GlucosaPreYPostServiceImpl implements GlucosaPreYPostService {

	@Autowired
	private GlucosaPreYPostDao dao;
	
	public GlucosaPreYPost findById(int id){
		return dao.findById(id);
	}
	
	public void updateGlucosaPreYPost(GlucosaPreYPost glucosaPreYPost){
		dao.updateGlucosaPreYPost(glucosaPreYPost);
	}
	
	public void deleteGlucosaPreYPost(GlucosaPreYPost glucosaPreYPost){
		dao.deleteGlucosaPreYPost(glucosaPreYPost);
	}
	
	public void saveGlucosaPreYPost(GlucosaPreYPost glucosaPreYPost){
		dao.saveGlucosaPreYPost(glucosaPreYPost);
	}
	
	public List<GlucosaPreYPost> findAll(){
		return dao.findAll();
	}
	
	public List<GlucosaPreYPost> findByIdExpediente(Integer idExpediente){
		return dao.findByIdExpediente(idExpediente);
	}
}
