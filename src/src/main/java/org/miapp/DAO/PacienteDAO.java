package org.miapp.DAO;

import org.miapp.Clases.Paciente;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PacienteDAO {
    private static final String FILE_PATH = "pacientes.json";
    private Map<Integer, Paciente> pacientes;
    private ObjectMapper objectMapper;

    public PacienteDAO() {
        this.pacientes = new HashMap<>();
        this.objectMapper = new ObjectMapper();
        loadPacientesFromFile();
    }

    private void loadPacientesFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                String json = new String(file.getBytes());
                pacientes = objectMapper.readValue(json, objectMapper.getTypeFactory().constructMapType(Map.class, Paciente.class));
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
            File file = new File(FILE_PATH);
            file.writeBytes(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}