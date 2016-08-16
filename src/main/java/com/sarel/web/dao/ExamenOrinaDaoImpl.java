package com.sarel.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sarel.web.model.EstadoResultadoLaboratorio;
import com.sarel.web.model.ExamenOrina;

@Repository("ExamenOrinaDao")
public class ExamenOrinaDaoImpl extends AbstractDao<Integer, ExamenOrina> implements ExamenOrinaDao{

	public ExamenOrina findById(int id) {
		return getByKey(id);
	}
	
	public void saveExamenOrina(ExamenOrina examenOrina) {
		persist(examenOrina);
	}
	
	public void updateExamenOrina(ExamenOrina examenOrina) {
		update(examenOrina);
	}
	
	public void deleteExamenOrina(ExamenOrina examenOrina){
		delete(examenOrina);
	}
	
	@SuppressWarnings("unchecked")
	public List<ExamenOrina> findAll() {
		Criteria criteria = createEntityCriteria();
		return (List<ExamenOrina>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ExamenOrina> findByIdExpediente(Integer idExpediente) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("idExpediente", idExpediente));
		criteria.add(Restrictions.eq("estado", EstadoResultadoLaboratorio.ACTIVO));
		return criteria.list();
	}
}
