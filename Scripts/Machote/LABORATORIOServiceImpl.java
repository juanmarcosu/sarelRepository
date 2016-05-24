package com.sarel.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sarel.web.dao.PerfilLipidicoDao;
import com.sarel.web.model.PerfilLipidico;

@Service("perfilLipidicoService")
@Transactional
public class PerfilLipidicoServiceImpl implements PerfilLipidicoService {

	@Autowired
	private PerfilLipidicoDao dao;
	
	public PerfilLipidico findById(int id){
		return dao.findById(id);
	}
	
	public void updatePerfilLipidico(PerfilLipidico perfilLipidico){
		dao.updatePerfilLipidico(perfilLipidico);
	}
	
	public void deletePerfilLipidico(PerfilLipidico perfilLipidico){
		dao.deletePerfilLipidico(perfilLipidico);
	}
	
	public void savePerfilLipidico(PerfilLipidico perfilLipidico){
		dao.savePerfilLipidico(perfilLipidico);
	}
	
	public List<PerfilLipidico> findAll(){
		return dao.findAll();
	}
	
	public List<PerfilLipidico> findByIdExpediente(Integer idExpediente){
		return dao.findByIdExpediente(idExpediente);
	}
}

