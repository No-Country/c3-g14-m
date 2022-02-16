package com.clinica.sgt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/registro")
public class adminController {
	
	
	@GetMapping
    

    @PostMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap model) {
        if (error != null) {
            model.put("error", "Email o contraseña incorrectos.");
        }
        
        return "login.html";
    }

    
    @GetMapping("/inicio")
    public String inicio(ModelMap model) {
		
    	return "index.html";
        
    }
}
