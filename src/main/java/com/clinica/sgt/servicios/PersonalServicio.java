package com.clinica.sgt.servicios;

import com.clinica.sgt.entidades.Genero;
import com.clinica.sgt.entidades.Personal;
import com.clinica.sgt.entidades.UserType;
import com.clinica.sgt.repositorios.PersonalRepositorio;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonalServicio{
    
    @Autowired
    PersonalRepositorio personalRepositorio;
    
    //****************************CREACION******************
    @Transactional
    public void crearPersonal(LocalTime inicioLaboral, LocalTime finLaboral,
            String dni, String mail, String password, String nombre,
            String telefono, Genero genero, Boolean alta, UserType userType) {

        Personal personal = new Personal();
        personal.setInicioLaboral(inicioLaboral);
        personal.setFinLaboral(finLaboral);
        personal.setDni(dni);
        personal.setMail(mail);
        personal.setPassword(password);
        personal.setNombre(nombre);
        personal.setTelefono(telefono);
        personal.setGenero(genero);
        
        personalRepositorio.save(personal);
    }
    
    
    //******************UPDATE***********************
    @Transactional
    public void modificarPersonal(String id,String dni, String mail, String password,
            String nombre, String telefono, Genero genero, Boolean alta,
            UserType userType) {
        Personal personal = personalRepositorio.buscarPorID(id);
        
        personal.setNombre(nombre);
        personal.setDni(dni);
        personal.setMail(mail);
        personal.setPassword(password);
        personal.setTelefono(telefono);
        personal.setGenero(genero);
        personal.setAlta(true);
        personal.setUserType(userType);
        
        personalRepositorio.save(personal);

    }
    
    //************************BUSQUEDA O CONSULTA*******************
    public Personal buscarPersonalPorDNI(String dni){
       Personal existePersonal = personalRepositorio.buscarPorDNI(dni);
       if(existePersonal !=null){
           return existePersonal;
       }
       return null;
    }
    
    public Personal buscarPersonalPorID(String id){
       Personal existePersonal = personalRepositorio.buscarPorID(id);
       if(existePersonal !=null){
           return existePersonal;
       }
       return null;
    }

    public Personal buscarPersonalPorUsername(String username){
        Personal existePersonal = personalRepositorio.findByUsername(username);
        if(existePersonal !=null){
            return existePersonal;
        }
        return null;
     }

    //***********************BAJA*****************
    @Transactional
    public void bajaPersonal(String id, UserType userType) {
        Personal personal = personalRepositorio.buscarPorID(id);
        switch (userType) {
            case ADMIN:
                if (personal != null) {
                    personal.setAlta(false);
                    personalRepositorio.save(personal);
                }
                System.out.println("No existe personal con el ID ingresado");
                break;
            default:
                System.out.println("No tiene privilegios de Administrador");
                break;
        }

    }
    

    //***********************AlTA*****************
    @Transactional
    public void altaPersonal(String id, UserType userType) {
        Personal personal = personalRepositorio.buscarPorID(id);
        switch (userType) {
            case ADMIN:
                if (personal != null) {
                    personal.setAlta(true);
                    personalRepositorio.save(personal);
                }
                System.out.println("No existe personal con el ID ingresado");
                break;
            default:
                System.out.println("No tiene privilegios de Administrador");
                break;
        }
    }

}
