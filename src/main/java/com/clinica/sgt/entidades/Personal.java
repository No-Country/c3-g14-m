/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.sgt.entidades;

import javax.persistence.Entity;

@Entity
public class Personal extends Usuario {

    private String horarioDisponible;

    public Personal() {
    }

    public Personal(String horarioDisponible, String dni, String mail,
            String password, String nombreCompleto, String telefono,
            Genero genero, Boolean alta, UserType userType) {
        super(dni, mail, password, nombreCompleto, telefono, genero, alta, userType);
        this.horarioDisponible = horarioDisponible;
    }

    public String getHorarioDisponible() {
        return horarioDisponible;
    }

    public void setHorarioDisponible(String horarioDisponible) {
        this.horarioDisponible = horarioDisponible;
    }

}
