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
import java.util.*;

public class FarmaciaDAO {
    private static final String FILE_PATH = "farmacia_stock.json";
    private final Farmacia farmacia;
    private final ObjectMapper objectMapper;

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

    public List<Medicamento> obtenerMedicamentosPorCategoria(String categoria) {
        // Obtener el mapa de stock de medicamentos
        Map<Medicamento, Integer> stockMedicamentos = farmacia.getStockMedicamentos().orElse(Collections.emptyMap());

        // Filtrar los medicamentos por categoría
        List<Medicamento> medicamentosPorCategoria = new ArrayList<>();
        for (Map.Entry<Medicamento, Integer> entry : stockMedicamentos.entrySet()) {
            Medicamento medicamento = entry.getKey();
            if (medicamento.getCategoria().equals(categoria)) {
                medicamentosPorCategoria.add(medicamento);
            }
        }
        return medicamentosPorCategoria;
    }

}
