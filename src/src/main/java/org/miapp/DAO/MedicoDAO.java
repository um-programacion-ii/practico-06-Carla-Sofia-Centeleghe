package org.miapp.DAO;

import org.miapp.Clases.Medico;
import org.miapp.Clases.ObraSocial;
import org.miapp.Clases.Especialidad;
import org.miapp.Clases.Turno;

import java.util.*;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class MedicoDAO {
    private static final String FILE_PATH = "medicos.json";
    private Map<Integer, Medico> medicos;
    public ObjectMapper objectMapper;
    public EspecialidadDAO especialidadDAO;
    public ObraSocialDAO obraSocialDAO;
    private TurnoDAO turnoDAO;
    public int idTurno;

    public MedicoDAO() {
        this.especialidadDAO = new EspecialidadDAO();
        this.obraSocialDAO = new ObraSocialDAO();
        this.turnoDAO = new TurnoDAO();
        this.idTurno = 0 ;
        this.turnoDAO = new TurnoDAO();
        this.medicos = new HashMap<>();
        this.objectMapper = new ObjectMapper();
        loadMedicosFromFile();
    }

    private void loadMedicosFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                String json = new String(Files.readAllBytes(file.toPath()));
                medicos = objectMapper.readValue(json, Map.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Medico crearMedico(Medico medico) {
        if (!medicos.containsKey(medico.getId())) {
            medicos.put(medico.getId(), medico);
            saveMedicosToFile();
        } else {
            throw new IllegalArgumentException("El ID del médico debe ser único.");
        }
        return medico;
    }

    public Medico obtenerMedicoPorId(int id) {
        return medicos.get(id);
    }

    public Medico actualizarMedico(Medico medico) {
        if (medicos.containsKey(medico.getId())) {
            medicos.put(medico.getId(), medico);
            saveMedicosToFile();
        } else {
            throw new IllegalArgumentException("El médico no existe.");
        }
        return medico;
    }

    public boolean eliminarMedico(int id) {
        if (medicos.containsKey(id)) {
            medicos.remove(id);
            saveMedicosToFile();
            return true;
        } else {
            throw new IllegalArgumentException("El médico no existe.");
        }
    }

    private void saveMedicosToFile() {
        try {
            String json = objectMapper.writeValueAsString(medicos);
            FileWriter writer = new FileWriter(FILE_PATH);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Medico> listarTodosLosMedicos() {
        return new ArrayList<>(medicos.values()); // Devuelve todos los médicos
    }

    public Medico agregarMedico(Medico medico) {
        return crearMedico(medico); 
    }

    public List<Medico> obtenerMedicosPorEspecialidadYObraSocial(String especialidad, String obraSocial) {
        // Obtiene la especialidad y la obra social por sus IDs
        Especialidad especialidadBuscada = especialidadDAO.obtenerEspecialidadPorId(especialidad);
        ObraSocial obraSocialBuscada = obraSocialDAO.obtenerObraSocialPorId(obraSocial);

        // Filtra los médicos por la especialidad y la obra social buscadas
        return medicos.values().stream()
                .filter(m -> m.getEspecialidad().equals(especialidadBuscada) && m.getObraSocial().equals(obraSocialBuscada))
                .collect(Collectors.toList());
    }

    public List<Medico> obtenerMedicosParaTurnoParticular() {
        Turno turno = turnoDAO.obtenerTurnoPorId(idTurno);
        if (turno == null) {
            return Collections.emptyList(); // O manejar la situación de manera adecuada
        }
        return medicos.values().stream()
                .filter(m -> m.getTurnosAsignados().contains(turno))
                .collect(Collectors.toList());
    }

    public boolean asignarMedicoATurno(int idMedico, int idTurno) {
        Medico medico = obtenerMedicoPorId(idMedico);
        Turno turno = turnoDAO.obtenerTurnoPorId(idTurno);
        if (medico == null || turno == null) {
            return false; // O manejar la situación de manera adecuada
        }

        // Verificar si el turno ya ha sido iniciado
        if (turno.isEstaIniciado()) {
            return false; // No permitir que se asigne otro turno si el actual ya ha sido iniciado
        }

        medico.getTurnosAsignados().add(turno);
        turno.getMedicosAsignados().add(medico);
        turnoDAO.actualizarTurno(turno);
        return true;
    }


}

