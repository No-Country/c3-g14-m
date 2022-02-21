package com.clinica.sgt.servicios;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import com.clinica.sgt.entidades.Turno;
import com.clinica.sgt.repositorios.AdminRepositorio;
import com.clinica.sgt.repositorios.TurnoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TurnoServicio {
    
    @Autowired 
    TurnoRepositorio turnoRepo;

    @Autowired
    AdminRepositorio adminRepo;

    @Autowired
    PacienteServicio pacienteServicio;

    public void validarDatos(LocalDate dia, LocalTime hora, String dniPaciente, String dniProfesional) throws Exception{

        if(dia == null || hora == null){
            throw new Exception("Los datos dia y/o hora son invalidos");
        }
        if(pacienteServicio.buscarPacientePorDNI(dniPaciente) == null){
            throw new Exception("El paciente no se encuentra en la base de datos, revise los datos ingresados");
        }
        if(adminRepo.buscarPorDNI(dniProfesional) == null){
            throw new Exception("El profesional no se encuentra en la base de datos, revise los datos ingresados");
        }

    }

    public void validarFechaDisponible(LocalDate dia, LocalTime hora, String dniProfesional) throws Exception{
        if(turnoRepo.buscarPorHoraYDia(hora, dia, dniProfesional) != null){
            throw new Exception("La hora en el dia especificado esta ocupado");
        }
    }

    // Creacion
    @Transactional
    public void agregarTurno(LocalDate dia, LocalTime hora, String dniPaciente, String dniProfesional) throws Exception{

        validarDatos(dia, hora, dniPaciente, dniProfesional);
        validarFechaDisponible(dia, hora, dniProfesional);

        Turno turno = new Turno();

        turno.setDia(dia);
        turno.setHora(hora);
        turno.setPaciente(pacienteServicio.buscarPacientePorDNI(dniPaciente));
        turno.setProfesional(adminRepo.buscarPorDNI(dniProfesional));
        turno.setAlta(true);

        turnoRepo.save(turno);


    }

    //Modificacion
    @Transactional
    public void modificarTurno(LocalDate dia, LocalTime hora, String dniPaciente, String dniProfesional, boolean alta, String id) throws Exception{
        
        Turno turno = turnoRepo.buscarPorID(id);

        validarDatos(dia, hora, dniPaciente, dniProfesional);

        turno.setDia(dia);
        turno.setHora(hora);
        turno.setPaciente(pacienteServicio.buscarPacientePorDNI(dniPaciente));
        turno.setProfesional(adminRepo.buscarPorDNI(dniProfesional));
        turno.setAlta(true);

        turnoRepo.save(turno);

    }

    //Baja o Alta
    @Transactional
    public void modificarAlta(String id, boolean alta){

        Turno turno = turnoRepo.buscarPorID(id);

        turno.setAlta(alta);

        turnoRepo.save(turno);

    }

    //Busqueda
    public Turno buscarPorID(String idTurno){
        return turnoRepo.buscarPorID(idTurno);
    }
    public ArrayList<Turno> buscarPorDia(LocalDate dia, String dniProfesional){
        return turnoRepo.buscarPorDia(dia, dniProfesional);
    }
    public Turno buscarPorHoraYDia(LocalDate dia, LocalTime hora, String dniProfesional){
        return turnoRepo.buscarPorHoraYDia(hora, dia, dniProfesional);
    }
    public ArrayList<Turno> buscarTurnosPaciente(String dni){
        return turnoRepo.buscarTurnoPaciente(dni);
    }
    public ArrayList<Turno> buscarTurnosProfesional(String dni){
        return turnoRepo.buscarTurnoProfesional(dni);
    }
}
