/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.sgt.entidades;

import java.time.LocalTime;

import javax.persistence.Entity;

@Entity
public class Personal extends Usuario {

    private LocalTime inicioLaboral; // Inicio del horario laboral de cada profesional
    private LocalTime finLaboral; //Fin del horario laboral de cada profesional
     
    
    public Personal() {
    }

    public Personal(LocalTime inicioLaboral, LocalTime finLaboral, String id,
        String dni, String mail, String password, String nombre, String telefono,
        Genero genero, Boolean alta, UserType userType) {
        super(id, dni, mail, password, nombre, telefono, genero, alta, userType);
        this.inicioLaboral = inicioLaboral;
        this.finLaboral = finLaboral;
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
