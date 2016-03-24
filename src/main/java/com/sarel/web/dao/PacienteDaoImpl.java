package com.sarel.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
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
	
}
