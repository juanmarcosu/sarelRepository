package com.sarel.web.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sarel.web.model.Employee;
import com.sarel.web.model.ExpedienteLaboratorio;

@Repository("expedienteLaboratorioDao")
public class ExpedienteLaboratorioDaoImpl extends AbstractDao<Integer, ExpedienteLaboratorio> implements ExpedienteLaboratorioDao{

	public ExpedienteLaboratorio findById(int id) {
		return getByKey(id);
	}
	
	public void saveExpedienteLaboratorio(ExpedienteLaboratorio expediente) {
		persist(expediente);
	}
	
	@SuppressWarnings("unchecked")
	public List<ExpedienteLaboratorio> findAll() {
		Criteria criteria = createEntityCriteria();
		return (List<ExpedienteLaboratorio>) criteria.list();
	}
	
	public ExpedienteLaboratorio findByCarnet(Integer carne) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("carne", carne));
		return (ExpedienteLaboratorio) criteria.uniqueResult();
	}
	
	public ExpedienteLaboratorio findByIdPaciente(Integer idPaciente) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("idPaciente", idPaciente));
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
		return (List<ExpedienteLaboratorio>) criteria.list();
	}
	
}
