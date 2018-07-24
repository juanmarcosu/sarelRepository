package com.sarel.web.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sarel.web.model.EstadoResultadoLaboratorio;
import com.sarel.web.model.PruebasHematologicas;
import com.sarel.web.util.UtilsSarel;

@Repository("PruebasHematologicasDao")
public class PruebasHematologicasDaoImpl extends AbstractDao<Integer, PruebasHematologicas> implements PruebasHematologicasDao{

	public PruebasHematologicas findById(int id) {
		return getByKey(id);
	}
	
	public void savePruebasHematologicas(PruebasHematologicas pruebasHematologicas) {
		persist(pruebasHematologicas);
	}
	
	public void updatePruebasHematologicas(PruebasHematologicas pruebasHematologicas) {
		update(pruebasHematologicas);
	}
	
	public void deletePruebasHematologicas(PruebasHematologicas pruebasHematologicas){
		delete(pruebasHematologicas);
	}
	
	@SuppressWarnings("unchecked")
	public List<PruebasHematologicas> findAll() {
		Criteria criteria = createEntityCriteria();
		return (List<PruebasHematologicas>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<PruebasHematologicas> findByIdExpediente(Integer idExpediente) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("idExpediente", idExpediente));
		criteria.add(Restrictions.eq("estado", EstadoResultadoLaboratorio.ACTIVO));
		return criteria.list();
	}
	@SuppressWarnings("unchecked")
	public List<PruebasHematologicas> findByIdExpedienteAndDates(Integer idExpediente, Date fechaInicial, Date fechaFinal) {
		Criteria criteria = createEntityCriteria();
		if(fechaInicial != null && fechaFinal != null){
			criteria.add(Restrictions.between("fechaLaboratorio", UtilsSarel.convertToLocalDate(fechaInicial), UtilsSarel.convertToLocalDate(fechaFinal)));
		}else if (fechaInicial!=null){
			criteria.add(Restrictions.ge("fechaLaboratorio", UtilsSarel.convertToLocalDate(fechaInicial)));
		}
		else if (fechaFinal!=null){
			criteria.add(Restrictions.le("fechaLaboratorio", UtilsSarel.convertToLocalDate(fechaFinal)));
		}
		criteria.add(Restrictions.eq("idExpediente", idExpediente));
		criteria.add(Restrictions.eq("estado", EstadoResultadoLaboratorio.ACTIVO));
		return criteria.list();
	}
}
