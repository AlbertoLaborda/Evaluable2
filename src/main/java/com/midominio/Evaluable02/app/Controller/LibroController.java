package com.midominio.Evaluable02.app.Controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.midominio.Evaluable02.app.Exception.IdLibroNoExisteException;
import com.midominio.Evaluable02.app.Model.Identity.Libro;
import com.midominio.Evaluable02.app.Service.ILibroService;

import jakarta.validation.Valid;

@Controller
public class LibroController {

	@Autowired
	private ILibroService libroService;
	
	@GetMapping("/libro/listar")
	public String s(Model model) {

		model.addAttribute("encabezado", "Listado de Libros");
		model.addAttribute("libros", libroService.findAll());

		return "libro/listar";

	}
	
	@GetMapping("/libro/form")
	public String crear(Map<String, Object> model) {
		
		model.put("encabezado", "Formulario de libro");
		model.put("libro", new Libro());
		return "libro/form";
	}

	@PostMapping("/libro/form")
	// Aquí cliente está ya poblado con los datos del formulario
	public String guardar(@Valid Libro libro, BindingResult result, Model model) {  
		
		// la anotación para que se habiliten las validaciones
		if (result.hasErrors()) {
			model.addAttribute("encabezado", "Formulario de libro");
			return "libro/form"; 
		}
		libroService.save(libro);
		return "redirect:/libro/listar";
	}
		
	@GetMapping("/libro/form/{id}")
	public String actualizar(@PathVariable("id") Long id,Map<String,Object>model) throws IdLibroNoExisteException{
		Optional<Libro> libro = null;
		if(!(libroService.findById(id).isPresent())) {
			throw new IdLibroNoExisteException("Libro no encontrado");
		}else {
			libro = libroService.findById(id);
			model.put("libro", libro);
			model.put("encabezado", "Editar libro"); 	
			return "libro/form";
		}
		}		
	
	@GetMapping("/libro/eliminar/{id}")
	public String eliminar(@PathVariable("id") Long id) {
		if (id > 0)
			libroService.delete(id);
		return "redirect:/libro/listar";
	}
}
