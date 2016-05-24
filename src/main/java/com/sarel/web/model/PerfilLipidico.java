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
@Table(name="PERFIL_LIPIDICO")
public class PerfilLipidico {
	
	private EstadoResultadoLaboratorio estado = EstadoResultadoLaboratorio.ACTIVO;
	
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

	@Column(name = "colesterol_total", unique=false, nullable = false)
	private BigDecimal colesterolTotal;
	
	@Column(name = "colesterol_alta_densidad", unique=false, nullable = false)
	private BigDecimal colesterolAltaDensidad;
	
	@Column(name = "colesterol_baja_densidad", unique=false, nullable = false)
	private BigDecimal colesterolBajaDensidad;
	
	@Column(name = "colesterol_muy_baja_densidad", unique=false, nullable = false)
	private BigDecimal colesterolMuyBajaDensidad;
	
	@Column(name = "trigliceridos", unique=false, nullable = false)
	private BigDecimal trigliceridos;
	
	@Column(name = "indice_riesgo", unique=false, nullable = false)
	private BigDecimal indiceRiesgo;
	
	@Column(name = "resistencia_insulina", unique=false, nullable = false)
	private BigDecimal resistenciaInsulina;
	
	/*@ManyToOne(optional=false)
    @JoinColumn(name="id_quimico_biologo")
    private User quimicoBiologo;
	
	public User getQuimicoBiologo() {
		return quimicoBiologo;
	}
	public void setQuimicoBiologo(User quimicoBiologo) {
		this.quimicoBiologo = quimicoBiologo;
	}*/
	
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
		if (!(obj instanceof PerfilLipidico))
			return false;
		PerfilLipidico other = (PerfilLipidico) obj;
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
	public BigDecimal getColesterolTotal() {
		return colesterolTotal;
	}
	public void setColesterolTotal(BigDecimal colesterolTotal) {
		this.colesterolTotal = colesterolTotal;
	}
	public BigDecimal getColesterolAltaDensidad() {
		return colesterolAltaDensidad;
	}
	public void setColesterolAltaDensidad(BigDecimal colesterolAltaDensidad) {
		this.colesterolAltaDensidad = colesterolAltaDensidad;
	}
	public BigDecimal getColesterolBajaDensidad() {
		return colesterolBajaDensidad;
	}
	public void setColesterolBajaDensidad(BigDecimal colesterolBajaDensidad) {
		this.colesterolBajaDensidad = colesterolBajaDensidad;
	}
	public BigDecimal getTrigliceridos() {
		return trigliceridos;
	}
	public void setTrigliceridos(BigDecimal trigliceridos) {
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
		return "PerfilLipidico [id ="+ id +", idExpediente=" + idExpediente 
				+", colesterolTotal=" + colesterolTotal +", colesterolAltaDensidad=" + colesterolAltaDensidad +", colesterolBajaDensidad=" + colesterolBajaDensidad 
				+", trigliceridos=" + trigliceridos +", colesterolMuyBajaDensidad=" + colesterolMuyBajaDensidad +", indiceRiesgo=" + indiceRiesgo 
				+", resistenciaInsulina=" + resistenciaInsulina +", idQuimicoBiologo=" + idQuimicoBiologo
				//+", estado=" + EstadoResultadoLaboratorio.
				+ "]";
	}
	
}
