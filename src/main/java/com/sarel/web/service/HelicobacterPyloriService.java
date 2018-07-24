package com.sarel.web.service;

import java.util.Date;
import java.util.List;

import com.sarel.web.model.HelicobacterPylori;

public interface HelicobacterPyloriService {
	
	HelicobacterPylori findById(int id);
	void saveHelicobacterPylori(HelicobacterPylori helicobacterPylori);
	void updateHelicobacterPylori(HelicobacterPylori helicobacterPylori);
	void deleteHelicobacterPylori(HelicobacterPylori helicobacterPylori);
	List<HelicobacterPylori> findAll();
	List<HelicobacterPylori> findByIdExpediente(Integer idExpediente);
	List<HelicobacterPylori> findByIdExpedienteAndDates(Integer idExpediente, Date fechaInicial, Date fechaFinal);
}