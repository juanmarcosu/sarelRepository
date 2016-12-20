package com.sarel.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sarel.web.model.EstadoResultadoLaboratorio;
import com.sarel.web.model.PruebaDengue;

@Repository("PruebaDengueDao")
public class PruebaDengueDaoImpl extends AbstractDao<Integer, PruebaDengue> implements PruebaDengueDao{

	public PruebaDengue findById(int id) {
		return getByKey(id);
	}
	
	public void savePruebaDengue(PruebaDengue pruebaDengue) {
		persist(pruebaDengue);
	}
	
	public void updatePruebaDengue(PruebaDengue pruebaDengue) {
		update(pruebaDengue);
	}
	
	public void deletePruebaDengue(PruebaDengue pruebaDengue){
		delete(pruebaDengue);
	}
	
	@SuppressWarnings("unchecked")
	public List<PruebaDengue> findAll() {
		Criteria criteria = createEntityCriteria();
		return (List<PruebaDengue>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<PruebaDengue> findByIdExpediente(Integer idExpediente) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("idExpediente", idExpediente));
		criteria.add(Restrictions.eq("estado", EstadoResultadoLaboratorio.ACTIVO));
		return criteria.list();
	}
}