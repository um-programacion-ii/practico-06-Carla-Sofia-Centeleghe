package org.miapp.DAO;

import org.miapp.Clases.Medicamento;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Paths;


public class MedicamentoDAO {
    private static final String FILE_PATH = "medicament.json";
    private Map medicamentos;
    private final ObjectMapper objectMapper;

    public MedicamentoDAO() {
        this.medicamentos = new HashMap<>();
        this.objectMapper = new ObjectMapper();
        loadMedicamentosFromFile();
    }

    private void loadMedicamentosFromFile() {
        try {
            String json = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            medicamentos = objectMapper.readValue(json, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void crearMedicamento(Medicamento medicamento) {
        if (medicamentos.containsKey(medicamento.getId())) {
            throw new IllegalArgumentException("El ID del medicamento debe ser único.");
        } else {
            medicamentos.put(medicamento.getId(), medicamento);
            saveMedicamentosToFile();
        }
    }

    public Object obtenerMedicamentoPorId(int id) {
        return medicamentos.get(id);
    }

    public void actualizarMedicamento(Medicamento medicamento) {
        if (medicamentos.containsKey(medicamento.getId())) {
            medicamentos.put(medicamento.getId(), medicamento);
            saveMedicamentosToFile();
        } else {
            throw new IllegalArgumentException("El medicamento no existe.");
        }
    }

    public void eliminarMedicamento(int id) {
        if (medicamentos.containsKey(id)) {
            medicamentos.remove(id);
            saveMedicamentosToFile();
        } else {
            throw new IllegalArgumentException("El medicamento no existe.");
        }
    }

    private void saveMedicamentosToFile() {
        try {
            String json = objectMapper.writeValueAsString(medicamentos);
            FileWriter writer = new FileWriter(FILE_PATH);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
