package com.sarel.web.service;

import java.util.List;
import java.util.Map;

import com.sarel.web.model.Paciente;

public interface PacienteService {

	Paciente findById(int id);
	
	List<Paciente> findAll();
	
	List<Paciente> findVeinte();

	Paciente findByCarnet(Integer carne);
	
	List<Paciente> findByCriteria(Map<String, Object> params);
}
