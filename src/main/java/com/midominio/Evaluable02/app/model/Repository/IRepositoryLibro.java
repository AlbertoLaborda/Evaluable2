package com.midominio.Evaluable02.app.model.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.midominio.Evaluable02.app.Model.Identity.Libro;


public interface IRepositoryLibro extends PagingAndSortingRepository<Libro, Long>,
										  CrudRepository<Libro, Long>{

	List<Libro> findAllByOrderByTituloAsc();

	List<Libro> findAllByOrderByAutorAsc();
	
	List<Libro> findAllByOrderByTituloDesc();

	List<Libro> findAllByOrderByAutorDesc();

}
