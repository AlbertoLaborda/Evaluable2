package com.midominio.Evaluable02.app.model.Repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.midominio.Evaluable02.app.Model.Identity.Libro;


public interface IRepositoryLibro extends PagingAndSortingRepository<Libro, Long>,
										  CrudRepository<Libro, Long>{

	Page<Libro> findAllByOrderByTituloAsc(Pageable pageable);

	Page<Libro> findAllByOrderByAutorAsc(Pageable pageable);
	
	Page<Libro> findAllByOrderByTituloDesc(Pageable pageable);

	Page<Libro> findAllByOrderByAutorDesc(Pageable pageable);

}
