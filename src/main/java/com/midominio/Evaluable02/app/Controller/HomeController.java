package com.midominio.Evaluable02.app.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

	@GetMapping({"/","/home","/home/",""})
	public String as(Model model) {
		model.addAttribute("titulo", "La Biblioteca de Core Network");
		model.addAttribute("enlace", "Enlace a Libros y a Usuarios");

		return "home";		
	}
	
	@GetMapping({"/division"})
	public String aas(Model model) {
		
		System.out.println(1/0);

		return "home";		
	}
}
