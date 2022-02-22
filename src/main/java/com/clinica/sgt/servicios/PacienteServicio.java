package com.clinica.sgt.servicios;

import com.clinica.sgt.entidades.Paciente;
import com.clinica.sgt.repositorios.PacienteRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteServicio extends UsuarioServicio{
    
    @Autowired
    PacienteRepositorio pacienteRepo;

    public Paciente buscarPacientePorDNI(String dni){
        return pacienteRepo.buscarPorDNI(dni);
    }

}
