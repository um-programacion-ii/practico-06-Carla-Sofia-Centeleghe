package org.miapp.DAO;

import com.fasterxml.jackson.core.type.TypeReference;
import org.miapp.Clases.Especialidad;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                StringBuilder json = new StringBuilder();
                while ((line = reader.readLine())!= null) {
                    json.append(line);
                }
                reader.close();
                especialidades = objectMapper.readValue(json.toString(), new TypeReference<Map<Integer, Especialidad>>() {});
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
            FileWriter writer = new FileWriter(FILE_PATH);
            objectMapper.writeValue(writer, especialidades);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
