package com.sarel.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sarel.web.dao.PerfilLipidoDao;
import com.sarel.web.model.PerfilLipido;

@Service("perfilLipidoService")
@Transactional
public class PerfilLipidoServiceImpl implements PerfilLipidoService {

	@Autowired
	private PerfilLipidoDao dao;
	
	public PerfilLipido findById(int id){
		return dao.findById(id);
	}
	
	public void savePerfilLipido(PerfilLipido perfilLipido){
		dao.savePerfilLipido(perfilLipido);
	}
	
	public List<PerfilLipido> findAll(){
		return dao.findAll();
	}
	
	public PerfilLipido findByIdExpediente(Integer idExpediente){
		return dao.findByIdExpediente(idExpediente);
	}
}
