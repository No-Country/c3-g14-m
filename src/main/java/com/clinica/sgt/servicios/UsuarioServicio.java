/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.sgt.servicios;

import com.clinica.sgt.entidades.Genero;
import com.clinica.sgt.entidades.UserType;
import com.clinica.sgt.entidades.Usuario;
import com.clinica.sgt.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    //****************************CREACION******************
    @Transactional
    public void crearUsuario(String dni, String mail, String password,
            String nombre, String telefono, Genero genero, Boolean alta,
            UserType userType) {

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setDni(dni);
        usuario.setMail(mail);
        usuario.setPassword(dni);
        usuario.setTelefono(telefono);
        usuario.setGenero(genero);
        usuario.setAlta(true);
        
        usuarioRepositorio.save(usuario);
    }

    //******************UPDATE***********************
    @Transactional
    public void modificarUsuario(String idUsuario,String dni, String mail, String password,
            String nombre, String telefono, Genero genero, Boolean alta,
            UserType userType) {
        Usuario usuario = usuarioRepositorio.buscarPorID(idUsuario);
        
        usuario.setNombre(nombre);
        usuario.setDni(dni);
        usuario.setMail(mail);
        usuario.setPassword(password);
        usuario.setTelefono(telefono);
        usuario.setGenero(genero);
        usuario.setAlta(true);
        
        usuarioRepositorio.save(usuario);

    }
    
     //************************BUSQUEDA O CONSULTA*******************
    public Usuario buscarUsuarioDNI(String dni){
       Usuario existeUsuario = usuarioRepositorio.buscarPorDNI(dni);
       if(existeUsuario !=null){
           return existeUsuario;
       }
       return null;
    }
    
    public Usuario buscarUsuarioID(String idUsuario){
       Usuario existeUsuario = usuarioRepositorio.buscarPorID(idUsuario);
       if(existeUsuario !=null){
           return existeUsuario;
       }
       return null;
    }
    
    //***********************BAJA*****************
    @Transactional
    public void bajaUsuario(String idUsuario, UserType userType) {
        Usuario usuario = usuarioRepositorio.buscarPorID(idUsuario);
        switch (userType) {
            case ADMIN:
                if (usuario != null) {
                    usuario.setAlta(false);
                    usuarioRepositorio.save(usuario);
                }
                System.out.println("No existe un usuario con el ID ingresado");
                break;
            default:
                System.out.println("No tiene privilegios de Administrador");
                break;
        }

    }

    //***********************AlTA*****************
    @Transactional
    public void altaUsuario(String idUsuario, UserType userType) {
        Usuario usuario = usuarioRepositorio.buscarPorID(idUsuario);
        switch (userType) {
            case ADMIN:
                if (usuario != null) {
                    usuario.setAlta(true);
                    usuarioRepositorio.save(usuario);
                }
                System.out.println("No existe un usuario con el ID ingresado");
                break;
            default:
                System.out.println("No tiene privilegios de Administrador");
                break;
        }
    }

}
