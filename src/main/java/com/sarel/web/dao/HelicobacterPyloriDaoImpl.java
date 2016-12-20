package com.sarel.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sarel.web.model.EstadoResultadoLaboratorio;
import com.sarel.web.model.HelicobacterPylori;

@Repository("HelicobacterPyloriDao")
public class HelicobacterPyloriDaoImpl extends AbstractDao<Integer, HelicobacterPylori> implements HelicobacterPyloriDao{

	public HelicobacterPylori findById(int id) {
		return getByKey(id);
	}
	
	public void saveHelicobacterPylori(HelicobacterPylori helicobacterPylori) {
		persist(helicobacterPylori);
	}
	
	public void updateHelicobacterPylori(HelicobacterPylori helicobacterPylori) {
		update(helicobacterPylori);
	}
	
	public void deleteHelicobacterPylori(HelicobacterPylori helicobacterPylori){
		delete(helicobacterPylori);
	}
	
	@SuppressWarnings("unchecked")
	public List<HelicobacterPylori> findAll() {
		Criteria criteria = createEntityCriteria();
		return (List<HelicobacterPylori>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<HelicobacterPylori> findByIdExpediente(Integer idExpediente) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("idExpediente", idExpediente));
		criteria.add(Restrictions.eq("estado", EstadoResultadoLaboratorio.ACTIVO));
		return criteria.list();
	}
}