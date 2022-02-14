package com.clinica.sgt.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository{
    
    public Usuario findByUsername(String nombre);
}
