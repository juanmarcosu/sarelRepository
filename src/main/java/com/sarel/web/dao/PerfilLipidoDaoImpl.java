package com.sarel.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sarel.web.model.PerfilLipido;

@Repository("PerfilLipidoDao")
public class PerfilLipidoDaoImpl extends AbstractDao<Integer, PerfilLipido> implements PerfilLipidoDao{

	public PerfilLipido findById(int id) {
		return getByKey(id);
	}
	
	public void savePerfilLipido(PerfilLipido perfilLipido) {
		persist(perfilLipido);
	}
	
	public void updatePerfilLipido(PerfilLipido perfilLipido) {
		update(perfilLipido);
	}
	
	public void deletePerfilLipido(PerfilLipido perfilLipido){
		delete(perfilLipido);
	}
	
	@SuppressWarnings("unchecked")
	public List<PerfilLipido> findAll() {
		Criteria criteria = createEntityCriteria();
		return (List<PerfilLipido>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<PerfilLipido> findByIdExpediente(Integer idExpediente) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("idExpediente", idExpediente));
		return criteria.list();
	}
}
