package com.clinica.sgt.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.clinica.sgt.entidades.Genero;
import com.clinica.sgt.entidades.Paciente;
import com.clinica.sgt.entidades.Personal;
import com.clinica.sgt.entidades.Turno;
import com.clinica.sgt.entidades.UserType;
import com.clinica.sgt.servicios.PacienteServicio;
import com.clinica.sgt.servicios.PersonalServicio;
import com.clinica.sgt.servicios.TurnoServicio;

import org.springframework.beans.factory.annotation.Autowired;
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
	PacienteServicio pacienteServicio;

	@Autowired
	PersonalServicio personalServicio;


	@PostMapping("/registro") 	
	public String registro(@RequestParam String email, @RequestParam String nombreCompleto,
			@RequestParam String dni, @RequestParam String telefono,
			@RequestParam Genero genero, ModelMap modelo) {

		try {
			
			LocalTime inicioLaboral = LocalTime.of(8, 00);
			LocalTime finLaboral = LocalTime.of(16, 45);

			personalServicio.crearPersonal(inicioLaboral, finLaboral, dni, email, dni, nombreCompleto, telefono, genero, true, UserType.PROFESIONAL);
			return "exitoRegistro_1.html";

		} catch (Exception e) {

			e.printStackTrace();
            modelo.put("error", e.getMessage());

			return "error.html";
		}

	}

	@GetMapping("/inicio")
	public String inicio(ModelMap model) {

		List<Personal> profesionales = personalServicio.listarPersonal();

		model.put("profesionales", profesionales);
		
		return "inicioAdmin.html";

	}

	
	@GetMapping("/turnos") 
	public String listarTurnos(ModelMap modelo, String dni){
		try {
			List<Turno> turnos = new ArrayList<>();
			turnos = turnoServicio.buscarTurnos(dni);
			System.out.println(turnos+"ultimo");
			modelo.put("turnos", turnos);
			return "turnos.html";
		} catch (Exception e) {
			e.printStackTrace();
			return "error.html";
		}
	}

	@GetMapping("/form-turno") //Formulario para nuevo turno
	public String formTurno(ModelMap modelo){
		List<Personal> profesionales = personalServicio.listarPersonal();

		modelo.put("profesionales", profesionales);
		
		return "form-turno-admin.html";
	}
	
	@PostMapping("/agregar-turno")
	public String agregarTurno(ModelMap modelo, @RequestParam String dia, @RequestParam LocalTime hora, @RequestParam String dniPaciente, @RequestParam String dniProfesional,
	@RequestParam String email, @RequestParam String nombre, @RequestParam String telefono, @RequestParam Genero genero) {
		try{
			
				Paciente p = pacienteServicio.buscarPacientePorDNI(dniPaciente);
				if (p == null) {
					pacienteServicio.crearPaciente("", dniPaciente, email, dniPaciente, nombre, telefono, genero, true, UserType.PACIENTE);
					p = pacienteServicio.buscarPacientePorDNI(dniPaciente);
				}
			
			DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    		LocalDate ld = LocalDate.parse(dia, DATEFORMATTER);
			turnoServicio.agregarTurno(ld, hora, dniPaciente, dniProfesional);
			return "exitoT.html";
		}catch(Exception ex){
			ex.getMessage();
			modelo.put("dia", dia);
			modelo.put("dni", dniPaciente);
			modelo.put("email", email);
			modelo.put("nombre", nombre);
			modelo.put("telefono", telefono);
            modelo.put("error", ex.getMessage());
			
			return "form-turno-admin.html";
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

	@GetMapping("/modificar-alta-turno")
	public String modificarAltaTurno(ModelMap modelo, String id){
		try{
			turnoServicio.modificarAlta(id, false);
			return "redirect:/admin/inicio";
		}catch(Exception e){
			e.getMessage();
           modelo.put("error", e.getMessage());
			return "error.html";
		}
	}

	@GetMapping("/modificar-alta-profesional")
	public String modificarAltaProfesional(ModelMap modelo, String id){
		try{
			personalServicio.bajaPersonal(id);
			return "redirect:/welcome";
		}catch(Exception e){
			e.getMessage();
           modelo.put("error", e.getMessage());
			return "error.html";
		}
	}
	
}
