package com.sarel.web.service;

import java.util.List;

import com.sarel.web.model.Paciente;

public interface PacienteService {

	Paciente findById(int id);
	
	List<Paciente> findAll();
	
	List<Paciente> findVeinte();

	public Paciente findByCarnet(Integer carne);
	
}
