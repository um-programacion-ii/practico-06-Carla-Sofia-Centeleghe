package org.miapp.Clases;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Setter
@Getter
public class Medico {
    private int id;
    private String nombre;
    private Especialidad especialidad;
    private ObraSocial obraSocial;
    private boolean atiendeParticular;
    private List<Turno> turnosAsignados; // Lista de turnos a los que está asignado el médico

    public Medico(String médicoEjemplo) {

    }
}
