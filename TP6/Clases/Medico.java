package org.miapp.Clases;

public class Medico {
    private int id;
    private String nombre;
    private Especialidad especialidad;
    private ObraSocial obraSocial;
    private boolean atiendeParticular;

    // Constructor, getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public ObraSocial getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(ObraSocial obraSocial) {
        this.obraSocial = obraSocial;
    }

    public boolean isAtiendeParticular() {
        return atiendeParticular;
    }

    public void setAtiendeParticular(boolean atiendeParticular) {
        this.atiendeParticular = atiendeParticular;
    }
}