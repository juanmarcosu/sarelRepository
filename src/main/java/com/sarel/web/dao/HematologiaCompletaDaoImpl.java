package com.sarel.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sarel.web.model.EstadoResultadoLaboratorio;
import com.sarel.web.model.HematologiaCompleta;

@Repository("HematologiaCompletaDao")
public class HematologiaCompletaDaoImpl extends AbstractDao<Integer, HematologiaCompleta> implements HematologiaCompletaDao{

	public HematologiaCompleta findById(int id) {
		return getByKey(id);
	}
	
	public void saveHematologiaCompleta(HematologiaCompleta hematologiaCompleta) {
		persist(hematologiaCompleta);
	}
	
	public void updateHematologiaCompleta(HematologiaCompleta hematologiaCompleta) {
		update(hematologiaCompleta);
	}
	
	public void deleteHematologiaCompleta(HematologiaCompleta hematologiaCompleta){
		delete(hematologiaCompleta);
	}
	
	@SuppressWarnings("unchecked")
	public List<HematologiaCompleta> findAll() {
		Criteria criteria = createEntityCriteria();
		return (List<HematologiaCompleta>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<HematologiaCompleta> findByIdExpediente(Integer idExpediente) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("idExpediente", idExpediente));
		criteria.add(Restrictions.eq("estado", EstadoResultadoLaboratorio.ACTIVO));
		return criteria.list();
	}
}
