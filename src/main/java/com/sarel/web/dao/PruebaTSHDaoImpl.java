package com.sarel.web.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sarel.web.model.EstadoResultadoLaboratorio;
import com.sarel.web.model.PruebaTSH;
import com.sarel.web.util.UtilsSarel;

@Repository("PruebaTSHDao")
public class PruebaTSHDaoImpl extends AbstractDao<Integer, PruebaTSH> implements PruebaTSHDao{

	public PruebaTSH findById(int id) {
		return getByKey(id);
	}
	
	public void savePruebaTSH(PruebaTSH pruebaTSH) {
		persist(pruebaTSH);
	}
	
	public void updatePruebaTSH(PruebaTSH pruebaTSH) {
		update(pruebaTSH);
	}
	
	public void deletePruebaTSH(PruebaTSH pruebaTSH){
		delete(pruebaTSH);
	}
	
	@SuppressWarnings("unchecked")
	public List<PruebaTSH> findAll() {
		Criteria criteria = createEntityCriteria();
		return (List<PruebaTSH>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<PruebaTSH> findByIdExpediente(Integer idExpediente) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("idExpediente", idExpediente));
		criteria.add(Restrictions.eq("estado", EstadoResultadoLaboratorio.ACTIVO));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<PruebaTSH> findByIdExpedienteAndDates(Integer idExpediente, Date fechaInicial, Date fechaFinal) {
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