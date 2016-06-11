package com.sarel.web.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="PRUEBA_VDRL")
public class PruebaVDRL {
	
	private EstadoResultadoLaboratorio estado = EstadoResultadoLaboratorio.ACTIVO;
	private Resultado resultado;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "id_expediente", unique=false, nullable = false)
	private int idExpediente;
	
	@Column(name = "id_quimico_biologo", unique=false, nullable = false)
	private int idQuimicoBiologo;
	
	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy") 
	@Column(name = "fecha_laboratorio", nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate fechaLaboratorio;

	@Enumerated(EnumType.STRING)
	@Column(name="resultado_laboratorio")
	public Resultado getResultado(){
		return this.resultado;
	}
	public void setResultado(Resultado resultado){
		this.resultado = resultado;
	}
	
	@Column(name = "nivel_VDRL", unique=false, nullable = false)
	private BigDecimal nivelVDRL;
	
	@Enumerated(EnumType.STRING)
	@Column(name="estado")
	public EstadoResultadoLaboratorio getEstado(){
		return this.estado;
	}
	public void setEstado(EstadoResultadoLaboratorio estado){
		this.estado = estado;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof PruebaVDRL))
			return false;
		PruebaVDRL other = (PruebaVDRL) obj;
		if (id != other.id)
			return false;
		if (idExpediente != other.idExpediente)
			return false;
		return true;
	}
	
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
	public BigDecimal getNivelVDRL() {
		return nivelVDRL;
	}
	public void setNivelVDRL(BigDecimal nivelVDRL) {
		this.nivelVDRL = nivelVDRL;
	}
	public LocalDate getFechaLaboratorio() {
		return fechaLaboratorio;
	}
	public void setFechaLaboratorio(LocalDate fechaLaboratorio) {
		this.fechaLaboratorio = fechaLaboratorio;
	}
	public int getIdQuimicoBiologo() {
		return idQuimicoBiologo;
	}
	public void setIdQuimicoBiologo(int idQuimicoBiologo) {
		this.idQuimicoBiologo = idQuimicoBiologo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	
	@Override
	public String toString() {
		return "PruebaVDRL [id ="+ id +", idExpediente=" + idExpediente 
				+", nivelVDRL=" + nivelVDRL +", idQuimicoBiologo=" + idQuimicoBiologo
				//+", estado=" + EstadoResultadoLaboratorio.
				+ "]";
	}
	
}
