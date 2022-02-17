package com.clinica.sgt.controllers;

import com.clinica.sgt.entidades.Genero;
import com.clinica.sgt.entidades.UserType;
import com.clinica.sgt.entidades.Usuario;
import com.clinica.sgt.servicios.UsuarioServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class adminController {

	@Autowired
	UsuarioServicio usuarioServicio;

	// @PostMapping("/login")
    // public String login(@RequestParam String email, @RequestParam String password, ModelMap model) {
        
    //     try {
    		
	// 	    Usuario u1 = usuarioServicio.loguearUsuario(email, password);
		
    //    		return "login.html";
 
	//    } catch(Exception e/*error*/) {
		
	// 	      // AGREGAR ERROR 
		
	//    return null;
	// }
        
//   }

	@PostMapping("/registro")
	public String registro(@RequestParam String email, @RequestParam String nombreCompleto,
			@RequestParam String password, @RequestParam String dni, @RequestParam String telefono,
			@RequestParam Genero genero, ModelMap model) {

		try {

			usuarioServicio.crearUsuario(dni, email, password, nombreCompleto, telefono, genero, true, UserType.GUEST);
			return "exito.html";

		} catch (Exception e) {

			e.getMessage();

			return "error.html";
		}

	}

	@GetMapping("/inicio")
	public String inicio(ModelMap model) {

		return "inicio.html";

	}
}
