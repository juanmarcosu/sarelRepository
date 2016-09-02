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
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="paciente")
public class Paciente {
	/*
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int idPaciente;
	
	@Column(name = "id_tipo_paciente", unique=true, nullable = true)
	private Integer idTipoPaciente;
	
	@Column(name = "id_dependencia", unique=true, nullable = true)
	private Integer idDependencia;
	
	@Size(min=3, max=100)
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	@Column(name = "fecha_nacimiento", nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate fechaNac;
	
	@Column(name = "sexo", nullable = false)
	private Integer sexo;
	
	@Column(name = "carne", unique=true, nullable = true)
	private Integer carne;
	
	@Column(name = "dpi", unique=true, nullable = true)
	private Integer dpi;
	
	@Size(min=3, max=50)
	@Column(name = "telefono", nullable = false)
	private String telefono;
	
	@Size(min=3, max=80)
	@Column(name = "correo", nullable = false)
	private String email;
	
	@NotNull
	@Digits(integer=8, fraction=2)
	@Column(name = "talla", nullable = false)
	private BigDecimal talla;
	
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

	public int getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}

	public Integer getCarne() {
		return carne;
	}

	public void setCarne(Integer carne) {
		this.carne = carne;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getSexo() {
		return sexo;
	}

	public void setSexo(Integer sexo) {
		this.sexo = sexo;
	}

	public Integer getIdTipoPaciente() {
		return idTipoPaciente;
	}

	public void setIdTipoPaciente(Integer idTipoPaciente) {
		this.idTipoPaciente = idTipoPaciente;
	}

	public Integer getIdDependencia() {
		return idDependencia;
	}

	public void setIdDependencia(Integer idDependencia) {
		this.idDependencia = idDependencia;
	}

	public Integer getDpi() {
		return dpi;
	}

	public void setDpi(Integer dpi) {
		this.dpi = dpi;
	}

	public BigDecimal getTalla() {
		return talla;
	}

	public void setTalla(BigDecimal talla) {
		this.talla = talla;
	}

	@Override
	public String toString() {
		return "Paciente [idPaciente=" + idPaciente + ", nombre=" + nombre + ", fechaNac="
				+ fechaNac + ", carne=" + carne 
				+ ", telefono=" + telefono 
				+ ", email=" + email 
				+ ", sexo=" + sexo  
				+ "]";
	}*/

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idpaciente")
	private int idPaciente;

	@Size(min=3, max=100)
	@Column(name = "nombre", nullable = false)
	private String nombre;

	@Size(min=3, max=100)
	@Column(name = "apellido", nullable = false)
	private String apellido;
	
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	@Column(name = "fecha_nac", nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate fechaNac;

	//@NotNull
	//@Digits(integer=8, fraction=2)
	//@Column(name = "SALARY", nullable = false)
	//private BigDecimal salary;
	
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
	
	@Column(name = "tipo_sangreidtipo_sangre", nullable = true)
	private Integer tipoSangreIdTipoSangre;
	
	@Column(name = "estado_civilidestado_civil", nullable = true)
	private Integer estadoCivilidEstadoCivil;
	
	@Size(min=3, max=20)
	@Column(name = "usuario", nullable = true)
	private String usuario;
	
	@Column(name = "sexo", nullable = false)
	private Integer sexo;
	
	@Size(min=3, max=150)
	@Column(name = "titulo_secundaria", nullable = true)
	private String tituloSecundaria;
	
	@Size(min=3, max=100)
	@Column(name = "crecio_en", nullable = true)
	private String crecioEn;
	
	@Column(name = "estado", nullable = false)
	private Integer estado;
	
	@Column(name = "iddepartamento", nullable = true)
	private Integer idDepartamento;
	
	@Column(name = "idnacionalidad", nullable = true)
	private Integer idNacionalidad;
	
	@Column(name = "examen_linea", nullable = false)
	private Integer examenLinea;
	
	@Column(name = "idunidad_academica", nullable = true)
	private Integer idUnidadAcademica;
	
	@Size(min=3, max=45)
	@Column(name = "fecha_exam", nullable = true)
	private String fechaExam;
	
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

	public int getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(int idPaciente) {
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

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public LocalDate getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
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

	public Integer getTipoSangreIdTipoSangre() {
		return tipoSangreIdTipoSangre;
	}

	public void setTipoSangreIdTipoSangre(Integer tipoSangreIdTipoSangre) {
		this.tipoSangreIdTipoSangre = tipoSangreIdTipoSangre;
	}

	public Integer getEstadoCivilidEstadoCivil() {
		return estadoCivilidEstadoCivil;
	}

	public void setEstadoCivilidEstadoCivil(Integer estadoCivilidEstadoCivil) {
		this.estadoCivilidEstadoCivil = estadoCivilidEstadoCivil;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Integer getSexo() {
		return sexo;
	}

	public void setSexo(Integer sexo) {
		this.sexo = sexo;
	}

	public String getTituloSecundaria() {
		return tituloSecundaria;
	}

	public void setTituloSecundaria(String tituloSecundaria) {
		this.tituloSecundaria = tituloSecundaria;
	}

	public String getCrecioEn() {
		return crecioEn;
	}

	public void setCrecioEn(String crecioEn) {
		this.crecioEn = crecioEn;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Integer getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(Integer idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public Integer getIdNacionalidad() {
		return idNacionalidad;
	}

	public void setIdNacionalidad(Integer idNacionalidad) {
		this.idNacionalidad = idNacionalidad;
	}

	public Integer getExamenLinea() {
		return examenLinea;
	}

	public void setExamenLinea(Integer examenLinea) {
		this.examenLinea = examenLinea;
	}

	public Integer getIdUnidadAcademica() {
		return idUnidadAcademica;
	}

	public void setIdUnidadAcademica(Integer idUnidadAcademica) {
		this.idUnidadAcademica = idUnidadAcademica;
	}

	public String getFechaExam() {
		return fechaExam;
	}

	public void setFechaExam(String fechaExam) {
		this.fechaExam = fechaExam;
	}

	@Override
	public String toString() {
		return "Paciente [idPaciente=" + idPaciente + ", nombre=" + nombre + ", apellido=" + apellido + ", fechaNac="
				+ fechaNac + 
				//", salary=" + salary + 
				", carne=" + carne 
				+ ", direccion=" + direccion + ", telefono=" + telefono 
				+ ", movil=" + movil + ", email=" + email 
				+ ", emerNombre=" + emerNombre + ", idEmerParentesco=" + idEmerParentesco + ", emerTelefono=" + emerTelefono + ", emerMovil=" + emerMovil 
				+ ", tipoSangreIdTipoSangre=" + tipoSangreIdTipoSangre + ", estadoCivilidEstadoCivil=" + estadoCivilidEstadoCivil + ", usuario=" + usuario + ", sexo=" + sexo 
				+ ", tituloSecundaria=" + tituloSecundaria + ", crecioEn=" + crecioEn + ", estado=" + estado + ", idDepartamento=" + idDepartamento + ", idNacionalidad=" + idNacionalidad 
				+ ", examenLinea=" + examenLinea + ", idUnidadAcademica=" + idUnidadAcademica + ", fechaExam=" + fechaExam 
				+ "]";
	}	
}
