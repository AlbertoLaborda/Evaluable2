package com.midominio.Evaluable02.app.model.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.midominio.Evaluable02.app.Model.Identity.Usuario;


public interface IRepositoryUsuario extends PagingAndSortingRepository<Usuario, Long>,
											CrudRepository<Usuario, Long>{

	List<Usuario> findAllByOrderByNombreAsc();

	List<Usuario> findAllByOrderByApellidoAsc();
	
	List<Usuario> findAllByOrderByNombreDesc();

	List<Usuario> findAllByOrderByApellidoDesc();
	
}
