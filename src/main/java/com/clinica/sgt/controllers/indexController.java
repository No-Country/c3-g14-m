package com.clinica.sgt.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class indexController {

	@GetMapping("/")
    public String index() {      
        return "index.html";
   
	}
	
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
}
