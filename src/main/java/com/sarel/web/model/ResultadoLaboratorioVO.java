package com.sarel.web.model;

import org.joda.time.LocalDate;

public class ResultadoLaboratorioVO {

	private int id;
	private int idExpediente;
	private String quimicoBiologo;
	private LocalDate fechaLaboratorio;
	private String estado;
	private String tipoLaboratorio;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdExpediente() {
		return idExpediente;
	}
	public void setIdExpediente(int idExpediente) {
		this.idExpediente = idExpediente;
	}
	public String getQuimicoBiologo() {
		return quimicoBiologo;
	}
	public void setQuimicoBiologo(String quimicoBiologo) {
		this.quimicoBiologo = quimicoBiologo;
	}
	public LocalDate getFechaLaboratorio() {
		return fechaLaboratorio;
	}
	public void setFechaLaboratorio(LocalDate fechaLaboratorio) {
		this.fechaLaboratorio = fechaLaboratorio;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getTipoLaboratorio() {
		return tipoLaboratorio;
	}
	public void setTipoLaboratorio(String tipoLaboratorio) {
		this.tipoLaboratorio = tipoLaboratorio;
	}
}
