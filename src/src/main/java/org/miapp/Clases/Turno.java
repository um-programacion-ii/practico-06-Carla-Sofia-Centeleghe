package org.miapp.Clases;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@Getter
public class Turno {
    private int id;
    private Paciente paciente;
    private List<Medico> medicosAsignados;
    private boolean estaIniciado;
    private boolean estaCompletado;
    private List<Medicamento> receta;

    public Turno(int i, String pacienteEjemplo, String médicoEjemplo, boolean b, boolean b1, String recetaEjemplo) {

    }
}
