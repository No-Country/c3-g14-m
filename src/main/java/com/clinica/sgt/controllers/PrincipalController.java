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
    @RequestMapping({"/","login"})
	  public String inicio() {
	    return "redirect: /";
	  }

	 @RequestMapping("/admin")
	  public String privado() {
	    return "redirect: /admin/inicio";
	  }
	 @RequestMapping("/paciente")
	  public String loginpub() {
	    return "redirect: /paciente/inicio";
	  }
	 @RequestMapping("/profesional")
	  public String admin() {
	    return "redirect: /profesional/inicio";
	  }
//	 @RequestMapping("/login")
//	  public String login() {
//	    return "login";
//	  }
}
