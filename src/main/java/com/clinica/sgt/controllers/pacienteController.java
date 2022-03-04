package com.clinica.sgt.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.clinica.sgt.entidades.Genero;
import com.clinica.sgt.entidades.Paciente;
import com.clinica.sgt.entidades.Personal;
import com.clinica.sgt.entidades.Turno;
import com.clinica.sgt.entidades.UserType;
import com.clinica.sgt.servicios.PacienteServicio;
import com.clinica.sgt.servicios.TurnoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/paciente")
public class pacienteController {

    @Autowired
    TurnoServicio turnoServicio;


	@Autowired
	BCryptPasswordEncoder bCrypt;

	@Autowired
	PacienteServicio pacienteServicio;

	@GetMapping("/inicio")
	public String inicio(ModelMap model) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
  			userDetails = (UserDetails) principal;
		}
		 Paciente p1 = pacienteServicio.buscarPacientePorUsername(userDetails.getUsername());
		 List<Turno> turnos = new ArrayList<>();
		 turnos = turnoServicio.buscarTurnosPaciente(p1.getDni());
		 model.put("turnos", turnos);
		return "inicioPaciente.html";

	}

    @GetMapping("/turnos/{dni}") 
	public String listarTurnos(ModelMap modelo, @PathVariable String dni){
		List<Turno> turnos = turnoServicio.buscarTurnosPaciente(dni);
		modelo.put("turnos", turnos);
		return "turnos.html";
	}

	@GetMapping("/form-turno") //Formulario para nuevo turno
	public String formTurno(){
		return "form-turno-paciente.html";
	}
	
	@PostMapping("/agregar-turno/{dni}") //El dni del paciente se ingresa sin pedirselo, se utiliza sus datos de registro
	public String agregarTurno(ModelMap modelo, @RequestParam LocalDate dia, @RequestParam LocalTime hora, @PathVariable String dni, @RequestParam Personal profesional) { 

        //Revisar si el parametro ingresado para profesional es entidad o String
        try {
            turnoServicio.agregarTurno(dia, hora, dni, profesional.getDni());
            return "exito.html";
        } catch (Exception e) {
            e.getMessage();
            modelo.put("error", e.getMessage());
            return "error.html";
        }
    }


    @PostMapping("/alta-turno/{id}") //id del turno, funcion para dar de baja o alta
    public String altaTurno(ModelMap modelo, @PathVariable String id, @RequestParam boolean alta) {
        try {
            turnoServicio.modificarAlta(id, alta);
            return "exito.html";
        } catch (Exception e) {
            e.getMessage();
            modelo.put("error", e.getMessage());
            return "error.html";
        }
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String email, @RequestParam String nombreCompleto, @RequestParam String dni,
            @RequestParam String telefono,
            @RequestParam Genero genero, ModelMap modelo) {


        try {

            pacienteServicio.crearPaciente("", dni, email, bCrypt.encode(dni), nombreCompleto, telefono, genero, true, UserType.PACIENTE);
            return "exito.html";

        } catch (Exception e) {

            e.getMessage();
            e.printStackTrace();
            modelo.put("error", e.getMessage());

            return "error.html";
        }

    }
}
