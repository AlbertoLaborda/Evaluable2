package com.midominio.Evaluable02.app.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.midominio.Evaluable02.app.Model.Identity.Usuario;
import com.midominio.Evaluable02.app.model.Repository.IRepositoryUsuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService{
	
	@Autowired
	IRepositoryUsuario repositoryUsuario;

	@Transactional(readOnly = true)
	@Override
	public List<Usuario> findAll() {
		return (List<Usuario>) repositoryUsuario.findAll();
	}

	@Transactional
	@Override
	public void save(Usuario usuario) {
		repositoryUsuario.save(usuario);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Usuario> findById(Long id) {
		return repositoryUsuario.findById(id);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		repositoryUsuario.deleteById(id);
	}

	@Override
	public Page<Usuario> listar(Pageable pageable) {
		// TODO paginado
		return repositoryUsuario.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Usuario> findAllByOrderByNombreAsc(){
		return repositoryUsuario.findAllByOrderByNombreAsc();
	}
	@Transactional(readOnly = true)
	@Override
	public List<Usuario> findAllByOrderByApellidoAsc(){
		return repositoryUsuario.findAllByOrderByApellidoAsc();
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Usuario> findAllByOrderByNombreDesc(){
		return repositoryUsuario.findAllByOrderByNombreDesc();
	}
	@Transactional(readOnly = true)
	@Override
	public List<Usuario> findAllByOrderByApellidoDesc(){
		return repositoryUsuario.findAllByOrderByApellidoDesc();
	}
}
