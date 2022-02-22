package com.clinica.sgt.entidades;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Turno {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private LocalDate dia;
    private LocalTime hora;
    @ManyToOne
    private Paciente paciente;
    @ManyToOne
    private Personal personal;
    private boolean alta;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public LocalDate getDia() {
        return dia;
    }
    public void setDia(LocalDate dia) {
        this.dia = dia;
    }
    public LocalTime getHora() {
        return hora;
    }
    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
    public Paciente getPaciente() {
        return paciente;
    }
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    public Personal getPersonal() {
        return personal;
    }
    public void setPersonal(Personal personal) {
        this.personal = personal;
    }
    public boolean isAlta() {
        return alta;
    }
    public void setAlta(boolean alta) {
        this.alta = alta;
    }
    @Override
    public String toString() {
        return "Turno [dia=" + dia + ", hora=" + hora + ", paciente=" + paciente.getNombre() + ", profesional=" + personal.getNombre() + "]";
    }

    
}
