package org.miapp.Servicios;

import lombok.Getter;
import lombok.Setter;
import org.miapp.DAO.TurnoDAO;
import org.miapp.Clases.Turno;

import java.util.Collections;
import java.util.List;

public class GestionTurnoService {
    private static TurnoDAO turnoDAO;
    @Getter
    @Setter

    private static GestionTurnoService instance = new GestionTurnoService(turnoDAO);

    public GestionTurnoService(TurnoDAO turnoDAO) {
        GestionTurnoService.turnoDAO = new TurnoDAO();
    }

    public List<Turno> obtenerTodosLosTurnos(int id) {
        Turno turno = turnoDAO.obtenerTurnoPorId(id);
        if (turno == null) {
            return Collections.emptyList(); // Devuelve una lista vacía si el turno no se encuentra
        }
        return Collections.singletonList(turno); // Devuelve una lista con el turno si se encuentra
    }

    public Turno agregarTurno(Turno turno) {
        return turnoDAO.crearTurno(turno);
    }

    public Turno actualizarTurno(Turno turnoActualizado) {
        return turnoDAO.actualizarTurno(turnoActualizado);
    }

    public void eliminarTurno(int idTurno) {
        turnoDAO.eliminarTurno(idTurno);
    }

    public Turno obtenerTurnoPorId(int idTurno) {
        Turno turno = turnoDAO.obtenerTurnoPorId(idTurno);
        if (turno != null) {
            // Cargar la lista de médicos asignados para el turno
            turno.setMedicosAsignados(turnoDAO.obtenerMedicosPorTurno(idTurno));
        }
        return turno;
    }

    public List<Turno> buscarTurnosPorFiltro(String filtro) {
        return turnoDAO.buscarTurnosPorFiltro(filtro);
    }
}
