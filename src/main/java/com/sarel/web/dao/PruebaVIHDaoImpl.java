package com.sarel.web.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;

import com.sarel.web.model.EstadoResultadoLaboratorio;
import com.sarel.web.model.Paciente;
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
	
	@SuppressWarnings("unchecked")
	public List<PruebaVIH> findByCriteria(Map<String, Object> params) {
		Criteria criteria = createEntityCriteria();
		if(params.containsKey("fechaLaboratorio"))
		{
			LocalDate pFecha = (LocalDate) params.get("fechaLaboratorio");
			criteria.add(Restrictions.eq("fechaLaboratorio", pFecha));
		}
		if(params.containsKey("orientador"))
		{
			criteria.add(Restrictions.ilike("orientador", (String) params.get("orientador"), MatchMode.ANYWHERE));
		}
		if(params.containsKey("codigo"))
		{
			criteria.add(Restrictions.ilike("codigo", (String) params.get("codigo"), MatchMode.ANYWHERE));
		}
		if(params.containsKey("estado"))
		{
			criteria.add(Restrictions.eq("estado", (EstadoResultadoLaboratorio)params.get("estado")));
		}
		return (List<PruebaVIH>) criteria.list();
	}

}