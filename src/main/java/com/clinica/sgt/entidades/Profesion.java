/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.sgt.entidades;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Cristian
 */
public class Profesion extends Personal {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idProfesion;
    private String profesion;

    public Profesion(String idProfesion, String profesion) {
        this.idProfesion = idProfesion;
        this.profesion = profesion;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getIdProfesion() {
        return idProfesion;
    }

    public void setIdProfesion(String idProfesion) {
        this.idProfesion = idProfesion;
    }
    
    
    
}
