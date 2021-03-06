package com.sarel.web.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sarel.web.model.Paciente;

@Repository("pacienteDao")
public class PacienteDaoImpl extends AbstractDao<Integer, Paciente> implements PacienteDao{

	public Paciente findById(int id) {
		return getByKey(id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Paciente> findAll() {
		Criteria criteria = createEntityCriteria();
		return (List<Paciente>) criteria.list();
	}
	
	public Paciente findByCarnet(Integer carne) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("carne", carne));
		return (Paciente) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Paciente> findVeinte() {
		Criteria criteria = createEntityCriteria();
		criteria.setMaxResults(20);
		return (List<Paciente>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Paciente> findByCriteria(Map<String, Object> params) {
		Criteria criteria = createEntityCriteria();
		if(params.containsKey("carne"))
		{
			String pCarne = (String) params.get("carne");
			Integer carne = Integer.parseInt(pCarne);
			criteria.add(Restrictions.eq("carne", carne));
		}
		if(params.containsKey("nombre"))
		{
			criteria.add(Restrictions.ilike("nombre", (String) params.get("nombre"), MatchMode.ANYWHERE));
		}
		if(params.containsKey("apellido"))
		{
			criteria.add(Restrictions.ilike("apellido", (String) params.get("apellido"), MatchMode.ANYWHERE));
		}
		return (List<Paciente>) criteria.list();
	}
	
}
