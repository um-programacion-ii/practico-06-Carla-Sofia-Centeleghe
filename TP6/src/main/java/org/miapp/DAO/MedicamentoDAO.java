package org.miapp.DAO;

import org.miapp.Clases.Medicamento;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MedicamentoDAO {
    private static final String FILE_PATH = "medicamentos.json";
    private Map<Integer, Medicamento> medicamentos;
    private ObjectMapper objectMapper;

    public MedicamentoDAO() {
        this.medicamentos = new HashMap<>();
        this.objectMapper = new ObjectMapper();
        loadMedicamentosFromFile();
    }

    private void loadMedicamentosFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                String json = new String(file.getBytes());
                medicamentos = objectMapper.readValue(json, objectMapper.getTypeFactory().constructMapType(Map.class, Medicamento.class));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void crearMedicamento(Medicamento medicamento) {
        if (!medicamentos.containsKey(medicamento.getId())) {
            medicamentos.put(medicamento.getId(), medicamento);
            saveMedicamentosToFile();
        } else {
            throw new IllegalArgumentException("El ID del medicamento debe ser único.");
        }
    }

    public Medicamento obtenerMedicamentoPorId(int id) {
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
            File file = new File(FILE_PATH);
            file.writeBytes(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}