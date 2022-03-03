package com.clinica.sgt.controllers;

import java.util.ArrayList;
import java.util.List;

import com.clinica.sgt.entidades.Paciente;
import com.clinica.sgt.entidades.Personal;
import com.clinica.sgt.entidades.Turno;
import com.clinica.sgt.servicios.PacienteServicio;
import com.clinica.sgt.servicios.PersonalServicio;
import com.clinica.sgt.servicios.TurnoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/profesional")
public class profesionalController {
    
    @Autowired
    PersonalServicio personalServicio;

    @Autowired
    TurnoServicio turnoServicio;

    @Autowired
    PacienteServicio pacienteServicio;


    @GetMapping("/inicio")
	public String inicio(ModelMap model) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
  			userDetails = (UserDetails) principal;
		}
		 Personal p1 = personalServicio.buscarPersonalPorUsername(userDetails.getUsername());
		 List<Turno> turnos = new ArrayList<>();
		 turnos = turnoServicio.buscarTurnosProfesional(p1.getDni());
		 model.put("turnos", turnos);
		return "inicioAdmin.html";

	}

    @GetMapping("/info-paciente/{dni}")
    public String infoPaciente(@PathVariable String dni, ModelMap modelo){
        Paciente paciente = pacienteServicio.buscarPacientePorDNI(dni);
        modelo.put("paciente", paciente);
        return "info-paciente.html";
    }
}
