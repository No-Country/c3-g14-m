/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.sgt.repositorios;

import com.clinica.sgt.entidades.Personal;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalRepositorio extends JpaRepository<Personal,String>{
     @Query("SELECT p FROM Usuario p WHERE p.alta = 1 AND user_type='PROFESIONAL' ORDER BY p.nombre ASC")
     public ArrayList<Personal> mostrarPersonalActivo();
    
    @Query("SELECT p FROM Usuario p ORDER BY p.nombre ASC")
    public List<Personal> mostrarTodoPersonal();
    
    @Query("SELECT p FROM Usuario p WHERE p.mail = :nombre")
    public Personal findByUsername(@Param("nombre")String nombre);
    
    @Query("SELECT p FROM Usuario p WHERE p.id = :id")
    public Personal buscarPorID(@Param("id")String id);

    @Query("SELECT p FROM Usuario p WHERE p.dni = :dni")
    public Personal buscarPorDNI(@Param("dni")String dni);
    
    @Query("SELECT inicioLaboral FROM Usuario p WHERE p.id = :id")
    public Personal buscarHoraInicio(@Param("id")String id);
    
    @Query("SELECT finLaboral FROM Usuario p WHERE p.id = :id")
    public Personal buscarHoraFin(@Param("id")String id);
}
