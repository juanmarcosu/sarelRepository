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
@Table(name="HEMATOLOGIA_COMPLETA")
public class HematologiaCompleta {
	
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

	@Column(name = "rbc", unique=false, nullable = false)
	private BigDecimal rbc;
	
	@Column(name = "hct", unique=false, nullable = false)
	private BigDecimal hct;
	
	@Column(name = "mcv", unique=false, nullable = false)
	private BigDecimal mcv;
	
	@Column(name = "hgb", unique=false, nullable = false)
	private BigDecimal hgb;
	
	@Column(name = "mch", unique=false, nullable = false)
	private BigDecimal mch;
	
	@Column(name = "mchc", unique=false, nullable = false)
	private BigDecimal mchc;
	
	@Column(name = "plt", unique=false, nullable = false)
	private BigDecimal plt;
	
	@Column(name = "wbc", unique=false, nullable = false)
	private BigDecimal wbc;
	
	@Column(name = "lym", unique=false, nullable = false)
	private BigDecimal lym;
	
	@Column(name = "gran", unique=false, nullable = false)
	private BigDecimal gran;
	
	@Column(name = "mid", unique=false, nullable = false)
	private BigDecimal mid;
	
	@Column(name = "vse", unique=false, nullable = true)
	private Integer vse;
	
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
		if (!(obj instanceof HematologiaCompleta))
			return false;
		HematologiaCompleta other = (HematologiaCompleta) obj;
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
	public BigDecimal getRbc() {
		return rbc;
	}
	public void setRbc(BigDecimal rbc) {
		this.rbc = rbc;
	}
	public BigDecimal getHct() {
		return hct;
	}
	public void setHct(BigDecimal hct) {
		this.hct = hct;
	}
	public BigDecimal getMcv() {
		return mcv;
	}
	public void setMcv(BigDecimal mcv) {
		this.mcv = mcv;
	}
	public BigDecimal getHgb() {
		return hgb;
	}
	public void setHgb(BigDecimal hgb) {
		this.hgb = hgb;
	}
	public BigDecimal getMch() {
		return mch;
	}
	public void setMch(BigDecimal mch) {
		this.mch = mch;
	}
	public BigDecimal getMchc() {
		return mchc;
	}
	public void setMchc(BigDecimal mchc) {
		this.mchc = mchc;
	}
	public BigDecimal getPlt() {
		return plt;
	}
	public void setPlt(BigDecimal plt) {
		this.plt = plt;
	}
	public BigDecimal getWbc() {
		return wbc;
	}
	public void setWbc(BigDecimal wbc) {
		this.wbc = wbc;
	}
	public BigDecimal getLym() {
		return lym;
	}
	public void setLym(BigDecimal lym) {
		this.lym = lym;
	}
	public BigDecimal getGran() {
		return gran;
	}
	public void setGran(BigDecimal gran) {
		this.gran = gran;
	}
	public BigDecimal getMid() {
		return mid;
	}
	public void setMid(BigDecimal mid) {
		this.mid = mid;
	}
	public Integer getVse() {
		return vse;
	}
	public void setVse(Integer vse) {
		this.vse = vse;
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
		return "HematologiaCompleta [id ="+ id +", idExpediente=" + idExpediente 
				+", rbc=" + rbc +", hct=" + hct +", mcv=" + mcv +", hgb=" + hgb +", mch=" + mch +", mchc=" + mchc 
				+", plt=" + plt +", wbc=" + wbc +", lym=" + lym +", gran=" + gran +", mid=" + mid
				+", vse=" + vse +", idQuimicoBiologo=" + idQuimicoBiologo
				//+", estado=" + EstadoResultadoLaboratorio.
				+ "]";
	}
	
}
