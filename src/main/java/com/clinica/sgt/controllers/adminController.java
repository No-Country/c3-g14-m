package com.clinica.sgt.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.clinica.sgt.entidades.Genero;
import com.clinica.sgt.entidades.Personal;
import com.clinica.sgt.entidades.Turno;
import com.clinica.sgt.entidades.UserType;
import com.clinica.sgt.servicios.PersonalServicio;
import com.clinica.sgt.servicios.TurnoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/admin")
public class adminController {


	@Autowired
	TurnoServicio turnoServicio;

	@Autowired
	PersonalServicio personalServicio;


	@PostMapping("/registro") 	
	public String registro(@RequestParam LocalTime inicioLaboral, @RequestParam LocalTime finLaboral, @RequestParam String email, @RequestParam String nombreCompleto,
			@RequestParam String password, @RequestParam String dni, @RequestParam String telefono,
			@RequestParam Genero genero, @RequestParam UserType userType, ModelMap modelo) {

		try {

			personalServicio.crearPersonal(inicioLaboral, finLaboral, dni, email, password, nombreCompleto, telefono, genero, true, userType);
			return "exito.html";

		} catch (Exception e) {

			e.getMessage();
           modelo.put("error", e.getMessage());

			return "error.html";
		}

	}

	@GetMapping("/inicio")
	public String inicio(ModelMap model) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
  			userDetails = (UserDetails) principal;
		}

		 Personal p1 = personalServicio.buscarPersonalPorUsername(userDetails.getUsername());
		 List<Turno> turnos = turnoServicio.buscarTurnosProfesional(p1.getDni());
		 model.put("turnos", turnos);
		return "inicioAdmin.html";

	}

	// @GetMapping("/turnos/{dni}") 
	// public String listarTurnos(ModelMap modelo, @PathVariable String dni){
	// 	List<Turno> turnos = turnoServicio.buscarTurnosProfesional(dni);
	// 	modelo.put("turnos", turnos);
	// 	return "turnos.html";
	// }

	@GetMapping("/form-turno") //Formulario para nuevo turno
	public String formTurno(){
		return "form-turno.html";
	}
	
	@PostMapping("/agregar-turno")
	public String agregarTurno(ModelMap modelo, @RequestParam LocalDate dia, @RequestParam LocalTime hora, @RequestParam String dniPaciente, @RequestParam String dniProfesional) {
		try{
			turnoServicio.agregarTurno(dia, hora, dniPaciente, dniProfesional);
			return "exito.html";
		}catch(Exception e){
			e.getMessage();
           modelo.put("error", e.getMessage());
			return "error.html";
		}
	}

	//Modificar turno
	@GetMapping("/mod-turno/{id}") //id del turno
	public String modTurno(ModelMap modelo, @PathVariable String id){
		modelo.put("turno", turnoServicio.buscarPorID(id));
		return "form-turno.html";
	}

	@PostMapping("/modificar-turno")
	public String modificarTurno(ModelMap modelo,@PathVariable String id, @RequestParam LocalDate dia, @RequestParam LocalTime hora, @RequestParam String dniPaciente, @RequestParam String dniProfesional, @RequestParam boolean alta ){
		try{
			turnoServicio.modificarTurno(dia, hora, dniPaciente, dniProfesional, alta, id);
			return "exito.html";
		}catch(Exception e){
			e.getMessage();
           modelo.put("error", e.getMessage());
			return "error.html";
		}
	}

	@PostMapping("/modificar-alta-turno/{id}")
	public String modificarAltaTurno(ModelMap modelo,@PathVariable String id, @RequestParam boolean alta ){
		try{
			turnoServicio.modificarAlta(id, alta);
			return "exito.html";
		}catch(Exception e){
			e.getMessage();
           modelo.put("error", e.getMessage());
			return "error.html";
		}
	}
	
}

