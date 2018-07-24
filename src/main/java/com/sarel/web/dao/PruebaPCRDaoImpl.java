package com.sarel.web.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sarel.web.model.EstadoResultadoLaboratorio;
import com.sarel.web.model.PruebaPCR;
import com.sarel.web.util.UtilsSarel;

@Repository("PruebaPCRDao")
public class PruebaPCRDaoImpl extends AbstractDao<Integer, PruebaPCR> implements PruebaPCRDao{

	public PruebaPCR findById(int id) {
		return getByKey(id);
	}
	
	public void savePruebaPCR(PruebaPCR pruebaPCR) {
		persist(pruebaPCR);
	}
	
	public void updatePruebaPCR(PruebaPCR pruebaPCR) {
		update(pruebaPCR);
	}
	
	public void deletePruebaPCR(PruebaPCR pruebaPCR){
		delete(pruebaPCR);
	}
	
	@SuppressWarnings("unchecked")
	public List<PruebaPCR> findAll() {
		Criteria criteria = createEntityCriteria();
		return (List<PruebaPCR>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<PruebaPCR> findByIdExpediente(Integer idExpediente) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("idExpediente", idExpediente));
		criteria.add(Restrictions.eq("estado", EstadoResultadoLaboratorio.ACTIVO));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<PruebaPCR> findByIdExpedienteAndDates(Integer idExpediente, Date fechaInicial, Date fechaFinal) {
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