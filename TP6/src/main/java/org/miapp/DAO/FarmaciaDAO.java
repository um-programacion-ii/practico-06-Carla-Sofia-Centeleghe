package org.miapp.DAO;

import org.miapp.Clases.Farmacia;
import org.miapp.Clases.Medicamento;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FarmaciaDAO {
    private static final String FILE_PATH = "farmacia_stock.json";
    private Farmacia farmacia;
    private ObjectMapper objectMapper;

    public FarmaciaDAO() {
        this.farmacia = new Farmacia();
        this.objectMapper = new ObjectMapper();
        loadStockMedicamentosFromFile();
    }

    private void loadStockMedicamentosFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                String json = new String(file.getBytes());
                farmacia.setStockMedicamentos(objectMapper.readValue(json, objectMapper.getTypeFactory().constructMapType(Map.class, Medicamento.class, Integer.class)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void crearStockMedicamento(Medicamento medicamento, int cantidad) {
        if (!farmacia.getStockMedicamentos().containsKey(medicamento)) {
            farmacia.getStockMedicamentos().put(medicamento, cantidad);
            saveStockMedicamentosToFile();
        } else {
            throw new IllegalArgumentException("El medicamento ya existe en el stock.");
        }
    }

    public int obtenerStockMedicamento(Medicamento medicamento) {
        return farmacia.getStockMedicamentos().getOrDefault(medicamento, 0);
    }

    public void actualizarStockMedicamento(Medicamento medicamento, int cantidad) {
        if (farmacia.getStockMedicamentos().containsKey(medicamento)) {
            farmacia.getStockMedicamentos().put(medicamento, cantidad);
            saveStockMedicamentosToFile();
        } else {
            throw new IllegalArgumentException("El medicamento no existe en el stock.");
        }
    }

    public void eliminarStockMedicamento(Medicamento medicamento) {
        if (farmacia.getStockMedicamentos().containsKey(medicamento)) {
            farmacia.getStockMedicamentos().remove(medicamento);
            saveStockMedicamentosToFile();
        } else {
            throw new IllegalArgumentException("El medicamento no existe en el stock.");
        }
    }

    private void saveStockMedicamentosToFile() {
        try {
            String json = objectMapper.writeValueAsString(farmacia.getStockMedicamentos());
            File file = new File(FILE_PATH);
            file.writeBytes(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}