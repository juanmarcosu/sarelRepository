package com.sarel.web.dao;

import java.util.Date;
import java.util.List;

import com.sarel.web.model.GlucosaPreYPost;

public interface GlucosaPreYPostDao {
	
	GlucosaPreYPost findById(int id);
	void saveGlucosaPreYPost(GlucosaPreYPost glucosaPreYPost);
	void updateGlucosaPreYPost(GlucosaPreYPost glucosaPreYPost);
	void deleteGlucosaPreYPost(GlucosaPreYPost glucosaPreYPost);
	List<GlucosaPreYPost> findAll();
	List<GlucosaPreYPost> findByIdExpediente(Integer idExpediente);
	List<GlucosaPreYPost> findByIdExpedienteAndDates(Integer idExpediente, Date fechaInicial, Date fechaFinal);
}
