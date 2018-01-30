package com.sarel.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sarel.web.model.EstadoResultadoLaboratorio;
import com.sarel.web.model.AntigenoProstatico;

@Repository("AntigenoProstaticoDao")
public class AntigenoProstaticoDaoImpl extends AbstractDao<Integer, AntigenoProstatico> implements AntigenoProstaticoDao{

	public AntigenoProstatico findById(int id) {
		return getByKey(id);
	}
	
	public void saveAntigenoProstatico(AntigenoProstatico antigenoProstatico) {
		persist(antigenoProstatico);
	}
	
	public void updateAntigenoProstatico(AntigenoProstatico antigenoProstatico) {
		update(antigenoProstatico);
	}
	
	public void deleteAntigenoProstatico(AntigenoProstatico antigenoProstatico){
		delete(antigenoProstatico);
	}
	
	@SuppressWarnings("unchecked")
	public List<AntigenoProstatico> findAll() {
		Criteria criteria = createEntityCriteria();
		return (List<AntigenoProstatico>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<AntigenoProstatico> findByIdExpediente(Integer idExpediente) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("idExpediente", idExpediente));
		criteria.add(Restrictions.eq("estado", EstadoResultadoLaboratorio.ACTIVO));
		return criteria.list();
	}
}