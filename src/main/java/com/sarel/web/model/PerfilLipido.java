package com.sarel.web.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="PERFIL_LIPIDO")
public class PerfilLipido {
	
	//private User quimicoBiologo; 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "id_expediente", unique=true, nullable = false)
	private int idExpediente;
	
	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy") 
	@Column(name = "fecha_laboratorio", nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate fechaLaboratorio;

	@Column(name = "colesterol_total", unique=false, nullable = false)
	private int colesterolTotal;
	
	@Column(name = "colesterol_alta_densidad", unique=false, nullable = false)
	private int colesterolAltaDensidad;
	
	@Column(name = "colesterol_baja_densidad", unique=false, nullable = false)
	private int colesterolBajaDensidad;
	
	@Column(name = "colesterol_muy_baja_densidad", unique=false, nullable = false)
	private BigDecimal colesterolMuyBajaDensidad;
	
	@Column(name = "trigliceridos", unique=false, nullable = false)
	private int trigliceridos;
	
	@Column(name = "indice_riesgo", unique=false, nullable = false)
	private BigDecimal indiceRiesgo;
	
	@Column(name = "resistencia_insulina", unique=false, nullable = false)
	private BigDecimal resistenciaInsulina;
	/*
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_quimico_biologo", nullable=true)
	public User getQuimicoBiologo(){
		return this.quimicoBiologo;
	}
	public void setQuimicoBiologo(User quimicoBiologo){
		this.quimicoBiologo = quimicoBiologo;
	}*/
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof PerfilLipido))
			return false;
		PerfilLipido other = (PerfilLipido) obj;
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
	public int getColesterolTotal() {
		return colesterolTotal;
	}
	public void setColesterolTotal(int colesterolTotal) {
		this.colesterolTotal = colesterolTotal;
	}
	public int getColesterolAltaDensidad() {
		return colesterolAltaDensidad;
	}
	public void setColesterolAltaDensidad(int colesterolAltaDensidad) {
		this.colesterolAltaDensidad = colesterolAltaDensidad;
	}
	public int getColesterolBajaDensidad() {
		return colesterolBajaDensidad;
	}
	public void setColesterolBajaDensidad(int colesterolBajaDensidad) {
		this.colesterolBajaDensidad = colesterolBajaDensidad;
	}
	public int getTrigliceridos() {
		return trigliceridos;
	}
	public void setTrigliceridos(int trigliceridos) {
		this.trigliceridos = trigliceridos;
	}
	public BigDecimal getColesterolMuyBajaDensidad() {
		return colesterolMuyBajaDensidad;
	}
	public void setColesterolMuyBajaDensidad(BigDecimal colesterolMuyBajaDensidad) {
		this.colesterolMuyBajaDensidad = colesterolMuyBajaDensidad;
	}
	public BigDecimal getIndiceRiesgo() {
		return indiceRiesgo;
	}
	public void setIndiceRiesgo(BigDecimal indiceRiesgo) {
		this.indiceRiesgo = indiceRiesgo;
	}
	public BigDecimal getResistenciaInsulina() {
		return resistenciaInsulina;
	}
	public void setResistenciaInsulina(BigDecimal resistenciaInsulina) {
		this.resistenciaInsulina = resistenciaInsulina;
	}
	public LocalDate getFechaLaboratorio() {
		return fechaLaboratorio;
	}
	public void setFechaLaboratorio(LocalDate fechaLaboratorio) {
		this.fechaLaboratorio = fechaLaboratorio;
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
		return "PerfilLipido [id ="+ id +", idExpediente=" + idExpediente 
				+", colesterolTotal=" + colesterolTotal +", colesterolAltaDensidad=" + colesterolAltaDensidad +", colesterolBajaDensidad=" + colesterolBajaDensidad 
				+", trigliceridos=" + trigliceridos +", colesterolMuyBajaDensidad=" + colesterolMuyBajaDensidad +", indiceRiesgo=" + indiceRiesgo 
			//	+", resistenciaInsulina=" + resistenciaInsulina +", quimicoBiologo=" + quimicoBiologo.getId() 
				+ "]";
	}
	
}
