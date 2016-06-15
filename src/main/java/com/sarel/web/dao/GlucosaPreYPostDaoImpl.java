package com.sarel.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sarel.web.model.EstadoResultadoLaboratorio;
import com.sarel.web.model.GlucosaPreYPost;

@Repository("GlucosaPreYPostDao")
public class GlucosaPreYPostDaoImpl extends AbstractDao<Integer, GlucosaPreYPost> implements GlucosaPreYPostDao{

	public GlucosaPreYPost findById(int id) {
		return getByKey(id);
	}
	
	public void saveGlucosaPreYPost(GlucosaPreYPost glucosaPreYPost) {
		persist(glucosaPreYPost);
	}
	
	public void updateGlucosaPreYPost(GlucosaPreYPost glucosaPreYPost) {
		update(glucosaPreYPost);
	}
	
	public void deleteGlucosaPreYPost(GlucosaPreYPost glucosaPreYPost){
		delete(glucosaPreYPost);
	}
	
	@SuppressWarnings("unchecked")
	public List<GlucosaPreYPost> findAll() {
		Criteria criteria = createEntityCriteria();
		return (List<GlucosaPreYPost>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<GlucosaPreYPost> findByIdExpediente(Integer idExpediente) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("idExpediente", idExpediente));
		criteria.add(Restrictions.eq("estado", EstadoResultadoLaboratorio.ACTIVO));
		return criteria.list();
	}
}
