package com.sarel.web.dao;

import java.util.List;

import com.sarel.web.model.Paciente;

public interface PacienteDao {

	Paciente findById(int id);
	
	List<Paciente> findAll();
	
	List<Paciente> findVeinte();

	public Paciente findByCarnet(Integer carne);
	
}
