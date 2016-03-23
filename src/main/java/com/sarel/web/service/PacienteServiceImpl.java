package com.sarel.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sarel.web.dao.PacienteDao;
import com.sarel.web.model.Paciente;

@Service("pacienteService")
@Transactional
public class PacienteServiceImpl implements PacienteService{

	@Autowired
	private PacienteDao dao;
	
	public Paciente findById(int id) {
		return dao.findById(id);
	}
	
	public List<Paciente> findAll() {
		return dao.findAll();
	}
	
	public List<Paciente> findVeinte(){
		return dao.findVeinte();
	}

	public Paciente findByCarnet(Integer carne) {
		return dao.findByCarnet(carne);
	}
	
}
