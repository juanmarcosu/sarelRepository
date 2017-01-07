package com.sarel.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sarel.web.model.EstadoResultadoLaboratorio;
import com.sarel.web.model.HemoglobinaGlucosa;

@Repository("HemoglobinaGlucosaDao")
public class HemoglobinaGlucosaDaoImpl extends AbstractDao<Integer, HemoglobinaGlucosa> implements HemoglobinaGlucosaDao{

	public HemoglobinaGlucosa findById(int id) {
		return getByKey(id);
	}
	
	public void saveHemoglobinaGlucosa(HemoglobinaGlucosa hemoglobinaGlucosa) {
		persist(hemoglobinaGlucosa);
	}
	
	public void updateHemoglobinaGlucosa(HemoglobinaGlucosa hemoglobinaGlucosa) {
		update(hemoglobinaGlucosa);
	}
	
	public void deleteHemoglobinaGlucosa(HemoglobinaGlucosa hemoglobinaGlucosa){
		delete(hemoglobinaGlucosa);
	}
	
	@SuppressWarnings("unchecked")
	public List<HemoglobinaGlucosa> findAll() {
		Criteria criteria = createEntityCriteria();
		return (List<HemoglobinaGlucosa>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<HemoglobinaGlucosa> findByIdExpediente(Integer idExpediente) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("idExpediente", idExpediente));
		criteria.add(Restrictions.eq("estado", EstadoResultadoLaboratorio.ACTIVO));
		return criteria.list();
	}
}