package com.sarel.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sarel.web.model.EstadoResultadoLaboratorio;
import com.sarel.web.model.PerfilLipidico;

@Repository("PerfilLipidicoDao")
public class PerfilLipidicoDaoImpl extends AbstractDao<Integer, PerfilLipidico> implements PerfilLipidicoDao{

	public PerfilLipidico findById(int id) {
		return getByKey(id);
	}
	
	public void savePerfilLipidico(PerfilLipidico perfilLipidico) {
		persist(perfilLipidico);
	}
	
	public void updatePerfilLipidico(PerfilLipidico perfilLipidico) {
		update(perfilLipidico);
	}
	
	public void deletePerfilLipidico(PerfilLipidico perfilLipidico){
		delete(perfilLipidico);
	}
	
	@SuppressWarnings("unchecked")
	public List<PerfilLipidico> findAll() {
		Criteria criteria = createEntityCriteria();
		return (List<PerfilLipidico>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<PerfilLipidico> findByIdExpediente(Integer idExpediente) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("idExpediente", idExpediente));
		criteria.add(Restrictions.eq("estado", EstadoResultadoLaboratorio.ACTIVO));
		return criteria.list();
	}
}

