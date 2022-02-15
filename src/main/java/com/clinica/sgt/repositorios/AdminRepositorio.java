/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.sgt.repositorios;

import com.clinica.sgt.entidades.Admin;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepositorio extends JpaRepository<Admin,String>{
    @Query("SELECT a FROM Admin a WHERE a.alta = 1 ORDER BY a.nombre ASC")
    public List<Admin> mostrarAdministradoresActivos();
    
    @Query("SELECT a FROM Admin a ORDER BY a.nombre ASC")
    public List<Admin> mostrarTodosAdministradores();
    
    @Query("SELECT a FROM Admin a WHERE a.nombre = :nombre")
    public List<Admin> findByUsername(@Param("nombre")String nombre);
    
    @Query("SELECT a FROM Admin a WHERE a.id = :id")
    public Admin buscarPorID(@Param("id")String id);
}
