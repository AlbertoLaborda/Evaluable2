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

import com.midominio.Evaluable02.app.Exception.IdUsuarioNoExisteException;
import com.midominio.Evaluable02.app.Model.Identity.Usuario;
import com.midominio.Evaluable02.app.Service.IUsuarioService;

import jakarta.validation.Valid;

@Controller
public class UsuarioController {

	@Autowired
	IUsuarioService servicioUsuario;
	
	@GetMapping("/usuario/listar")
	public String s(Model model) {

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
	public String guardar(@Valid Usuario usuario, BindingResult result, Model model) {  
		
		// la anotación para que se habiliten las validaciones
		if (result.hasErrors()) {
			model.addAttribute("encabezado", "Formulario de usuario");
			return "usuario/form"; 
		}
		servicioUsuario.save(usuario);
		return "redirect:/usuario/listar";
	}
	
	@GetMapping("/usuario/form/{id}")
	public String actualizar(@PathVariable("id") Long id,Map<String,Object>model) throws IdUsuarioNoExisteException{
		Optional<Usuario> usuario = null;
		if(!(servicioUsuario.findById(id).isPresent())) {
			throw new IdUsuarioNoExisteException("Usuario no encontrado");
		}else {
			usuario = servicioUsuario.findById(id);
			model.put("usuario", usuario);
			model.put("encabezado", "Editar usuario"); 	
			return "usuario/form";
		}
		}	
	
	@GetMapping("/usuario/eliminar/{id}")
	public String eliminar(@PathVariable("id") Long id) {
		if (id > 0)
			servicioUsuario.delete(id);
		return "redirect:/usuario/listar";
	}
}
