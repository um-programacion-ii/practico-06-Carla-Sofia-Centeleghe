package org.miapp.Clases;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Paciente {
    private int id;
    private String nombre;
    private ObraSocial obraSocial;
    private Turno turnoActual;

    public Paciente(String pacienteEjemplo) {

    }
}