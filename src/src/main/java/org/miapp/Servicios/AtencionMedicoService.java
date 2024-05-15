package org.miapp.Servicios;

import lombok.Getter;
import lombok.Setter;
import org.miapp.DAO.MedicoDAO;
import org.miapp.Clases.Medico;

import java.util.List;

public class AtencionMedicoService {
    private final MedicoDAO medicoDAO = new MedicoDAO();
    @Getter
    @Setter
    private static AtencionMedicoService instance = new AtencionMedicoService();

    public AtencionMedicoService() {
    }

    public List<Medico> obtenerMedicosPorEspecialidadYObraSocial(String especialidad, String obraSocial) {
        return medicoDAO.obtenerMedicosPorEspecialidadYObraSocial(especialidad, obraSocial);
    }

    public List<Medico> obtenerMedicosParaTurnoParticular() {
        return medicoDAO.obtenerMedicosParaTurnoParticular();
    }

    public List<Medico> listarTodosLosMedicos() {
        return medicoDAO.listarTodosLosMedicos();
    }

    public Medico agregarMedico(Medico medico) {
        return medicoDAO.agregarMedico(medico);
    }

    public Medico actualizarMedico(Medico medicoActualizado) {
        return medicoDAO.actualizarMedico(medicoActualizado);
    }

    public boolean eliminarMedico(int idMedico) {
        return medicoDAO.eliminarMedico(idMedico);
    }

    public Medico obtenerMedicoPorId(int idMedico) {
        return medicoDAO.obtenerMedicoPorId(idMedico);
    }


    public boolean asignarMedicoATurno(int idMedico, int idTurno) {
        return medicoDAO.asignarMedicoATurno(idMedico, idTurno);
    }
}
