package org.miapp.DAO;

import org.miapp.Clases.Medico;
import org.miapp.Clases.Turno;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class TurnoDAO {
    private static final String FILE_PATH = "turnos.json";
    private Map<Integer, Turno> turnos;
    private ObjectMapper objectMapper;

    public TurnoDAO() {
        this.turnos = new HashMap<>();
        this.objectMapper = new ObjectMapper();
        loadTurnosFromFile();
    }

    private void loadTurnosFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                String json = new String(Files.readAllBytes(file.toPath()));
                turnos = objectMapper.readValue(json, Map.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Turno crearTurno(Turno turno) {
        if (!turnos.containsKey(turno.getId())) {
            turnos.put(turno.getId(), turno);
            saveTurnosToFile();
        } else {
            throw new IllegalArgumentException("El ID del turno debe ser único.");
        }
        return turno;
    }

    public Turno obtenerTurnoPorId(int id) {
        return turnos.get(id);
    }

    public Turno actualizarTurno(Turno turno) {
        if (turnos.containsKey(turno.getId())) {
            turnos.put(turno.getId(), turno);
            saveTurnosToFile();
        } else {
            throw new IllegalArgumentException("El turno no existe.");
        }
        return turno;
    }

    public boolean eliminarTurno(int id) {
        if (turnos.containsKey(id)) {
            turnos.remove(id);
            saveTurnosToFile();
        } else {
            throw new IllegalArgumentException("El turno no existe.");
        }
        return false;
    }

    private void saveTurnosToFile() {
        try {
            String json = objectMapper.writeValueAsString(turnos);
            FileWriter writer = new FileWriter(FILE_PATH);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Turno> buscarTurnosPorFiltro(String filtro) {
        List<Turno> turnosFiltrados = new ArrayList<>();
        for (Turno turno : turnos.values()) {
            if (Integer.toString(turno.getId()).equals(filtro) || Integer.toString(turno.getPaciente().getId()).equals(filtro)) {
                turnosFiltrados.add(turno);
            }
        }
        return turnosFiltrados;
    }

    public List<Medico> obtenerMedicosPorTurno(int idTurno) {
        Turno turno = turnos.get(idTurno);
        if (turno != null) {
            return turno.getMedicosAsignados();
        }
        return Collections.emptyList(); // Devuelve una lista vacía si no se encuentra el turno o si no hay médicos asignados
    }


}


