package com.midominio.Evaluable02.app.Service;


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
	
	Page<Libro> findAllByOrderByTituloAsc(Pageable pageable);	
	Page<Libro> findAllByOrderByAutorAsc(Pageable pageable);	
	
	Page<Libro> findAllByOrderByTituloDesc(Pageable pageable);	
	Page<Libro> findAllByOrderByAutorDesc(Pageable pageable);	
}
