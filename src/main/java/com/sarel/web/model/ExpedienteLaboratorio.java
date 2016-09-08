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
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="EXPEDIENTE_LABORATORIO", schema="laboratorio")
public class ExpedienteLaboratorio {

	private EstadoResultadoLaboratorio estado = EstadoResultadoLaboratorio.ACTIVO;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "id_paciente")
	private Integer idPaciente;

	@Size(min=3, max=100)
	@Column(name = "nombres", nullable = false)
	private String nombres;

	@Size(min=3, max=100)
	@Column(name = "apellidos", nullable = false)
	private String apellidos;
	
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	@Column(name = "fecha_nacimiento", nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate fechaNacimiento;
	
	@Column(name = "no_registro", unique=true, nullable = false)
	private Integer carne;
	
	@Column(name = "direccion", nullable = true)
	private String direccion;

	@Column(name = "telefono", nullable = true)
	private String telefono;
	
	@Column(name = "movil", nullable = true)
	private String movil;

	@Column(name = "email", nullable = true)
	private String email;
	
	@Enumerated(EnumType.STRING)
	@Column(name="estado")
	public EstadoResultadoLaboratorio getEstado(){
		return this.estado;
	}
	public void setEstado(EstadoResultadoLaboratorio estado){
		this.estado = estado;
	}
	
	@Column(name = "sexo", nullable = false)
	private Sexo sexo;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPaciente;
		result = prime * result + ((carne == null) ? 0 : carne);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ExpedienteLaboratorio))
			return false;
		ExpedienteLaboratorio other = (ExpedienteLaboratorio) obj;
		if (id != other.id)
			return false;
		if (idPaciente != other.idPaciente)
			return false;
		if (carne == null) {
			if (other.carne != null)
				return false;
		} else if (carne != other.carne)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Integer getCarne() {
		return carne;
	}

	public void setCarne(Integer carne) {
		this.carne = carne;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getMovil() {
		return movil;
	}

	public void setMovil(String movil) {
		this.movil = movil;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	@Override
	public String toString() {
		return "ExpedienteLaboratorio [id ="+ id +", idPaciente=" + idPaciente + ", nombres=" + nombres + ", apellidos=" + apellidos + ", fechaNacimiento="
				+ fechaNacimiento + ", carne=" + carne 
				+ ", direccion=" + direccion + ", telefono=" + telefono 
				+ ", movil=" + movil + ", email=" + email 
				+ "]";
	}	

}
