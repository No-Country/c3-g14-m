package com.clinica.sgt.servicios;

import com.clinica.sgt.entidades.Personal;
import com.clinica.sgt.repositorios.PersonalRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalServicio extends UsuarioServicio{
    
    @Autowired
    PersonalRepositorio personalRepo;

    public Personal buscarPorDNI(String dni){
        return personalRepo.buscarPorDNI(dni);
    }

    public Personal buscarPorID(String id){
        return personalRepo.buscarPorID(id);
    }
}
