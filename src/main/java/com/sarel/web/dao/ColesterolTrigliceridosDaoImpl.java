package com.sarel.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sarel.web.model.EstadoResultadoLaboratorio;
import com.sarel.web.model.ColesterolTrigliceridos;

@Repository("ColesterolTrigliceridosDao")
public class ColesterolTrigliceridosDaoImpl extends AbstractDao<Integer, ColesterolTrigliceridos> implements ColesterolTrigliceridosDao{

	public ColesterolTrigliceridos findById(int id) {
		return getByKey(id);
	}
	
	public void saveColesterolTrigliceridos(ColesterolTrigliceridos colesterolTrigliceridos) {
		persist(colesterolTrigliceridos);
	}
	
	public void updateColesterolTrigliceridos(ColesterolTrigliceridos colesterolTrigliceridos) {
		update(colesterolTrigliceridos);
	}
	
	public void deleteColesterolTrigliceridos(ColesterolTrigliceridos colesterolTrigliceridos){
		delete(colesterolTrigliceridos);
	}
	
	@SuppressWarnings("unchecked")
	public List<ColesterolTrigliceridos> findAll() {
		Criteria criteria = createEntityCriteria();
		return (List<ColesterolTrigliceridos>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ColesterolTrigliceridos> findByIdExpediente(Integer idExpediente) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("idExpediente", idExpediente));
		criteria.add(Restrictions.eq("estado", EstadoResultadoLaboratorio.ACTIVO));
		return criteria.list();
	}
}
