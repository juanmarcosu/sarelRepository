package com.sarel.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sarel.web.model.EstadoResultadoLaboratorio;
import com.sarel.web.model.PruebaVDRL;

@Repository("PruebaVDRLDao")
public class PruebaVDRLDaoImpl extends AbstractDao<Integer, PruebaVDRL> implements PruebaVDRLDao{

	public PruebaVDRL findById(int id) {
		return getByKey(id);
	}
	
	public void savePruebaVDRL(PruebaVDRL pruebaVDRL) {
		persist(pruebaVDRL);
	}
	
	public void updatePruebaVDRL(PruebaVDRL pruebaVDRL) {
		update(pruebaVDRL);
	}
	
	public void deletePruebaVDRL(PruebaVDRL pruebaVDRL){
		delete(pruebaVDRL);
	}
	
	@SuppressWarnings("unchecked")
	public List<PruebaVDRL> findAll() {
		Criteria criteria = createEntityCriteria();
		return (List<PruebaVDRL>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<PruebaVDRL> findByIdExpediente(Integer idExpediente) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("idExpediente", idExpediente));
		criteria.add(Restrictions.eq("estado", EstadoResultadoLaboratorio.ACTIVO));
		return criteria.list();
	}
}
