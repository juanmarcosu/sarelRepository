package com.sarel.web.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sarel.web.model.AcidoUrico;
import com.sarel.web.model.EstadoResultadoLaboratorio;
import com.sarel.web.util.UtilsSarel;

@Repository("AcidoUricoDao")
public class AcidoUricoDaoImpl extends AbstractDao<Integer, AcidoUrico> implements AcidoUricoDao{

	public AcidoUrico findById(int id) {
		return getByKey(id);
	}
	
	public void saveAcidoUrico(AcidoUrico acidoUrico) {
		persist(acidoUrico);
	}
	
	public void updateAcidoUrico(AcidoUrico acidoUrico) {
		update(acidoUrico);
	}
	
	public void deleteAcidoUrico(AcidoUrico acidoUrico){
		delete(acidoUrico);
	}
	
	@SuppressWarnings("unchecked")
	public List<AcidoUrico> findAll() {
		Criteria criteria = createEntityCriteria();
		return (List<AcidoUrico>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<AcidoUrico> findByIdExpediente(Integer idExpediente) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("idExpediente", idExpediente));
		criteria.add(Restrictions.eq("estado", EstadoResultadoLaboratorio.ACTIVO));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<AcidoUrico> findByIdExpedienteAndDates(Integer idExpediente, Date fechaInicial, Date fechaFinal) {
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
