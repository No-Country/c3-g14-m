package com.clinica.sgt.servicios;

import com.clinica.sgt.entidades.Genero;
import com.clinica.sgt.entidades.Paciente;
import com.clinica.sgt.entidades.UserType;
import com.clinica.sgt.repositorios.PacienteRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PacienteServicio{
    
    @Autowired
    PacienteRepositorio pacienteRepositorio;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    //****************************VALIDACION******************
    public void validar( String dni, String mail,
    String password, String nombre) throws Exception{
        
        Paciente p = new Paciente();
        p = null;
        p = pacienteRepositorio.buscarPorDNI(dni);
        if(p != null && p.getDni() == dni){
            throw new Exception("El DNI ya existe en la base de datos");
        }else if(dni.isEmpty() || dni == null){
            throw new Exception("Debe ingresar datos validos para el DNI");
        }

        if(password.isEmpty() || password == null){
            throw new Exception("Debe ingresar datos validos para la contrasena, no puede estar vacia");
        }
        
        p = pacienteRepositorio.findByUsername(nombre); //el username es igual al mail
        if (p != null && p.getMail() == mail) {
            throw new Exception("Ya existe un usuario con ese mail");
        }else if(mail.isEmpty() || mail == null){
            throw new Exception("Debe ingresar datos validos para el mail");
        }
   
    }
    
    //****************************CREACION******************
    @Transactional
    public void crearPaciente(String historiaClinica, String dni, String mail,
            String password, String nombre, String telefono, Genero genero,
            Boolean alta, UserType userType) throws Exception {

        validar(dni, mail, password, nombre);

        Paciente paciente = new Paciente();
        paciente.setHistoriaClinica(historiaClinica);
        paciente.setDni(dni);
        paciente.setMail(mail);
        String passCoded = passwordEncoder.encode(password);
        // System.out.println(passCoded);
        paciente.setPassword(passCoded);
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
        paciente.setPassword(passwordEncoder.encode(password));
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

    public Paciente buscarPacientePorUsername(String username){
        Paciente existePaciente = pacienteRepositorio.findByUsername(username);
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
