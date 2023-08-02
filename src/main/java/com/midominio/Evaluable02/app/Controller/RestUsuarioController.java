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

import com.midominio.Evaluable02.app.Model.Identity.Usuario;

import com.midominio.Evaluable02.app.Service.IUsuarioService;

@RestController
@RequestMapping("/rest")
public class RestUsuarioController {

	@Autowired
	IUsuarioService servicioUsuario;

	@GetMapping("/usuarios")
	public Iterable<Usuario> listar(){
		return servicioUsuario.findAll();		
	}
	
	@GetMapping("/usuario/{id}")
	public Optional<Usuario> encontrarId(@PathVariable Long id){
		return servicioUsuario.findById(id);
	}
	
	@DeleteMapping("/usuario/{id}")
	public void eliminar(@PathVariable Long id) {
		if (id > 0)
			servicioUsuario.delete(id);
	}
	
	@PostMapping("/usuarios")
	public Usuario salvarUsuaario(@RequestBody Usuario usuario) {
		if(usuario.getId()== null)
			servicioUsuario.save(usuario);
	    return usuario;
	}
	
	@PutMapping("/usuarios")
	public Usuario actualizarUsuario(@RequestBody Usuario usuario) {
		if(usuario.getId()!= null)
			servicioUsuario.save(usuario);
	    return usuario;
	}
}
