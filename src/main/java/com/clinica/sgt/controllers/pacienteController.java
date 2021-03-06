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
@RequestMapping("/paciente")
public class pacienteController {

    @Autowired
    TurnoServicio turnoServicio;

    @Autowired
    PacienteServicio pacienteServicio;

    @Autowired
    PersonalServicio personalServicio;

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
    public String listarTurnos(ModelMap modelo, @PathVariable String dni) {
        List<Turno> turnos = turnoServicio.buscarTurnosPaciente(dni);
        modelo.put("turnos", turnos);
        return "turnos.html";
    }

    @GetMapping("/form-turno") // Formulario para nuevo turno
    public String formTurno(ModelMap modelo) { // (@PathVariable String id)
        
        List<Personal> profesionales = personalServicio.listarPersonal();
        modelo.put("listaProfesionales", profesionales);

        // modelo.put("horaEntrada", personalServicio.horaEntrada(id));
        // modelo.put("horaSalida", personalServicio.horaSalida(id));

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = null;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
        }
        Paciente paciente = pacienteServicio.buscarPacientePorUsername(userDetails.getUsername());

        modelo.put("paciente", paciente);
        return "form-turno-paciente.html";
    }

    @PostMapping("/agregar-turno")
    public String agregarTurno(ModelMap modelo, @RequestParam String dia, @RequestParam LocalTime hora,
            @RequestParam String dni, @RequestParam String dniProfesional) {

        
        try {
            DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    		LocalDate ld = LocalDate.parse(dia, DATEFORMATTER);
            turnoServicio.agregarTurno(ld, hora, dni, dniProfesional);
            return "exitoT.html";
        } catch (Exception e) {
            e.getMessage();
            modelo.put("error", e.getMessage());
            return "error.html";
        }
    }

    @GetMapping("/alta-turno") // id del turno, funcion para dar de baja o alta
    public String altaTurno(ModelMap modelo, String id) {
        try {
            turnoServicio.modificarAlta(id, false);
            return "redirect:/welcome";
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

            pacienteServicio.crearPaciente("", dni, email, dni, nombreCompleto, telefono, genero, true,
                    UserType.PACIENTE);
            return "exitoRegistro.html";

        } catch (Exception e) {

            e.getMessage();
            modelo.put("error", e.getMessage());

            return "error.html";
        }

    }
}
