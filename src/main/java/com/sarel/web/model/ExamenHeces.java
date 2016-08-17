package com.sarel.web.model;

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
@Table(name="EXAMEN_HECES")
public class ExamenHeces {
	
	private EstadoResultadoLaboratorio estado = EstadoResultadoLaboratorio.ACTIVO;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "id_expediente", unique=false, nullable = false)
	private int idExpediente;
	
	@Column(name = "id_quimico_biologo", unique=false, nullable = false)
	private int idQuimicoBiologo;
	
	@Column(name = "aspecto", unique=false, nullable = false)
	private String aspecto;
	
	@Column(name = "moco", unique=false, nullable = false)
	private String moco;
	
	@Column(name = "sangre", unique=false, nullable = false)
	private String sangre;
	
	@Column(name = "restos_alimenticios", unique=false, nullable = false)
	private String restosAlimenticios;

	@Column(name = "texto_parasitos", unique=false, nullable = false)
	private String textoParasitos;
	
	@Column(name = "parasitos", unique=false, nullable = false)
	private String parasitos;
	
	@Column(name = "almidones", unique=false, nullable = false)
	private String almidones;
	
	@Column(name = "grasas", unique=false, nullable = false)
	private String grasas;
	
	@Column(name = "fibras_musculares", unique=false, nullable = false)
	private String fibrasMusculares;
	
	@Column(name = "jabones", unique=false, nullable = false)
	private String jabones;
	
	@Column(name = "texto_otros", unique=false, nullable = false)
	private String textoOtros;
	
	@Column(name = "otros", unique=false, nullable = false)
	private String otros;
	
	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy") 
	@Column(name = "fecha_laboratorio", nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate fechaLaboratorio;
	
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
		if (!(obj instanceof ExamenHeces))
			return false;
		ExamenHeces other = (ExamenHeces) obj;
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
	public String getAspecto() {
		return aspecto;
	}
	public void setAspecto(String aspecto) {
		this.aspecto = aspecto;
	}
	public String getMoco() {
		return moco;
	}
	public void setMoco(String moco) {
		this.moco = moco;
	}
	public String getSangre() {
		return sangre;
	}
	public void setSangre(String sangre) {
		this.sangre = sangre;
	}
	public String getRestosAlimenticios() {
		return restosAlimenticios;
	}
	public void setRestosAlimenticios(String restosAlimenticios) {
		this.restosAlimenticios = restosAlimenticios;
	}
	public String getTextoParasitos() {
		return textoParasitos;
	}
	public void setTextoParasitos(String textoParasitos) {
		this.textoParasitos = textoParasitos;
	}
	public String getParasitos() {
		return parasitos;
	}
	public void setParasitos(String parasitos) {
		this.parasitos = parasitos;
	}
	public String getAlmidones() {
		return almidones;
	}
	public void setAlmidones(String almidones) {
		this.almidones = almidones;
	}
	public String getGrasas() {
		return grasas;
	}
	public void setGrasas(String grasas) {
		this.grasas = grasas;
	}
	public String getFibrasMusculares() {
		return fibrasMusculares;
	}
	public void setFibrasMusculares(String fibrasMusculares) {
		this.fibrasMusculares = fibrasMusculares;
	}
	public String getJabones() {
		return jabones;
	}
	public void setJabones(String jabones) {
		this.jabones = jabones;
	}
	public String getTextoOtros() {
		return textoOtros;
	}
	public void setTextoOtros(String textoOtros) {
		this.textoOtros = textoOtros;
	}
	public String getOtros() {
		return otros;
	}
	public void setOtros(String otros) {
		this.otros = otros;
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
		return "ExamenHeces [id ="+ id +", idExpediente=" + idExpediente 
				+", idQuimicoBiologo=" + idQuimicoBiologo
				//+", estado=" + EstadoResultadoLaboratorio.
				+ "]";
	}
	
}
