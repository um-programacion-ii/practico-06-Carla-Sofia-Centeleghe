package org.miapp.DAO;

import org.miapp.Clases.Medico;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.nio.file.Files;

public class MedicoDAO {
    private static final String FILE_PATH = "medicos.json";
    private Map<Integer, Medico> medicos;
    private ObjectMapper objectMapper;

    public MedicoDAO() {
        this.medicos = new HashMap<>();
        this.objectMapper = new ObjectMapper();
        loadMedicosFromFile();
    }

    private void loadMedicosFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                String json = new String(Files.readAllBytes(file.toPath()));
                medicos = objectMapper.readValue(json, Map.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void crearMedico(Medico medico) {
        if (!medicos.containsKey(medico.getId())) {
            medicos.put(medico.getId(), medico);
            saveMedicosToFile();
        } else {
            throw new IllegalArgumentException("El ID del médico debe ser único.");
        }
    }

    public Medico obtenerMedicoPorId(int id) {
        return medicos.get(id);
    }

    public void actualizarMedico(Medico medico) {
        if (medicos.containsKey(medico.getId())) {
            medicos.put(medico.getId(), medico);
            saveMedicosToFile();
        } else {
            throw new IllegalArgumentException("El médico no existe.");
        }
    }

    public void eliminarMedico(int id) {
        if (medicos.containsKey(id)) {
            medicos.remove(id);
            saveMedicosToFile();
        } else {
            throw new IllegalArgumentException("El médico no existe.");
        }
    }

    private void saveMedicosToFile() {
        try {
            String json = objectMapper.writeValueAsString(medicos);
            FileWriter writer = new FileWriter(FILE_PATH);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
