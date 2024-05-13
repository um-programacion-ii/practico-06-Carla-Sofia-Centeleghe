package org.miapp.Servicios;

import org.miapp.DAO.TurnoDAO;
import org.miapp.Clases.Turno;

import java.util.List;

public class GestionTurnoService {
    private TurnoDAO turnoDAO;
    private static final GestionTurnoService instance = new GestionTurnoService();

    private GestionTurnoService() {
        this.turnoDAO = new TurnoDAO();
    }

    public static GestionTurnoService getInstance() {
        return instance;
    }

    public List<Turno> obtenerTodosLosTurnos() {
        return turnoDAO.obtenerTodosLosTurnos();
    }

    public Turno agregarTurno(Turno turno) {
        return turnoDAO.agregarTurno(turno);
    }

    public Turno actualizarTurno(Turno turnoActualizado) {
        return turnoDAO.actualizarTurno(turnoActualizado);
    }

    public boolean eliminarTurno(int idTurno) {
        return turnoDAO.eliminarTurno(idTurno);
    }

    public Turno obtenerTurnoPorId(int idTurno) {
        return turnoDAO.obtenerTurnoPorId(idTurno);
    }

    public List<Turno> buscarTurnosPorFiltro(String filtro) {
        return turnoDAO.buscarTurnosPorFiltro(filtro);
    }
}
