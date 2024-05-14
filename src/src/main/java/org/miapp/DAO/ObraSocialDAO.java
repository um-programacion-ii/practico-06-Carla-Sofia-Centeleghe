package org.miapp.DAO;

import org.miapp.Clases.ObraSocial;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class ObraSocialDAO {
    private static final String FILE_PATH = "obras_sociales.json";
    private Map<Integer, ObraSocial> obrasSociales;
    private ObjectMapper objectMapper;

    public ObraSocialDAO() {
        this.obrasSociales = new HashMap<>();
        this.objectMapper = new ObjectMapper();
        loadObrasSocialesFromFile();
    }

    private void loadObrasSocialesFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                String json = new String(Files.readAllBytes(file.toPath()));
                obrasSociales = objectMapper.readValue(json, Map.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void crearObraSocial(ObraSocial obraSocial) {
        if (!obrasSociales.containsKey(obraSocial.getId())) {
            obrasSociales.put(obraSocial.getId(), obraSocial);
            saveObrasSocialesToFile();
        } else {
            throw new IllegalArgumentException("El ID de la obra social debe ser único.");
        }
    }

    public ObraSocial obtenerObraSocialPorId(int id) {
        return obrasSociales.get(id);
    }

    public void actualizarObraSocial(ObraSocial obraSocial) {
        if (obrasSociales.containsKey(obraSocial.getId())) {
            obrasSociales.put(obraSocial.getId(), obraSocial);
            saveObrasSocialesToFile();
        } else {
            throw new IllegalArgumentException("La obra social no existe.");
        }
    }

    public void eliminarObraSocial(int id) {
        if (obrasSociales.containsKey(id)) {
            obrasSociales.remove(id);
            saveObrasSocialesToFile();
        } else {
            throw new IllegalArgumentException("La obra social no existe.");
        }
    }

    private void saveObrasSocialesToFile() {
        try {
            String json = objectMapper.writeValueAsString(obrasSociales);
            FileWriter writer = new FileWriter(FILE_PATH);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
