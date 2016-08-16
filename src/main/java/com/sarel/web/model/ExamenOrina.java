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
@Table(name="EXAMEN_ORINA")
public class ExamenOrina {
	
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

	@Column(name = "color", unique=false, nullable = false)
	private String color;
	
	@Column(name = "otro_color", unique=false, nullable = false)
	private String otroColor;
	
	@Column(name = "aspecto", unique=false, nullable = false)
	private String aspecto;
	
	@Column(name = "ph", unique=false, nullable = false)
	private BigDecimal ph;
	
	@Column(name = "densidad", unique=false, nullable = false)
	private BigDecimal densidad;
	
	@Column(name = "leucositos", unique=false, nullable = false)
	private String leucositos;
	
	@Column(name = "eritrositos", unique=false, nullable = false)
	private String eritrositos;
	
	@Column(name = "celulas_epiteliales", unique=false, nullable = false)
	private String celulasEpiteliales;
	
	@Column(name = "moco", unique=false, nullable = false)
	private String moco;
	
	@Column(name = "texto_cristales", unique=false, nullable = false)
	private String textoCristales;
	
	@Column(name = "cristales", unique=false, nullable = false)
	private String cristales;
	
	@Column(name = "texto_cilindros", unique=false, nullable = false)
	private String textoCilindros;
	
	@Column(name = "cilindros", unique=false, nullable = false)
	private String cilindros;
	
	@Column(name = "bacterias", unique=false, nullable = false)
	private String bacterias;
	
	@Column(name = "texto_bacterias", unique=false, nullable = false)
	private String textoBacterias;
	
	@Column(name = "otros", unique=false, nullable = false)
	private String otros;
	
	@Column(name = "texto_otros", unique=false, nullable = false)
	private String textoOtros;
	
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
		if (!(obj instanceof ExamenOrina))
			return false;
		ExamenOrina other = (ExamenOrina) obj;
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
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getOtroColor() {
		return otroColor;
	}
	public void setOtroColor(String otroColor) {
		this.otroColor = otroColor;
	}
	public String getAspecto() {
		return aspecto;
	}
	public void setAspecto(String aspecto) {
		this.aspecto = aspecto;
	}
	public BigDecimal getPh() {
		return ph;
	}
	public void setPh(BigDecimal ph) {
		this.ph = ph;
	}
	public BigDecimal getDensidad() {
		return densidad;
	}
	public void setDensidad(BigDecimal densidad) {
		this.densidad = densidad;
	}
	public String getLeucositos() {
		return leucositos;
	}
	public void setLeucositos(String leucositos) {
		this.leucositos = leucositos;
	}
	public String getEritrositos() {
		return eritrositos;
	}
	public void setEritrositos(String eritrositos) {
		this.eritrositos = eritrositos;
	}
	public String getCelulasEpiteliales() {
		return celulasEpiteliales;
	}
	public void setCelulasEpiteliales(String celulasEpiteliales) {
		this.celulasEpiteliales = celulasEpiteliales;
	}
	public String getMoco() {
		return moco;
	}
	public void setMoco(String moco) {
		this.moco = moco;
	}
	public String getTextoCristales() {
		return textoCristales;
	}
	public void setTextoCristales(String textoCristales) {
		this.textoCristales = textoCristales;
	}
	public String getCristales() {
		return cristales;
	}
	public void setCristales(String cristales) {
		this.cristales = cristales;
	}
	public String getTextoCilindros() {
		return textoCilindros;
	}
	public void setTextoCilindros(String textoCilindros) {
		this.textoCilindros = textoCilindros;
	}
	public String getCilindros() {
		return cilindros;
	}
	public void setCilindros(String cilindros) {
		this.cilindros = cilindros;
	}
	public String getBacterias() {
		return bacterias;
	}
	public void setBacterias(String bacterias) {
		this.bacterias = bacterias;
	}
	public String getTextoBacterias() {
		return textoBacterias;
	}
	public void setTextoBacterias(String textoBacterias) {
		this.textoBacterias = textoBacterias;
	}
	public String getOtros() {
		return otros;
	}
	public void setOtros(String otros) {
		this.otros = otros;
	}
	public String getTextoOtros() {
		return textoOtros;
	}
	public void setTextoOtros(String textoOtros) {
		this.textoOtros = textoOtros;
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
		return "ExamenOrina [id ="+ id +", idExpediente=" + idExpediente 
				+", idQuimicoBiologo=" + idQuimicoBiologo
				//+", estado=" + EstadoResultadoLaboratorio.
				+ "]";
	}
	
}
