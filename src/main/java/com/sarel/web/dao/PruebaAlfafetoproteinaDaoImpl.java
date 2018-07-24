package com.sarel.web.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sarel.web.model.EstadoResultadoLaboratorio;
import com.sarel.web.model.PruebaAlfafetoproteina;
import com.sarel.web.util.UtilsSarel;

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
	
	@SuppressWarnings("unchecked")
	public List<PruebaAlfafetoproteina> findByIdExpedienteAndDates(Integer idExpediente, Date fechaInicial, Date fechaFinal) {
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