/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.sgt.repositorios;

import com.clinica.sgt.entidades.Personal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalRepositorio extends JpaRepository<Personal,String>{
     @Query("SELECT p FROM Personal p WHERE p.alta = 1 ORDER BY p.nombre ASC")
     public List<Personal> mostrarPersonalActivo();
    
    @Query("SELECT p FROM Personal p ORDER BY p.nombre ASC")
    public List<Personal> mostrarTodoPersonal();
    
    @Query("SELECT p FROM Personal p WHERE p.nombre = :nombre")
    public List<Personal> findByUsername(@Param("nombre")String nombre);
    
    @Query("SELECT p FROM Personal p WHERE p.id = :id")
    public Personal buscarPorID(@Param("id")String id);

    @Query("SELECT p FROM Personal p WHERE p.dni = :dni")
    public Personal buscarPorDNI(@Param("dni")String dni);
}
