package com.clinica.sgt.controllers;



import com.clinica.sgt.servicios.PacienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/")
public class indexController {

	@Autowired
	PacienteServicio pacienteServicio;

	@GetMapping({"/","/login"})
    public String index(ModelMap model) {      
        try {
			return "index.html";
		} catch (Exception e) {
			model.put("error", e.getMessage());
			return "index.html";
		}
		
	}

	@GetMapping("/welcome")
	public String welcome(ModelMap model) {
		
		try {
			String auth = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
			System.out.println(auth);
			if (auth.equalsIgnoreCase("[ROLE_ADMIN]")) {
				return "redirect:/admin/inicio";
			}else if(auth.equalsIgnoreCase("[ROLE_PROFESIONAL]")||auth.equalsIgnoreCase("[PROFESIONAL]")){
				return "redirect:/profesional/inicio";
			}else if(auth.equalsIgnoreCase("[PACIENTE]")||auth.equalsIgnoreCase("[ROLE_PACIENTE]")){
				return "redirect:/paciente/inicio";
			}
			 
		} catch (Exception e) {
			e.printStackTrace();
			model.put("error", e.getMessage());
			return "redirect:/";
		}
		
		return "redirect:/";
	}
	

	// @PostMapping("/login")
    // public String login(@RequestParam String email, @RequestParam String password, ModelMap modelo) {
        
    //     try {
    		
    //    		return "exito.html";
 
	//    } catch(Exception e/*error*/) {
	// 		e.printStackTrace();
	// 	    return "error.html";
	// }
        
//   }



}
