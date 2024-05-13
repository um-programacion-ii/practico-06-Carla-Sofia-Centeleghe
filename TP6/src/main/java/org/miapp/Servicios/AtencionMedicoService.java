package org.miapp.Servicios;

import org.miapp.DAO.MedicoDAO;
import org.miapp.Clases.Medico;

import java.util.List;

public class AtencionMedicoService {
    private MedicoDAO medicoDAO;
    private static final AtencionMedicoService instance = new AtencionMedicoService();

    private AtencionMedicoService() {
        this.medicoDAO = new MedicoDAO();
    }

    public static AtencionMedicoService getInstance() {
        return instance;
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

    public List<Medico> buscarMedicosPorFiltroAdicional(String filtroAdicional) {
        return medicoDAO.buscarMedicosPorFiltroAdicional(filtroAdicional);
    }

    public boolean asignarMedicoATurno(int idMedico, int idTurno) {
        return medicoDAO.asignarMedicoATurno(idMedico, idTurno);
    }
}
