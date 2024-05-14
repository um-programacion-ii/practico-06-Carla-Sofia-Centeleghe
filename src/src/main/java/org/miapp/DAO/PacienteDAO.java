package org.miapp.DAO;

import org.miapp.Clases.Paciente;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class PacienteDAO {
    private static final String FILE_PATH = "pacientes.json";
    private Map<Integer, Paciente> pacientes;
    public ObjectMapper objectMapper;

    public PacienteDAO() {
        this.pacientes = new HashMap<>();
        this.objectMapper = new ObjectMapper();
        loadPacientesFromFile();
    }

    private void loadPacientesFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                String json = new String(Files.readAllBytes(file.toPath()));
                pacientes = objectMapper.readValue(json, Map.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void crearPaciente(Paciente paciente) {
        if (!pacientes.containsKey(paciente.getId())) {
            pacientes.put(paciente.getId(), paciente);
            savePacientesToFile();
        } else {
            throw new IllegalArgumentException("El ID del paciente debe ser único.");
        }
    }

    public Paciente obtenerPacientePorId(int id) {
        return pacientes.get(id);
    }

    public void actualizarPaciente(Paciente paciente) {
        if (pacientes.containsKey(paciente.getId())) {
            pacientes.put(paciente.getId(), paciente);
            savePacientesToFile();
        } else {
            throw new IllegalArgumentException("El paciente no existe.");
        }
    }

    public void eliminarPaciente(int id) {
        if (pacientes.containsKey(id)) {
            pacientes.remove(id);
            savePacientesToFile();
        } else {
            throw new IllegalArgumentException("El paciente no existe.");
        }
    }

    private void savePacientesToFile() {
        try {
            String json = objectMapper.writeValueAsString(pacientes);
            FileWriter writer = new FileWriter(FILE_PATH);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
