package com.midominio.Evaluable02.app.Service;



import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.midominio.Evaluable02.app.Model.Identity.Usuario;

public interface IUsuarioService {

	Iterable<Usuario> findAll();
	
	void save(Usuario usuario);
	
	Optional<Usuario> findById(Long id);	

	void delete(Long id);
	
	
Page<Usuario> listar(Pageable pageable);
	
	List<Usuario> findAllByOrderByNombreAsc();	
	List<Usuario> findAllByOrderByApellidoAsc();	
	
	List<Usuario> findAllByOrderByNombreDesc();	
	List<Usuario> findAllByOrderByApellidoDesc();
}
