package com.midominio.Evaluable02.app.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.midominio.Evaluable02.app.Model.Identity.Libro;
import com.midominio.Evaluable02.app.Service.ILibroService;

@RestController
@RequestMapping("/rest")
public class RestLibroController {
	
	@Autowired
	ILibroService servicioLibro;

	@GetMapping("/libros")
	public Iterable<Libro> m(){
		return servicioLibro.findAll();		
	}
	
	@GetMapping("/libro/{id}")
	public Optional<Libro> ma(@PathVariable Long id){
		return servicioLibro.findById(id);
	}
	
	@DeleteMapping("/libro/{id}")
	public void eliminar(@PathVariable Long id) {
		if (id > 0)
			servicioLibro.delete(id);
	}
	
	@PostMapping("/libros")
	public Libro salvarLibro(@RequestBody Libro libro) {
		if(libro.getId()== null)
			servicioLibro.save(libro);
	    return libro;
	}
	
	@PutMapping("/libros")
	public Libro actualizarLibro(@RequestBody Libro libro) {
		if(libro.getId()!= null)
			servicioLibro.save(libro);
	    return libro;
	}
}
