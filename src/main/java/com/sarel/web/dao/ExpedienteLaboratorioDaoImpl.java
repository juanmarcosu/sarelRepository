package com.sarel.web.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sarel.web.model.EstadoResultadoLaboratorio;
import com.sarel.web.model.ExpedienteLaboratorio;

@Repository("expedienteLaboratorioDao")
public class ExpedienteLaboratorioDaoImpl extends AbstractDao<Integer, ExpedienteLaboratorio> implements ExpedienteLaboratorioDao{

	public ExpedienteLaboratorio findById(int id) {
		return getByKey(id);
	}
	
	public void saveExpedienteLaboratorio(ExpedienteLaboratorio expediente) {
		persist(expediente);
	}
	
	public void updateExpedienteLaboratorio(ExpedienteLaboratorio expedienteLaboratorio) {
		update(expedienteLaboratorio);
	}
	
	public void deleteExpedienteLaboratorio(ExpedienteLaboratorio expedienteLaboratorio){
		delete(expedienteLaboratorio);
	}
	
	@SuppressWarnings("unchecked")
	public List<ExpedienteLaboratorio> findAll() {
		Criteria criteria = createEntityCriteria();
		return (List<ExpedienteLaboratorio>) criteria.list();
	}
	
	public ExpedienteLaboratorio findByCarnet(Integer carne) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("carne", carne));
		criteria.add(Restrictions.eq("estado", EstadoResultadoLaboratorio.ACTIVO));
		criteria.setMaxResults(1);
		return (ExpedienteLaboratorio) criteria.uniqueResult();
	}
	
	public ExpedienteLaboratorio findByIdPaciente(Integer idPaciente) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("idPaciente", idPaciente));
		criteria.add(Restrictions.eq("estado", EstadoResultadoLaboratorio.ACTIVO));
		criteria.setMaxResults(1);
		return (ExpedienteLaboratorio) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<ExpedienteLaboratorio> findVeinte() {
		Criteria criteria = createEntityCriteria();
		criteria.setMaxResults(20);
		return (List<ExpedienteLaboratorio>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ExpedienteLaboratorio> findByCriteria(Map<String, Object> params) {
		Criteria criteria = createEntityCriteria();
		if(params.containsKey("carne"))
		{
			String pCarne = (String) params.get("carne");
			Integer carne = Integer.parseInt(pCarne);
			criteria.add(Restrictions.eq("carne", carne));
		}
		if(params.containsKey("nombres"))
		{
			criteria.add(Restrictions.ilike("nombres", (String) params.get("nombres"), MatchMode.ANYWHERE));
		}
		if(params.containsKey("apellidos"))
		{
			criteria.add(Restrictions.ilike("apellidos", (String) params.get("apellidos"), MatchMode.ANYWHERE));
		}
		if(params.containsKey("estado"))
		{
			criteria.add(Restrictions.eq("estado", (EstadoResultadoLaboratorio)params.get("estado")));
		}
		return (List<ExpedienteLaboratorio>) criteria.list();
	}
	
}
