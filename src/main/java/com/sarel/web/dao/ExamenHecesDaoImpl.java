package com.sarel.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sarel.web.model.EstadoResultadoLaboratorio;
import com.sarel.web.model.ExamenHeces;

@Repository("ExamenHecesDao")
public class ExamenHecesDaoImpl extends AbstractDao<Integer, ExamenHeces> implements ExamenHecesDao{

	public ExamenHeces findById(int id) {
		return getByKey(id);
	}
	
	public void saveExamenHeces(ExamenHeces examenHeces) {
		persist(examenHeces);
	}
	
	public void updateExamenHeces(ExamenHeces examenHeces) {
		update(examenHeces);
	}
	
	public void deleteExamenHeces(ExamenHeces examenHeces){
		delete(examenHeces);
	}
	
	@SuppressWarnings("unchecked")
	public List<ExamenHeces> findAll() {
		Criteria criteria = createEntityCriteria();
		return (List<ExamenHeces>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ExamenHeces> findByIdExpediente(Integer idExpediente) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("idExpediente", idExpediente));
		criteria.add(Restrictions.eq("estado", EstadoResultadoLaboratorio.ACTIVO));
		return criteria.list();
	}
}
