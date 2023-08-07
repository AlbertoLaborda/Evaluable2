package com.midominio.Evaluable02.app.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.midominio.Evaluable02.app.Model.Identity.Libro;
import com.midominio.Evaluable02.app.model.Repository.IRepositoryLibro;

@Service
public class LibroServiceImpl implements ILibroService{

	@Autowired
	IRepositoryLibro libroRepository;
	
	@Override
	public Page<Libro> listar(Pageable pageable) {
		// TODO paginado
		return libroRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Libro> findAll() {
		return (List<Libro>) libroRepository.findAll();
	}
	@Transactional
	@Override
	public void save(Libro libro) {
		libroRepository.save(libro);		
	}
	@Transactional(readOnly = true)
	@Override
	public Optional<Libro> findById(Long id) {
		return libroRepository.findById(id);
	}
	@Transactional
	@Override
	public void delete(Long id) {
		libroRepository.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	@Override
	public Page<Libro> findAllByOrderByTituloAsc(Pageable pageable){
		return libroRepository.findAllByOrderByTituloAsc(pageable);
	}
	@Transactional(readOnly = true)
	@Override
	public Page<Libro> findAllByOrderByAutorAsc(Pageable pageable){
		return libroRepository.findAllByOrderByAutorAsc(pageable);
	}
	
	@Transactional(readOnly = true)
	@Override
	public Page<Libro> findAllByOrderByTituloDesc(Pageable pageable){
		return libroRepository.findAllByOrderByTituloDesc(pageable);
	}
	@Transactional(readOnly = true)
	@Override
	public Page<Libro> findAllByOrderByAutorDesc(Pageable pageable){
		return libroRepository.findAllByOrderByAutorDesc(pageable);
	}

}
