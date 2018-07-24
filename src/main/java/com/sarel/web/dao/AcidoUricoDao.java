package com.sarel.web.dao;

import java.util.Date;
import java.util.List;

import com.sarel.web.model.AcidoUrico;

public interface AcidoUricoDao {
	
	AcidoUrico findById(int id);
	void saveAcidoUrico(AcidoUrico acidoUrico);
	void updateAcidoUrico(AcidoUrico acidoUrico);
	void deleteAcidoUrico(AcidoUrico acidoUrico);
	List<AcidoUrico> findAll();
	List<AcidoUrico> findByIdExpediente(Integer idExpediente);
	List<AcidoUrico> findByIdExpedienteAndDates(Integer idExpediente, Date fechaInicial, Date fechaFinal);
}
