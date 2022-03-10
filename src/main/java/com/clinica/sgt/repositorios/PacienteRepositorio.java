/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.sgt.repositorios;

import com.clinica.sgt.entidades.Paciente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepositorio extends JpaRepository<Paciente,String>{
     @Query("SELECT p FROM Usuario p WHERE p.alta = 1 AND p.userType = 'PACIENTE' ORDER BY p.nombre ASC")
     public List<Paciente> mostrarPacientesActivos();
    
    @Query("SELECT p FROM Usuario p WHERE p.userType = 'PACIENTE' ORDER BY p.nombre ASC")
    public List<Paciente> mostrarTodosPacientes();
    
    @Query("SELECT p FROM Usuario p WHERE p.userType = 'PACIENTE' AND p.mail = :nombre")
    public Paciente findByUsername(@Param("nombre")String nombre);

    @Query("SELECT p FROM Usuario p WHERE p.userType = 'PACIENTE' AND p.id = :id")
    public Paciente buscarPorID(@Param("id")String id);

    @Query("SELECT p FROM Usuario p WHERE p.userType = 'PACIENTE' AND p.dni = :dni")
    public Paciente buscarPorDNI(@Param("dni")String dni);
}
