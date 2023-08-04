package com.midominio.Evaluable02.app.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.midominio.Evaluable02.app.Model.Identity.Libro;


public interface ILibroService {

	Iterable<Libro> findAll();
	
	void save(Libro libro);

	Optional<Libro> findById(Long id);	
	
	void delete(Long id);
	//Paginado
	Page<Libro> listar(Pageable pageable);
	
	List<Libro> findAllByOrderByTituloAsc();	
	List<Libro> findAllByOrderByAutorAsc();	
	
	List<Libro> findAllByOrderByTituloDesc();	
	List<Libro> findAllByOrderByAutorDesc();	
}
