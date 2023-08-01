package com.midominio.Evaluable02.app.Model.Identity;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Size(min = 2, max = 20) 
	private String nombre;
	
	@NotEmpty
	@Size(min = 2, max = 20)
	private String apellido;
	
	@NotEmpty//(message ="Escribe un email válido")
	@Email // formato de email
	private String email;
	
	@Column(name = "fecha_libro_prestado")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull // equivalente a NotEmpty pero para los que no son String 
	private LocalDate fechaLibroPrestado;
	
	@Column(name = "cantidad_ejemplares_prestados")
	@NotNull
	private int cantidadEjemplaresPrestados;

	@PrePersist
	// Antes de persistir en base de datos los objetos añaden la fecha
	public void prePersist() {
		fechaLibroPrestado = fechaLibroPrestado != null ? fechaLibroPrestado: LocalDate.now();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getFechaLibroPrestado() {
		return fechaLibroPrestado;
	}

	public void setFechaLibroPrestado(LocalDate fechaLibroPrestado) {
		this.fechaLibroPrestado = fechaLibroPrestado;
	}

	public int getCantidadEjemplaresPrestados() {
		return cantidadEjemplaresPrestados;
	}

	public void setCantidadEjemplaresPrestados(int cantidadEjemplaresPrestados) {
		this.cantidadEjemplaresPrestados = cantidadEjemplaresPrestados;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Usuario() {
	}

	
}
