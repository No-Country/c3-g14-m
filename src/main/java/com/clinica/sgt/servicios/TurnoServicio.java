package com.clinica.sgt.servicios;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.clinica.sgt.entidades.Personal;
import com.clinica.sgt.entidades.Turno;
import com.clinica.sgt.repositorios.PersonalRepositorio;
import com.clinica.sgt.repositorios.TurnoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.jollyday.HolidayCalendar;
import de.jollyday.HolidayManager;
import de.jollyday.ManagerParameters;

@Service
public class TurnoServicio {
    
    @Autowired 
    TurnoRepositorio turnoRepo;

    @Autowired
    PersonalRepositorio personalRepo;

    @Autowired
    PacienteServicio pacienteServicio;
    
    private HolidayManager feriados = HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.ARGENTINA));
    
    //Validaciones
    public void validarDatos(LocalDate dia, LocalTime hora, String dniPaciente, String dniProfesional) throws Exception{

        if(dia == null || hora == null){
            throw new Exception("Los datos dia y/o hora son invalidos");
        }
        if(pacienteServicio.buscarPacientePorDNI(dniPaciente) == null){
            throw new Exception("El paciente no se encuentra en la base de datos, revise los datos ingresados");
        }
        if(personalRepo.buscarPorDNI(dniProfesional) == null){
            throw new Exception("El profesional no se encuentra en la base de datos, revise los datos ingresados");
        }

    }

    // public void validarFechaDisponible(LocalDate dia, LocalTime hora, String dniProfesional) throws Exception{
    //     if(turnoRepo.buscarPorHoraYDia(hora, dia, dniProfesional) != null){
    //         throw new Exception("La hora en el dia especificado esta ocupado");
    //     }
    // }

    public void validarHorario(LocalDate dia, LocalTime hora, String dniProfesional) throws Exception{

        if(dia.getDayOfWeek().toString().equalsIgnoreCase("SUNDAY") 
            || dia.getDayOfWeek().toString().equalsIgnoreCase("SATURDAY")
            || feriados.isHoliday(dia)){
            throw new Exception("Feriado o dia no laboral");
        }

        Personal profesional = personalRepo.buscarPorDNI(dniProfesional);

        if(hora.isBefore(profesional.getInicioLaboral()) || hora.isAfter(profesional.getFinLaboral())){
            throw new Exception("El turno no esta en el horario laboral del profesional");
        }
        
              

    }

    // Creacion
    @Transactional
    public void agregarTurno(LocalDate dia, LocalTime hora, String dniPaciente, String dniProfesional) throws Exception{

        validarDatos(dia, hora, dniPaciente, dniProfesional);
        // validarFechaDisponible(dia, hora, dniProfesional);
        validarHorario(dia, hora, dniProfesional);

        Turno turno = new Turno();

        turno.setDia(dia);
        turno.setHora(hora);
        turno.setPaciente(pacienteServicio.buscarPacientePorDNI(dniPaciente));
        turno.setPersonal(personalRepo.buscarPorDNI(dniProfesional));
        turno.setAlta(true);

        turnoRepo.save(turno);


    }

    //Modificacion
    @Transactional
    public void modificarTurno(LocalDate dia, LocalTime hora, String dniPaciente, String dniProfesional, boolean alta, String id) throws Exception{
        
        Turno turno = turnoRepo.buscarPorID(id);

        validarDatos(dia, hora, dniPaciente, dniProfesional);
        validarHorario(dia, hora, dniProfesional);

        turno.setDia(dia);
        turno.setHora(hora);
        turno.setPaciente(pacienteServicio.buscarPacientePorDNI(dniPaciente));
        turno.setPersonal(personalRepo.buscarPorDNI(dniProfesional));
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
    // public List<Turno> buscarPorDia(LocalDate dia, String dniProfesional){
    //     return turnoRepo.buscarPorDia(dia, dniProfesional);
    // }
    // public Turno buscarPorHoraYDia(LocalDate dia, LocalTime hora, String dniProfesional){
    //     return turnoRepo.buscarPorHoraYDia(hora, dia, dniProfesional);
    // }
    public List<Turno> buscarTurnosPaciente(String dni){
        return turnoRepo.buscarTurnoPaciente(dni);
    }
    public List<Turno> buscarTurnosProfesional(String dni){
        return turnoRepo.buscarTurnoProfesional(dni);
    }
}
