package org.miapp.Clases;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Medicamento {
    private int id;
    private String nombre;
    private int stock;

    private String categoria; // Nuevo campo para la categoría del medicamento


    public Medicamento(String recetaEjemplo) {

    }
}

