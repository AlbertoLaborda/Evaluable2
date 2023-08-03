package com.midominio.Evaluable02.app.Exception;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



	@ControllerAdvice
	public class ErrorException {
		
		@Value("Ha surgido un error")
		private String titulo;
		
		@ExceptionHandler(NullPointerException.class)
		public String templateInputError(NullPointerException ex, Model model) {
			model.addAttribute("error", "Error null pointer");
			model.addAttribute("message", ex.getMessage());
			model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			model.addAttribute("timestamp", LocalDate.now());
			return "error/internal-error";
		}	
		
		@ExceptionHandler(IdLibroNoExisteException.class)
		public String cocheNotFoundException(IdLibroNoExisteException ex, Model model) {
			model.addAttribute("titulo", titulo);
			model.addAttribute("error", "El Libro no existe en la lista");
			model.addAttribute("message", ex.getMessage());
			model.addAttribute("timestamp", LocalDate.now());		
			return "error/libro-error";
		}
		
		@ExceptionHandler(IdUsuarioNoExisteException.class)
		public String cocheNotFoundException(IdUsuarioNoExisteException ex, Model model) {
			model.addAttribute("titulo", titulo);
			model.addAttribute("error", "El Usuario no existe en la lista");
			model.addAttribute("message", ex.getMessage());
			model.addAttribute("timestamp", LocalDate.now());		
			return "error/usuario-error";
		}
	}
