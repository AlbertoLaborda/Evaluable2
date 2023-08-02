package com.midominio.Evaluable02.app.Service;



import java.util.Optional;

import com.midominio.Evaluable02.app.Model.Identity.Usuario;

public interface IUsuarioService {

	Iterable<Usuario> findAll();
	
	void save(Usuario usuario);
	
	Optional<Usuario> findById(Long id);	

	void delete(Long id);
}
