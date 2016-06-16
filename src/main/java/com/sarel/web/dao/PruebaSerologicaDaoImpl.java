package com.sarel.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sarel.web.model.EstadoResultadoLaboratorio;
import com.sarel.web.model.PruebaSerologica;

@Repository("PruebaSerologicaDao")
public class PruebaSerologicaDaoImpl extends AbstractDao<Integer, PruebaSerologica> implements PruebaSerologicaDao{

	public PruebaSerologica findById(int id) {
		return getByKey(id);
	}
	
	public void savePruebaSerologica(PruebaSerologica pruebaSerologica) {
		persist(pruebaSerologica);
	}
	
	public void updatePruebaSerologica(PruebaSerologica pruebaSerologica) {
		update(pruebaSerologica);
	}
	
	public void deletePruebaSerologica(PruebaSerologica pruebaSerologica){
		delete(pruebaSerologica);
	}
	
	@SuppressWarnings("unchecked")
	public List<PruebaSerologica> findAll() {
		Criteria criteria = createEntityCriteria();
		return (List<PruebaSerologica>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<PruebaSerologica> findByIdExpediente(Integer idExpediente) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("idExpediente", idExpediente));
		criteria.add(Restrictions.eq("estado", EstadoResultadoLaboratorio.ACTIVO));
		return criteria.list();
	}
}
