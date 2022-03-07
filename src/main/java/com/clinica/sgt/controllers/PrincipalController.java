/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.sgt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Cristian
 */
@Controller
public class PrincipalController {
    @RequestMapping("/")
	  public String inicio() {
	    return "redirect:/";
	  }

	 @RequestMapping("/admin")
	  public String admin() {
	    return "redirect:/admin/inicio";
	  }
	 @RequestMapping({"/paciente","login"})
	  public String paciente() {
	    return "redirect:/paciente/inicio";
	  }
	 @RequestMapping("/profesional")
	  public String profesional() {
	    return "redirect: /profesional/inicio";
	  }
//	 @RequestMapping("/login")
//	  public String login() {
//	    return "login";
//	  }
}
