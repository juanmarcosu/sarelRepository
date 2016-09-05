package com.sarel.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sarel.web.model.EstadoResultadoLaboratorio;
import com.sarel.web.model.PruebaVIH;

@Repository("PruebaVIHDao")
public class PruebaVIHDaoImpl extends AbstractDao<Integer, PruebaVIH> implements PruebaVIHDao{

	public PruebaVIH findById(int id) {
		return getByKey(id);
	}
	
	public void savePruebaVIH(PruebaVIH pruebaVIH) {
		persist(pruebaVIH);
	}
	
	public void updatePruebaVIH(PruebaVIH pruebaVIH) {
		update(pruebaVIH);
	}
	
	public void deletePruebaVIH(PruebaVIH pruebaVIH){
		delete(pruebaVIH);
	}
	
	@SuppressWarnings("unchecked")
	public List<PruebaVIH> findAll() {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("estado", EstadoResultadoLaboratorio.ACTIVO));
		return (List<PruebaVIH>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<PruebaVIH> findByCodigo(String pCodigo) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("estado", EstadoResultadoLaboratorio.ACTIVO));
		criteria.add(Restrictions.ilike("codigo", pCodigo, MatchMode.ANYWHERE));
		return (List<PruebaVIH>) criteria.list();
	}

}