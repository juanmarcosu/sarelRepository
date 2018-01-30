package com.sarel.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sarel.web.model.EstadoResultadoLaboratorio;
import com.sarel.web.model.PruebaTiroidea;

@Repository("PruebaTiroideaDao")
public class PruebaTiroideaDaoImpl extends AbstractDao<Integer, PruebaTiroidea> implements PruebaTiroideaDao{

	public PruebaTiroidea findById(int id) {
		return getByKey(id);
	}
	
	public void savePruebaTiroidea(PruebaTiroidea pruebaTiroidea) {
		persist(pruebaTiroidea);
	}
	
	public void updatePruebaTiroidea(PruebaTiroidea pruebaTiroidea) {
		update(pruebaTiroidea);
	}
	
	public void deletePruebaTiroidea(PruebaTiroidea pruebaTiroidea){
		delete(pruebaTiroidea);
	}
	
	@SuppressWarnings("unchecked")
	public List<PruebaTiroidea> findAll() {
		Criteria criteria = createEntityCriteria();
		return (List<PruebaTiroidea>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<PruebaTiroidea> findByIdExpediente(Integer idExpediente) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("idExpediente", idExpediente));
		criteria.add(Restrictions.eq("estado", EstadoResultadoLaboratorio.ACTIVO));
		return criteria.list();
	}
}