package com.sarel.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sarel.web.dao.AcidoUricoDao;
import com.sarel.web.model.AcidoUrico;

@Service("acidoUricoService")
@Transactional
public class AcidoUricoServiceImpl implements AcidoUricoService {

	@Autowired
	private AcidoUricoDao dao;
	
	public AcidoUrico findById(int id){
		return dao.findById(id);
	}
	
	public void updateAcidoUrico(AcidoUrico acidoUrico){
		dao.updateAcidoUrico(acidoUrico);
	}
	
	public void deleteAcidoUrico(AcidoUrico acidoUrico){
		dao.deleteAcidoUrico(acidoUrico);
	}
	
	public void saveAcidoUrico(AcidoUrico acidoUrico){
		dao.saveAcidoUrico(acidoUrico);
	}
	
	public List<AcidoUrico> findAll(){
		return dao.findAll();
	}
	
	public List<AcidoUrico> findByIdExpediente(Integer idExpediente){
		return dao.findByIdExpediente(idExpediente);
	}
}
