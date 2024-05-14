package org.miapp.DAO;

import org.miapp.Clases.Farmacia;
import org.miapp.Clases.Medicamento;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

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
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                StringBuilder json = new StringBuilder();
                while ((line = reader.readLine())!= null) {
                    json.append(line);
                }
                reader.close();
                farmacia.setStockMedicamentos(objectMapper.readValue(json.toString(), new TypeReference<Map<Medicamento, Integer>>() {}));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void crearStockMedicamento(Medicamento medicamento, int cantidad) {
        farmacia.addMedicamentoStock(medicamento, cantidad);
        saveStockMedicamentosToFile();
    }

    public int obtenerStockMedicamento(Medicamento medicamento) {
        return farmacia.getMedicamentoStock(medicamento);
    }

    public void actualizarStockMedicamento(Medicamento medicamento, int cantidad) {
        farmacia.updateMedicamentoStock(medicamento, cantidad);
        saveStockMedicamentosToFile();
    }

    public void eliminarStockMedicamento(Medicamento medicamento) {
        farmacia.removeMedicamentoStock(medicamento);
        saveStockMedicamentosToFile();
    }

    private void saveStockMedicamentosToFile() {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            objectMapper.writeValue(writer, farmacia.getStockMedicamentos().orElse(Collections.emptyMap()));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
