package com.clinica.sgt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class adminController {

	@PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, ModelMap model) {
        
        try {
    		
		       // AGREGAR SERVICIO LUEGO
		
       return "login.html";
 
	   } catch(/*error*/) {
		
		      // AGREGAR ERROR 
		
	   return null;
	}
        
  }

	@PostMapping("/registro")
	public String registro(@RequestParam String email, @RequestParam String nombreCompleto,
			@RequestParam String password, @RequestParam String dni, @RequestParam String telefono,
			@RequestParam String genero, ModelMap model) {

		try {

			// AGREGAR SERVICIO LUEGO

			return null;

		} catch (exception e) {

			// AGREGAR ERROR

			return null;
		}

	}

	@GetMapping("/inicio")
	public String inicio(ModelMap model) {

		return "inicio.html";

	}
}
