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
@Table(name="PRUEBA_SEROLOGICA")
public class PruebaSerologica {
	
	private EstadoResultadoLaboratorio estado = EstadoResultadoLaboratorio.ACTIVO;
	private Resultado resultadoFactorReumatoide;
	private Resultado resultadoProteinaCReactiva;
	private Resultado resultadoAntiEstreptolisinaO;
	
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
	@Column(name="resultado_factor_reumatoide")
	public Resultado getResultadoFactorReumatoide(){
		return this.resultadoFactorReumatoide;
	}
	public void setResultadoFactorReumatoide(Resultado resultadoFactorReumatoide){
		this.resultadoFactorReumatoide = resultadoFactorReumatoide;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="resultado_proteina_c_reactiva")
	public Resultado getResultadoProteinaCReactiva() {
		return resultadoProteinaCReactiva;
	}
	public void setResultadoProteinaCReactiva(Resultado resultadoProteinaCReactiva) {
		this.resultadoProteinaCReactiva = resultadoProteinaCReactiva;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="resultado_anti_estreptolisina_o")
	public Resultado getResultadoAntiEstreptolisinaO() {
		return resultadoAntiEstreptolisinaO;
	}
	public void setResultadoAntiEstreptolisinaO(Resultado resultadoAntiEstreptolisinaO) {
		this.resultadoAntiEstreptolisinaO = resultadoAntiEstreptolisinaO;
	}

	@Column(name = "factor_reumatoide", unique=false, nullable = true)
	private BigDecimal factorReumatoide;
	
	@Column(name = "proteina_c_reactiva", unique=false, nullable = true)
	private BigDecimal proteinaCReactiva;
	
	@Column(name = "anti_estreptolisina_o", unique=false, nullable = true)
	private BigDecimal antiEstreptolisinaO;
	
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
		if (!(obj instanceof PruebaSerologica))
			return false;
		PruebaSerologica other = (PruebaSerologica) obj;
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
	public BigDecimal getFactorReumatoide() {
		return factorReumatoide;
	}
	public void setFactorReumatoide(BigDecimal factorReumatoide) {
		this.factorReumatoide = factorReumatoide;
	}
	public BigDecimal getProteinaCReactiva() {
		return proteinaCReactiva;
	}
	public void setProteinaCReactiva(BigDecimal proteinaCReactiva) {
		this.proteinaCReactiva = proteinaCReactiva;
	}
	public BigDecimal getAntiEstreptolisinaO() {
		return antiEstreptolisinaO;
	}
	public void setAntiEstreptolisinaO(BigDecimal antiEstreptolisinaO) {
		this.antiEstreptolisinaO = antiEstreptolisinaO;
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
		return "PruebaSerologica [id ="+ id +", idExpediente=" + idExpediente 
				+", factorReumatoide=" + factorReumatoide +", proteinaCReactiva=" + proteinaCReactiva 
				+", antiEstreptolisinaO=" + antiEstreptolisinaO +", idQuimicoBiologo=" + idQuimicoBiologo
				//+", estado=" + EstadoResultadoLaboratorio.
				+ "]";
	}
	
}
