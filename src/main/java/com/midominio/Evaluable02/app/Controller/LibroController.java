package com.midominio.Evaluable02.app.Controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.midominio.Evaluable02.app.Exception.IdLibroNoExisteException;
import com.midominio.Evaluable02.app.Model.Identity.Libro;
import com.midominio.Evaluable02.app.Service.ILibroService;
import com.midominio.Evaluable02.app.Utils.Paginator.PageRender;

import jakarta.validation.Valid;

@Controller
public class LibroController {
	public boolean ascTitulo = false;
	public boolean ascAutor = false;

	public boolean isAscTitulo() {
		return ascTitulo;
	}

	public void setAscTitulo(boolean ascTitulo) {
		this.ascTitulo = ascTitulo;
	}

	public boolean isAscAutor() {
		return ascAutor;
	}

	public void setAscAutor(boolean ascAutor) {
		this.ascAutor = ascAutor;
	}

	@Autowired
	private ILibroService libroService;

	@GetMapping("/libro/listar-paginado")
	public String sasd(@RequestParam(defaultValue = "0") int page,Model model
			) {
		
		Pageable pageRequest = PageRequest.of(page,5);
		Page<Libro> libros = libroService.listar(pageRequest);
		PageRender<Libro> pageRender = new PageRender<>("/libro/listar-paginado", libros); 
		
		model.addAttribute("ascAutor", ascAutor);
		model.addAttribute("ascTitulo", ascTitulo);
		model.addAttribute("titulo", "Listado de libros");
		model.addAttribute("libros", libros);
		model.addAttribute("page", pageRender);

		return "libro/listar";
	}
	
	@GetMapping("/libro/listar")
	public String s(Model model) {

		model.addAttribute("ascAutor", ascAutor);
		model.addAttribute("ascTitulo", ascTitulo);
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
	public String guardar(@Valid Libro libro, BindingResult result, Model model, RedirectAttributes flash) {

		// la anotación para que se habiliten las validaciones
		if (result.hasErrors()) {
			model.addAttribute("encabezado", "Formulario de libro");
			return "libro/form";
		}

		libroService.save(libro);
		flash.addFlashAttribute("success", "Libro guardado con éxito");
		return "redirect:/libro/listar";
	}

	@GetMapping("/libro/form/{id}")
	public String actualizar(@PathVariable("id") Long id, Map<String, Object> model) throws IdLibroNoExisteException {
		Optional<Libro> libro = null;
		if (!(libroService.findById(id).isPresent())) {
			throw new IdLibroNoExisteException("Libro no encontrado");
		} else {
			libro = libroService.findById(id);
			model.put("libro", libro);
			model.put("encabezado", "Editar libro");
			return "libro/form";
		}
	}

	@GetMapping("/libro/eliminar/{id}")
	public String eliminar(@PathVariable("id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			libroService.delete(id);
			// Cargar el flash messenger
			flash.addFlashAttribute("warning", "Libro borrado con exito");
		}
		return "redirect:/libro/listar";
	}

	@GetMapping("/libro/{campo}/asc")
	public String asc(@RequestParam(defaultValue = "0") int page,
			@RequestParam(name = "asc-titulo") boolean ascTitulo,
			@RequestParam(name = "asc-autor") boolean ascAutor, 
			@PathVariable String campo, Model model) {

		Pageable pageRequest = PageRequest.of(page,5);
		PageRender<Libro> pageRender=null;
		Page<Libro> libros = null;
		
		if (campo.equals("titulo")) {
			if(ascTitulo)
				libros = libroService.findAllByOrderByTituloDesc(pageRequest);
			else
				libros = libroService.findAllByOrderByTituloAsc(pageRequest);
			
			pageRender = new PageRender<>("/libro/titulo/asc?asc-titulo="+ascTitulo+"&asc-autor="+ascAutor, libros); 
		
			model.addAttribute("libros",
					ascTitulo ? libroService.findAllByOrderByTituloDesc(pageRequest) : libroService.findAllByOrderByTituloAsc(pageRequest));
			model.addAttribute("encabezado",
					ascTitulo ? "Listado de Libros por titulo ascendente" : "Listado de Libros por titulo descendente");

			ascTitulo = !ascTitulo;
			
		} else if (campo.equals("autor")) {
			if(ascTitulo)
				libros = libroService.findAllByOrderByAutorDesc(pageRequest);
			else
				libros = libroService.findAllByOrderByAutorAsc(pageRequest);
			
			pageRender = new PageRender<>("/libro/autor/asc?asc-titulo="+ascTitulo+"&asc-autor="+ascAutor, libros); 
			model.addAttribute("libros",
					ascAutor ? libroService.findAllByOrderByAutorDesc(pageRequest) : libroService.findAllByOrderByAutorAsc(pageRequest));
			model.addAttribute("encabezado",
					ascAutor ? "Listado de Libros por autor ascendente" : "Listado de Libros por autor descendente");

			ascAutor = !ascAutor;	
		}
		model.addAttribute("ascAutor", ascAutor);
		model.addAttribute("ascTitulo", ascTitulo);
		model.addAttribute("page", pageRender);
		
		return "libro/listar";

	}
}
