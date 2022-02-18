/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.sgt.entidades;

import javax.persistence.Entity;

@Entity
public class Admin extends Usuario {

    private String especialidad;

    public Admin(String especialidad, String dni, String mail, String password,
             String nombreCompleto, String telefono, Genero genero, Boolean alta,
            UserType userType) {
        super(dni, mail, password, nombreCompleto, telefono, genero, alta, userType);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    
}
