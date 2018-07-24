package com.sarel.web.service;

import java.util.Date;
import java.util.List;

import com.sarel.web.model.AcidoUrico;

public interface AcidoUricoService {
	
	AcidoUrico findById(int id);
	void saveAcidoUrico(AcidoUrico acidoUrico);
	void updateAcidoUrico(AcidoUrico acidoUrico);
	void deleteAcidoUrico(AcidoUrico acidoUrico);
	List<AcidoUrico> findAll();
	List<AcidoUrico> findByIdExpediente(Integer idExpediente);
	List<AcidoUrico> findByIdExpedienteAndDates(Integer idExpediente, Date fechaInicial, Date fechaFinal);
}
