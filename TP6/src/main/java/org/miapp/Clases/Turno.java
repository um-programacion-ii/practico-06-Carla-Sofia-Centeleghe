package org.miapp.Clases;

public class Turno {
    private int id;
    private Paciente paciente;
    private Medico medico;
    private Date fechaInicio;
    private Date fechaFin;
    private List<Medicamento> receta;

    // Constructor, getters y setters, método para agregar medicamentos a la receta

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<Medicamento> getReceta() {
        return receta;
    }

    public void setReceta(List<Medicamento> receta) {
        this.receta = receta;
    }
}
