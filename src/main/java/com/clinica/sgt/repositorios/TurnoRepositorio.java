package com.clinica.sgt.repositorios;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import com.clinica.sgt.entidades.Turno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepositorio extends JpaRepository<Turno, String>{
    
    @Query("SELECT t FROM Turnos t WHERE t.paciente.dni = :dni")
    public ArrayList<Turno> buscarTurnoPaciente(@Param("dni") String dni);

    @Query("SELECT t FROM Turnos t WHERE t.admin.dni = :dni")
    public ArrayList<Turno> buscarTurnoProfesional(@Param("dni") String dni);

    @Query("SELECT t FROM Turnos t WHERE t.hora = :hora , t.dia = :dia AND t.profesional.dni = :dniProfesional")
    public Turno buscarPorHoraYDia(@Param("hora") LocalTime hora,@Param("dia") LocalDate dia, @Param("dniProfesional") String dniProfesional);

    @Query("SELECT t FROM Turnos t WHERE t.dia = :dia AND t.profesional.dni = :dniProfesional")
    public ArrayList<Turno> buscarPorDia(@Param("dia") LocalDate dia, @Param("dniProfesional") String dniProfesional);

    @Query("SELECT t FROM Turnos t WHERE t.id = :id")
    public Turno buscarPorID(@Param("id") String id);


}
