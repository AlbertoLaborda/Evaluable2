package com.midominio.Evaluable02.app.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
import com.midominio.Evaluable02.app.Model.Identity.Libro;


public interface ILibroService {
	@Transactional(readOnly = true)
	List<Libro> findAll();
	
	@Transactional
	void save(Libro libro);
	
	@Transactional(readOnly = true)
	Optional<Libro> findById(Long id);	
	
	@Transactional
	void delete(Long id);
}
