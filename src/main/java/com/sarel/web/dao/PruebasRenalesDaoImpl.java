package com.sarel.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sarel.web.model.EstadoResultadoLaboratorio;
import com.sarel.web.model.PruebasRenales;

@Repository("PruebasRenalesDao")
public class PruebasRenalesDaoImpl extends AbstractDao<Integer, PruebasRenales> implements PruebasRenalesDao{

	public PruebasRenales findById(int id) {
		return getByKey(id);
	}
	
	public void savePruebasRenales(PruebasRenales pruebasRenales) {
		persist(pruebasRenales);
	}
	
	public void updatePruebasRenales(PruebasRenales pruebasRenales) {
		update(pruebasRenales);
	}
	
	public void deletePruebasRenales(PruebasRenales pruebasRenales){
		delete(pruebasRenales);
	}
	
	@SuppressWarnings("unchecked")
	public List<PruebasRenales> findAll() {
		Criteria criteria = createEntityCriteria();
		return (List<PruebasRenales>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<PruebasRenales> findByIdExpediente(Integer idExpediente) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("idExpediente", idExpediente));
		criteria.add(Restrictions.eq("estado", EstadoResultadoLaboratorio.ACTIVO));
		return criteria.list();
	}
}