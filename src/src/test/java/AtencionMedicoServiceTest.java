import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.miapp.Clases.Especialidad;
import org.miapp.Clases.Medico;
import org.miapp.Clases.ObraSocial;
import org.miapp.DAO.EspecialidadDAO;
import org.miapp.DAO.MedicoDAO;
import org.miapp.DAO.ObraSocialDAO;
import org.miapp.DAO.TurnoDAO;
import org.miapp.Servicios.AtencionMedicoService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class AtencionMedicoServiceTest {

    @Mock
    private MedicoDAO medicoDAO;
    @Mock
    private EspecialidadDAO especialidadDAO;
    @Mock
    private ObraSocialDAO obraSocialDAO;
    @Mock
    private TurnoDAO turnoDAO;

    @InjectMocks
    private AtencionMedicoService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Crear ejemplos de objetos una sola vez
        Medico medicoEjemplo = new Medico(1, "Juan", "Cardiología", "Opción A");
        Especialidad especialidadEjemplo = new Especialidad("1", "Cardiología");
        ObraSocial obraSocialEjemplo = new ObraSocial("1", "Opción A");

        // Configurar comportamientos comunes
        when(medicoDAO.crearMedico(any(Medico.class))).thenReturn(medicoEjemplo);
        when(especialidadDAO.obtenerEspecialidadPorId(anyString())).thenReturn(especialidadEjemplo);
        when(obraSocialDAO.obtenerObraSocialPorId(anyString())).thenReturn(obraSocialEjemplo);
        when(medicoDAO.obtenerMedicosPorEspecialidadYObraSocial(anyString(), anyString())).thenReturn(Collections.singletonList(medicoEjemplo));
        when(medicoDAO.obtenerMedicosParaTurnoParticular()).thenReturn(Collections.singletonList(medicoEjemplo));
        when(medicoDAO.listarTodosLosMedicos()).thenReturn(Collections.singletonList(medicoEjemplo));
        when(medicoDAO.asignarMedicoATurno(anyInt(), anyInt())).thenReturn(true);
        when(medicoDAO.obtenerMedicoPorId(anyInt())).thenReturn(medicoEjemplo);
        when(medicoDAO.actualizarMedico(any(Medico.class))).thenReturn(medicoEjemplo);
        when(medicoDAO.eliminarMedico(anyInt())).thenReturn(true);
    }

    @Test
    public void testObtenerMedicosPorEspecialidadYObraSocial() {
        String especialidadId = "especialidadId";
        String obraSocialId = "obraSocialId";

        when(especialidadDAO.obtenerEspecialidadPorId(especialidadId)).thenReturn(new Especialidad(especialidadId, "Especialidad"));
        when(obraSocialDAO.obtenerObraSocialPorId(obraSocialId)).thenReturn(new ObraSocial(obraSocialId, "ObraSocial"));

        List<Medico> medicos = service.obtenerMedicosPorEspecialidadYObraSocial(especialidadId, obraSocialId);

        assertEquals(Collections.singletonList(medicoEjemplo), medicos);

        verify(especialidadDAO).obtenerEspecialidadPorId(especialidadId);
        verify(obraSocialDAO).obtenerObraSocialPorId(obraSocialId);
        verify(medicoDAO).obtenerMedicosPorEspecialidadYObraSocial(especialidadId, obraSocialId);
    }

    @Test
    public void testAsignarMedicoATurno() {
        int idMedico = 1;
        int idTurno = 101;

        boolean resultado = service.asignarMedicoATurno(idMedico, idTurno);

        assertTrue(resultado);

        verify(medicoDAO).asignarMedicoATurno(idMedico, idTurno);
    }

    @Test
    public void testListarTodosLosMedicos() {
        List<Medico> medicos = service.listarTodosLosMedicos();

        assertThat(medicos).hasSizeGreaterThan(0);

        verify(medicoDAO).listarTodosLosMedicos();
    }

}
