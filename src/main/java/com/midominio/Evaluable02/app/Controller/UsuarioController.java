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

import com.midominio.Evaluable02.app.Exception.IdUsuarioNoExisteException;
import com.midominio.Evaluable02.app.Model.Identity.Usuario;
import com.midominio.Evaluable02.app.Service.IUsuarioService;
import com.midominio.Evaluable02.app.Utils.Paginator.PageRender;

import jakarta.validation.Valid;

@Controller
public class UsuarioController {
	public boolean ascNombre = false;
	public boolean ascApellido = false;

	public boolean isAscNombre() {
		return ascNombre;
	}

	public void setAscNombre(boolean ascNombre) {
		this.ascNombre = ascNombre;
	}

	public boolean isAscApellido() {
		return ascApellido;
	}

	public void setAscApellido(boolean ascApellido) {
		this.ascApellido = ascApellido;
	}

	@Autowired
	IUsuarioService servicioUsuario;

	@GetMapping("/usuario/listar-paginado")
	public String sasd(@RequestParam(defaultValue = "0") int page, Model model) {

		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Usuario> usuarios = servicioUsuario.listar(pageRequest);
		PageRender<Usuario> pageRender = new PageRender<>("/usuario/listar-paginado", usuarios);

		model.addAttribute("ascApellido", ascApellido);
		model.addAttribute("ascNombre", ascNombre);
		model.addAttribute("titulo", "Listado de usuarios");
		model.addAttribute("usuarios", usuarios);
		model.addAttribute("page", pageRender);

		return "usuario/listar";
	}

	@GetMapping("/usuario/listar")
	public String s(Model model) {

		model.addAttribute("ascApellido", ascApellido);
		model.addAttribute("ascNombre", ascNombre);
		model.addAttribute("encabezado", "Listado de Usuarios");
		model.addAttribute("usuarios", servicioUsuario.findAll());

		return "usuario/listar";
	}

	@GetMapping("/usuario/form")
	public String crear(Map<String, Object> model) {

		model.put("encabezado", "Formulario de usuario");
		model.put("usuario", new Usuario());
		return "usuario/form";
	}

	@PostMapping("/usuario/form")
	// Aquí cliente está ya poblado con los datos del formulario
	public String guardar(@Valid Usuario usuario, BindingResult result, Model model, RedirectAttributes flash) {

		// la anotación para que se habiliten las validaciones
		if (result.hasErrors()) {
			model.addAttribute("encabezado", "Formulario de usuario");
			return "usuario/form";
		}
		servicioUsuario.save(usuario);
		flash.addFlashAttribute("success", "Usuario guardado con éxito");
		return "redirect:/usuario/listar";
	}

	@GetMapping("/usuario/form/{id}")
	public String actualizar(@PathVariable("id") Long id, Map<String, Object> model) throws IdUsuarioNoExisteException {
		Optional<Usuario> usuario = null;
		if (!(servicioUsuario.findById(id).isPresent())) {
			throw new IdUsuarioNoExisteException("Usuario no encontrado");
		} else {
			usuario = servicioUsuario.findById(id);
			model.put("usuario", usuario);
			model.put("encabezado", "Editar usuario");
			return "usuario/form";
		}
	}

	@GetMapping("/usuario/eliminar/{id}")
	public String eliminar(@PathVariable("id") Long id, RedirectAttributes flash) {
		if (id > 0)
			servicioUsuario.delete(id);
		// Cargar el flash messenger
		flash.addFlashAttribute("warning", "Usuario borrado con exito");
		return "redirect:/usuario/listar";
	}

	@GetMapping("/usuario/{campo}/asc")
	public String asc(@RequestParam(defaultValue = "0") int page, @RequestParam(name = "asc-nombre") boolean ascNombre,
			@RequestParam(name = "asc-apellido") boolean ascApellido, @PathVariable String campo, Model model) {

		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Usuario> usuarios = servicioUsuario.listar(pageRequest);
		PageRender<Usuario> pageRender = new PageRender<>("/usuario/listar-paginado", usuarios);

		if (campo.equals("nombre")) {
			model.addAttribute("usuarios", ascNombre ? servicioUsuario.findAllByOrderByNombreDesc()
					: servicioUsuario.findAllByOrderByNombreAsc());
			model.addAttribute("encabezado", ascNombre ? "Listado de Usuarios por nombre ascendente"
					: "Listado de Usuarios por nombre descendente");

			ascNombre = !ascNombre;
		} else if (campo.equals("apellido")) {
			model.addAttribute("usuarios", ascApellido ? servicioUsuario.findAllByOrderByApellidoDesc()
					: servicioUsuario.findAllByOrderByApellidoAsc());
			model.addAttribute("encabezado", ascApellido ? "Listado de Usuarios por apellido ascendente"
					: "Listado de Usuarios por apellido descendente");

			ascApellido = !ascApellido;
		}
		model.addAttribute("ascApellido", ascApellido);
		model.addAttribute("ascNombre", ascNombre);
		model.addAttribute("page", pageRender);

		return "usuario/listar";

	}

}
