package org.miapp.DAO;

import org.miapp.Clases.Especialidad;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;


public class EspecialidadDAO {
    private static final String FILE_PATH = "especialidades.json";
    private Map<Integer, Especialidad> especialidades;
    private ObjectMapper objectMapper;

    public EspecialidadDAO() {
        this.especialidades = new HashMap<>();
        this.objectMapper = new ObjectMapper();
        loadEspecialidadesFromFile();
    }

    private void loadEspecialidadesFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                String json = new String(file.getBytes());
                especialidades = objectMapper.readValue(json, objectMapper.getTypeFactory().constructMapType(Map.class, Especialidad.class));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void crearEspecialidad(Especialidad especialidad) {
        if (!especialidades.containsKey(especialidad.getId())) {
            especialidades.put(especialidad.getId(), especialidad);
            saveEspecialidadesToFile();
        } else {
            throw new IllegalArgumentException("El ID de la especialidad debe ser único.");
        }
    }

    public Especialidad obtenerEspecialidadPorId(int id) {
        return especialidades.get(id);
    }

    public void actualizarEspecialidad(Especialidad especialidad) {
        if (especialidades.containsKey(especialidad.getId())) {
            especialidades.put(especialidad.getId(), especialidad);
            saveEspecialidadesToFile();
        } else {
            throw new IllegalArgumentException("La especialidad no existe.");
        }
    }

    public void eliminarEspecialidad(int id) {
        if (especialidades.containsKey(id)) {
            especialidades.remove(id);
            saveEspecialidadesToFile();
        } else {
            throw new IllegalArgumentException("La especialidad no existe.");
        }
    }

    private void saveEspecialidadesToFile() {
        try {
            String json = objectMapper.writeValueAsString(especialidades);
            File file = new File(FILE_PATH);
            file.writeBytes(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}