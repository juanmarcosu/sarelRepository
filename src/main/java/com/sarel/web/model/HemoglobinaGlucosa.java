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
@Table(name="HEMOGLOBINA_GLUCOSA")
public class HemoglobinaGlucosa {
	
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
	
	@Column(name = "hemoglobina_glicosilada", unique=false, nullable = false)
	private BigDecimal hemoglobinaGlicosilada;
	
	@Column(name = "nivel_promedio_glucosa", unique=false, nullable = false)
	private BigDecimal nivelPromedioGlucosa;
	
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
		if (!(obj instanceof HemoglobinaGlucosa))
			return false;
		HemoglobinaGlucosa other = (HemoglobinaGlucosa) obj;
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
	public BigDecimal getHemoglobinaGlicosilada() {
		return hemoglobinaGlicosilada;
	}
	public void setHemoglobinaGlicosilada(BigDecimal hemoglobinaGlicosilada) {
		this.hemoglobinaGlicosilada = hemoglobinaGlicosilada;
	}
	public BigDecimal getNivelPromedioGlucosa() {
		return nivelPromedioGlucosa;
	}
	public void setNivelPromedioGlucosa(BigDecimal nivelPromedioGlucosa) {
		this.nivelPromedioGlucosa = nivelPromedioGlucosa;
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
		return "HemoglobinaGlucosa [id ="+ id +", idExpediente=" + idExpediente 
				+", hemoglobinaGlicosilada=" + hemoglobinaGlicosilada +", nivelPromedioGlucosa=" + nivelPromedioGlucosa
				+ ", idQuimicoBiologo=" + idQuimicoBiologo
				//+", estado=" + EstadoResultadoLaboratorio.
				+ "]";
	}
	
}
