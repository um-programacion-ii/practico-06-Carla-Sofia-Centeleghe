package org.miapp.DAO;

import org.miapp.Clases.Drogueria;
import org.miapp.Clases.Receta;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecetaDAO {
    private static final String FILE_PATH = "recetas.json";
    private Map<Integer, Receta> recetas;
    private ObjectMapper objectMapper;
    private Receta receta;

    public RecetaDAO() {
        this.receta = new Receta();
        this.recetas = new HashMap<>();
        this.objectMapper = new ObjectMapper();
        loadRecetasFromFile();
    }

    private void loadRecetasFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                String json = new String(Files.readAllBytes(file.toPath()));
                recetas = objectMapper.readValue(json, Map.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Receta crearReceta(@org.jetbrains.annotations.NotNull Receta receta) {
        if (!recetas.containsKey(receta.getId())) {
            recetas.put(receta.getId(), receta);
            saveRecetasToFile();
        } else {
            throw new IllegalArgumentException("El ID de la receta debe ser único.");
        }
        return receta;
    }

    public Receta obtenerRecetaPorId(int id) {
        return recetas.get(id);
    }

    public List<Receta> actualizarReceta() {
        if (recetas.containsKey(receta.getId())) {
            recetas.put(receta.getId(), receta);
            saveRecetasToFile();
        } else {
            throw new IllegalArgumentException("La receta no existe.");
        }
        return null;
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
            FileWriter writer = new FileWriter(FILE_PATH);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            
        }
    }

    public void asociarRecetaADrogueria(Receta receta, Drogueria drogueria) {
    }
}
