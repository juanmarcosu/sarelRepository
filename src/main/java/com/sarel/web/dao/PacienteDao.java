package com.sarel.web.dao;

import java.util.List;
import java.util.Map;

import com.sarel.web.model.Paciente;

public interface PacienteDao {

	Paciente findById(int id);
	
	List<Paciente> findAll();
	
	List<Paciente> findVeinte();

	Paciente findByCarnet(Integer carne);
	
	List<Paciente> findByCriteria(Map<String, Object> params);
	
}
