package com.sarel.web.service;

import java.util.List;

import com.sarel.web.model.HemoglobinaGlucosa;

public interface HemoglobinaGlucosaService {
	
	HemoglobinaGlucosa findById(int id);
	void saveHemoglobinaGlucosa(HemoglobinaGlucosa hemoglobinaGlucosa);
	void updateHemoglobinaGlucosa(HemoglobinaGlucosa hemoglobinaGlucosa);
	void deleteHemoglobinaGlucosa(HemoglobinaGlucosa hemoglobinaGlucosa);
	List<HemoglobinaGlucosa> findAll();
	List<HemoglobinaGlucosa> findByIdExpediente(Integer idExpediente);

}