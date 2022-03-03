/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.sgt.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Usuario implements UserDetails{

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String dni;
    private String mail;
    private String password;
    private String nombre;
    private String telefono;
    @Enumerated(EnumType.STRING)
    private Genero genero;
    private Boolean alta;
    @Enumerated(EnumType.STRING)
    private UserType userType;
   

    public Usuario() {
        }

    public Usuario(String id, String dni, String mail, String password,
            String nombre, String telefono, Genero genero, Boolean alta,
            UserType userType) {
        this.id = id;
        this.dni = dni;
        this.mail = mail;
        this.password = password;
        this.nombre = nombre;
        this.telefono = telefono;
        this.genero = genero;
        this.alta = alta;
        this.userType = userType;
    }

    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Boolean isAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

     //Overrides referidos a la seguridad 

     @Override
     public Collection<? extends GrantedAuthority> getAuthorities() {
         List<GrantedAuthority> roles = new ArrayList<>();
         roles.add(new SimpleGrantedAuthority(userType.toString()));
         return roles;
     }
 
     @Override
     public String getUsername() {
         return mail;
     }
 
     @Override
     public boolean isAccountNonExpired() {
         return true;
     }
 
     @Override
     public boolean isAccountNonLocked() {
         return true;
     }
 
     @Override
     public boolean isCredentialsNonExpired() {
         return true;
     }
 
     @Override
     public boolean isEnabled() {
         return true;
     }

   
}
