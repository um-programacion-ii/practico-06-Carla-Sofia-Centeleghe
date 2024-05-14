package org.miapp.DAO;

import org.miapp.Clases.Receta;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RecetaDAO {
    private static final String FILE_PATH = "recetas.json";
    private Map<Integer, Receta> recetas;
    private ObjectMapper objectMapper;

    public RecetaDAO() {
        this.recetas = new HashMap<>();
        this.objectMapper = new ObjectMapper();
        loadRecetasFromFile();
    }

    private void loadRecetasFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                String json = new String(file.getBytes());
                recetas = objectMapper.readValue(json, objectMapper.getTypeFactory().constructMapType(Map.class, Receta.class));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void crearReceta(Receta receta) {
        if (!recetas.containsKey(receta.getId())) {
            recetas.put(receta.getId(), receta);
            saveRecetasToFile();
        } else {
            throw new IllegalArgumentException("El ID de la receta debe ser único.");
        }
    }

    public Receta obtenerRecetaPorId(int id) {
        return recetas.get(id);
    }

    public void actualizarReceta(Receta receta) {
        if (recetas.containsKey(receta.getId())) {
            recetas.put(receta.getId(), receta);
            saveRecetasToFile();
        } else {
            throw new IllegalArgumentException("La receta no existe.");
        }
    }

    public void eliminarReceta(int id) {
        if (recetas.containsKey(id)) {
            recetas.remove(id);
            saveRecetasToFile();
        } else {
            throw new IllegalArgumentException("La receta no existe.");
        }
    }

    private void saveRecetasToFile() {
        try {
            String json = objectMapper.writeValueAsString(recetas);
            File file = new File(FILE_PATH);
            file.writeBytes(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}