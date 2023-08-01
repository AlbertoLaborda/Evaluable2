package com.midominio.Evaluable02.app.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.midominio.Evaluable02.app.Model.Identity.Usuario;

public interface IUsuarioService {

	@Transactional(readOnly = true)
	List<Usuario> findAll();
	
	@Transactional
	void save(Usuario usuario);
	
	@Transactional(readOnly = true)
	Optional<Usuario> findById(Long id);	
	
	@Transactional
	void delete(Long id);
}
