package com.clinica.sgt.servicios;

import com.clinica.sgt.entidades.Genero;
import com.clinica.sgt.entidades.Paciente;
import com.clinica.sgt.entidades.UserType;
import com.clinica.sgt.repositorios.PacienteRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PacienteServicio{
    
    @Autowired
    PacienteRepositorio pacienteRepositorio;

    public void validar( String dni, String mail,
    String password, String nombre, String telefono, Genero genero,
    Boolean alta, UserType userType){
        
    }
    
    //****************************CREACION******************
    @Transactional
    public void crearPaciente(String historiaClinica, String dni, String mail,
            String password, String nombre, String telefono, Genero genero,
            Boolean alta, UserType userType) {

        Paciente paciente = new Paciente();
        paciente.setHistoriaClinica(historiaClinica);
        paciente.setDni(dni);
        paciente.setMail(mail);
        String encriptar = new BCryptPasswordEncoder().encode(password);
        paciente.setPassword(encriptar);
        paciente.setNombre(nombre);
        paciente.setTelefono(telefono);
        paciente.setGenero(genero);
        paciente.setUserType(userType);
        paciente.setAlta(alta);
        pacienteRepositorio.save(paciente);
    }
    
    
    //******************UPDATE***********************
    @Transactional
    public void modificarPaciente(String id,String dni, String mail, String password,
            String nombre, String telefono, Genero genero, Boolean alta, UserType userType) {
        Paciente paciente = pacienteRepositorio.buscarPorID(id);
        
        paciente.setNombre(nombre);
        paciente.setDni(dni);
        paciente.setMail(mail);
        String encriptar = new BCryptPasswordEncoder().encode(password);
        paciente.setPassword(encriptar);
        paciente.setTelefono(telefono);
        paciente.setGenero(genero);
        paciente.setUserType(userType);
        paciente.setAlta(true);
        
        pacienteRepositorio.save(paciente);

    }
    
    //************************BUSQUEDA O CONSULTA*******************
    public Paciente buscarPacientePorDNI(String dni){
       Paciente existePaciente = pacienteRepositorio.buscarPorDNI(dni);
       if(existePaciente !=null){
           return existePaciente;
       }
       return null;
    }
    
    public Paciente buscarPacientePorID(String id){
       Paciente existePaciente = pacienteRepositorio.buscarPorID(id);
       if(existePaciente !=null){
           return existePaciente;
       }
       return null;
    }

    //***********************BAJA*****************
    @Transactional
    public void bajaPaciente(String id, UserType userType) {
        Paciente paciente = pacienteRepositorio.buscarPorID(id);
        switch (userType) {
            case ADMIN:
                if (paciente != null) {
                    paciente.setAlta(false);
                    pacienteRepositorio.save(paciente);
                }
                System.out.println("No existe un paciente con el ID ingresado");
                break;
            default:
                System.out.println("No tiene privilegios de Administrador");
                break;
        }

    }
    

    //***********************AlTA*****************
    @Transactional
    public void altaPaciente(String id, UserType userType) {
        Paciente paciente = pacienteRepositorio.buscarPorID(id);
        switch (userType) {
            case ADMIN:
                if (paciente != null) {
                    paciente.setAlta(true);
                    pacienteRepositorio.save(paciente);
                }
                System.out.println("No existe un paciente con el ID ingresado");
                break;
            default:
                System.out.println("No tiene privilegios de Administrador");
                break;
        }
    }

}
