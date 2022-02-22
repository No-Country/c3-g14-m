/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.sgt.entidades;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Personal extends Usuario {

    @ManyToOne
    private Profesion profesion;
    private LocalTime inicioLaboral; // Inicio del horario laboral de cada profesional
    private LocalTime finLaboral; //Fin del horario laboral de cada profesional

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

    public LocalTime getInicioLaboral() {
        return inicioLaboral;
    }

    public void setInicioLaboral(LocalTime inicioLaboral) {
        this.inicioLaboral = inicioLaboral;
    }

    public LocalTime getFinLaboral() {
        return finLaboral;
    }

    public void setFinLaboral(LocalTime finLaboral) {
        this.finLaboral = finLaboral;
    }

    
    
}
