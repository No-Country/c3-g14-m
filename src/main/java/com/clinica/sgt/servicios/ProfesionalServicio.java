package com.clinica.sgt.servicios;

import com.clinica.sgt.entidades.Admin;
import com.clinica.sgt.repositorios.AdminRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfesionalServicio extends UsuarioServicio{
    
    @Autowired
    AdminRepositorio adminRepo;

    public Admin buscarProfesionalPorDNI(String dni){
        return adminRepo.buscarPorDNI(dni);
    }

}
