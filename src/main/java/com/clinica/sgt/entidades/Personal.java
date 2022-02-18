/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.sgt.entidades;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Personal extends Usuario {

    @ManyToOne
    private Profesion profesion;

    public Personal() {
    }

    public Personal(Profesion profesion) {
        this.profesion = profesion;
    }

    public Profesion getProfesion() {
        return profesion;
    }

    public void setProfesion(Profesion profesion) {
        this.profesion = profesion;
    }

    
    
}
