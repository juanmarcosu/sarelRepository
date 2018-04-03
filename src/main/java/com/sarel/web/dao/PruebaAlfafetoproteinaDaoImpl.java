package com.sarel.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sarel.web.model.EstadoResultadoLaboratorio;
import com.sarel.web.model.PruebaAlfafetoproteina;

@Repository("PruebaAlfafetoproteinaDao")
public class PruebaAlfafetoproteinaDaoImpl extends AbstractDao<Integer, PruebaAlfafetoproteina> implements PruebaAlfafetoproteinaDao{

	public PruebaAlfafetoproteina findById(int id) {
		return getByKey(id);
	}
	
	public void savePruebaAlfafetoproteina(PruebaAlfafetoproteina pruebaAlfafetoproteina) {
		persist(pruebaAlfafetoproteina);
	}
	
	public void updatePruebaAlfafetoproteina(PruebaAlfafetoproteina pruebaAlfafetoproteina) {
		update(pruebaAlfafetoproteina);
	}
	
	public void deletePruebaAlfafetoproteina(PruebaAlfafetoproteina pruebaAlfafetoproteina){
		delete(pruebaAlfafetoproteina);
	}
	
	@SuppressWarnings("unchecked")
	public List<PruebaAlfafetoproteina> findAll() {
		Criteria criteria = createEntityCriteria();
		return (List<PruebaAlfafetoproteina>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<PruebaAlfafetoproteina> findByIdExpediente(Integer idExpediente) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("idExpediente", idExpediente));
		criteria.add(Restrictions.eq("estado", EstadoResultadoLaboratorio.ACTIVO));
		return criteria.list();
	}
}