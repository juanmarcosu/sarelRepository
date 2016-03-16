package com.sarel.web.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="paciente")
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPaciente;

	@Size(min=3, max=100)
	@Column(name = "nombre", nullable = false)
	private String nombre;

	@Size(min=3, max=100)
	@Column(name = "apellido", nullable = false)
	private String apellido;
	
	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy") 
	@Column(name = "fecha_nac", nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate fechaNac;

	/*@NotNull
	@Digits(integer=8, fraction=2)
	@Column(name = "SALARY", nullable = false)
	private BigDecimal salary;*/
	
	@Column(name = "carne", unique=true, nullable = true)
	private Integer carne;
	
	@Size(min=3, max=300)
	@Column(name = "direccion", nullable = false)
	private String direccion;

	@Size(min=3, max=50)
	@Column(name = "telefono", nullable = false)
	private String telefono;
	
	@Size(min=3, max=50)
	@Column(name = "movil", nullable = false)
	private String movil;

	@Size(min=3, max=80)
	@Column(name = "email", nullable = false)
	private String email;

	@Size(min=3, max=200)
	@Column(name = "emer_nombre", nullable = true)
	private String emerNombre;
	
	@Column(name = "idemer_parentesco", unique=true, nullable = true)
	private Integer idEmerParentesco;
	
	@Size(min=3, max=50)
	@Column(name = "emer_telefono", nullable = true)
	private String emerTelefono;

	@Size(min=3, max=50)
	@Column(name = "emer_movil", nullable = true)
	private String emerMovil;
	
	public int getIdpaciente() {
		return idPaciente;
	}

	public void setIdpaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}

	public void setName(String apellido) {
		this.apellido = apellido;
	}

	public LocalDate getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}

	/*public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}*/

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

	public String getEmerNombre() {
		return emerNombre;
	}
	
	public void setEmerNombre(String emerNombre) {
		this.emerNombre = emerNombre;
	}
	
	public Integer getIdEmerParentesco() {
		return idEmerParentesco;
	}

	public void setIdEmerParentesco(Integer idEmerParentesco) {
		this.idEmerParentesco = idEmerParentesco;
	}

	public String getEmerTelefono() {
		return emerTelefono;
	}
	
	public void setEmerTelefono(String emerTelefono) {
		this.emerTelefono = emerTelefono;
	}

	public String getEmerMovil() {
		return emerMovil;
	}
	
	public void setEmerMovil(String emerMovil) {
		this.emerMovil = emerMovil;
	}
	
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
		if (!(obj instanceof Paciente))
			return false;
		Paciente other = (Paciente) obj;
		if (idPaciente != other.idPaciente)
			return false;
		if (carne == null) {
			if (other.carne != null)
				return false;
		} else if (carne != other.carne)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Paciente [idPaciente=" + idPaciente + ", nombre=" + nombre + ", apellido=" + apellido + ", fechaNac="
				+ fechaNac + /*", salary=" + salary +*/ ", carne=" + carne 
				+ ", direccion=" + direccion + ", telefono=" + telefono 
				+ ", movil=" + movil + ", email=" + email 
				+ ", emerNombre=" + emerNombre + ", idEmerParentesco=" + idEmerParentesco + ", emerTelefono=" + emerTelefono + ", emerMovil=" + emerMovil 
				+ "]";
	}
	
	
	

}
