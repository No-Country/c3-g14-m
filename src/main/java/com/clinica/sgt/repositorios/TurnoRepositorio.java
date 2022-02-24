
package com.clinica.sgt.repositorios;

import java.time.LocalDate;
import java.time.LocalTime;

import com.clinica.sgt.entidades.Turno;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepositorio extends JpaRepository<Turno, String>{
    
    @Query("SELECT t FROM Turno t WHERE t.paciente.dni = :dni")
    public List<Turno> buscarTurnoPaciente(@Param("dni") String dni);

    @Query("SELECT t FROM Turno t WHERE t.personal.dni = :dni")
    public List<Turno> buscarTurnoProfesional(@Param("dni") String dni);

    @Query("SELECT t FROM Turno t WHERE (t.hora = :hora) AND (t.dia = :dia) AND (t.personal.dni = :dniPersonal)")
    public Turno buscarPorHoraYDia(@Param("hora") LocalTime hora,@Param("dia") LocalDate dia, @Param("dniPersonal") String dniPersonal);

    @Query("SELECT t FROM Turno t WHERE t.dia = :dia AND t.personal.dni = :dniPersonal")
    public List<Turno> buscarPorDia(@Param("dia") LocalDate dia, @Param("dniPersonal") String dniPersonal);

    @Query("SELECT t FROM Turno t WHERE t.id = :id")
    public Turno buscarPorID(@Param("id") String id);


}
