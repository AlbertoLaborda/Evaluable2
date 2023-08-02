package com.midominio.Evaluable02.app.Service;


import java.util.Optional;

import com.midominio.Evaluable02.app.Model.Identity.Libro;


public interface ILibroService {

	Iterable<Libro> findAll();
	
	void save(Libro libro);

	Optional<Libro> findById(Long id);	
	
	void delete(Long id);
}
