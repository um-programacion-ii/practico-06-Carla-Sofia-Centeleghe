package org.miapp.DAO;

import org.miapp.Clases.Turno;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

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

    public void crearTurno(Turno turno) {
        if (!turnos.containsKey(turno.getId())) {
            turnos.put(turno.getId(), turno);
            saveTurnosToFile();
        } else {
            throw new IllegalArgumentException("El ID del turno debe ser único.");
        }
    }

    public Turno obtenerTurnoPorId(int id) {
        return turnos.get(id);
    }

    public void actualizarTurno(Turno turno) {
        if (turnos.containsKey(turno.getId())) {
            turnos.put(turno.getId(), turno);
            saveTurnosToFile();
        } else {
            throw new IllegalArgumentException("El turno no existe.");
        }
    }

    public void eliminarTurno(int id) {
        if (turnos.containsKey(id)) {
            turnos.remove(id);
            saveTurnosToFile();
        } else {
            throw new IllegalArgumentException("El turno no existe.");
        }
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
}
