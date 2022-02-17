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
            String nombreCompleto, String telefono, Genero genero, Boolean alta,
            UserType userType){
        
        Usuario usuario = new Usuario();
        usuario.setNombreCompleto(nombreCompleto);
        usuario.setDni(dni);
        usuario.setMail(mail);
        usuario.setPassword(dni);
        usuario.setTelefono(telefono);
        usuario.setGenero(genero);
        usuario.setAlta(true);
    }
    
    
    //******************UPDATE***********************
    @Transactional
    public void modificarUsuario(String dni, String mail, String password,
            String nombreCompleto, String telefono, Genero genero, Boolean alta,
            UserType userType){
        
    }
            
}
