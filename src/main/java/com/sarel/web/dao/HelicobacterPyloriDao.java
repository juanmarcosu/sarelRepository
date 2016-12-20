package com.sarel.web.dao;

import java.util.List;

import com.sarel.web.model.HelicobacterPylori;

public interface HelicobacterPyloriDao {
	
	HelicobacterPylori findById(int id);
	void saveHelicobacterPylori(HelicobacterPylori helicobacterPylori);
	void updateHelicobacterPylori(HelicobacterPylori helicobacterPylori);
	void deleteHelicobacterPylori(HelicobacterPylori helicobacterPylori);
	List<HelicobacterPylori> findAll();
	List<HelicobacterPylori> findByIdExpediente(Integer idExpediente);

}