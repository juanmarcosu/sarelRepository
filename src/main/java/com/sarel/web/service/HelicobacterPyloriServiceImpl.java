package com.sarel.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sarel.web.dao.HelicobacterPyloriDao;
import com.sarel.web.model.HelicobacterPylori;

@Service("helicobacterPyloriService")
@Transactional
public class HelicobacterPyloriServiceImpl implements HelicobacterPyloriService {

	@Autowired
	private HelicobacterPyloriDao dao;
	
	public HelicobacterPylori findById(int id){
		return dao.findById(id);
	}
	
	public void updateHelicobacterPylori(HelicobacterPylori helicobacterPylori){
		dao.updateHelicobacterPylori(helicobacterPylori);
	}
	
	public void deleteHelicobacterPylori(HelicobacterPylori helicobacterPylori){
		dao.deleteHelicobacterPylori(helicobacterPylori);
	}
	
	public void saveHelicobacterPylori(HelicobacterPylori helicobacterPylori){
		dao.saveHelicobacterPylori(helicobacterPylori);
	}
	
	public List<HelicobacterPylori> findAll(){
		return dao.findAll();
	}
	
	public List<HelicobacterPylori> findByIdExpediente(Integer idExpediente){
		return dao.findByIdExpediente(idExpediente);
	}
}