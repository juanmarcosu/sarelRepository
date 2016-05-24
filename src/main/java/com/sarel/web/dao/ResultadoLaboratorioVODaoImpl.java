package com.sarel.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sarel.web.model.ResultadoLaboratorioVO;

@Repository("ResultadoLaboratorioVODao")
public class ResultadoLaboratorioVODaoImpl extends AbstractDao<Integer, ResultadoLaboratorioVO> implements ResultadoLaboratorioVODao{
	
	@SuppressWarnings("unchecked")
	public List<ResultadoLaboratorioVO> findByIdExpediente(Integer idExpediente) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("idExpediente", idExpediente));
		return criteria.list();
	}

}
