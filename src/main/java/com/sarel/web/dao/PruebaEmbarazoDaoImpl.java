package com.sarel.web.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sarel.web.model.EstadoResultadoLaboratorio;
import com.sarel.web.model.PruebaEmbarazo;
import com.sarel.web.util.UtilsSarel;

@Repository("PruebaEmbarazoDao")
public class PruebaEmbarazoDaoImpl extends AbstractDao<Integer, PruebaEmbarazo> implements PruebaEmbarazoDao{

	public PruebaEmbarazo findById(int id) {
		return getByKey(id);
	}
	
	public void savePruebaEmbarazo(PruebaEmbarazo pruebaEmbarazo) {
		persist(pruebaEmbarazo);
	}
	
	public void updatePruebaEmbarazo(PruebaEmbarazo pruebaEmbarazo) {
		update(pruebaEmbarazo);
	}
	
	public void deletePruebaEmbarazo(PruebaEmbarazo pruebaEmbarazo){
		delete(pruebaEmbarazo);
	}
	
	@SuppressWarnings("unchecked")
	public List<PruebaEmbarazo> findAll() {
		Criteria criteria = createEntityCriteria();
		return (List<PruebaEmbarazo>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<PruebaEmbarazo> findByIdExpediente(Integer idExpediente) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("idExpediente", idExpediente));
		criteria.add(Restrictions.eq("estado", EstadoResultadoLaboratorio.ACTIVO));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<PruebaEmbarazo> findByIdExpedienteAndDates(Integer idExpediente, Date fechaInicial, Date fechaFinal) {
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
