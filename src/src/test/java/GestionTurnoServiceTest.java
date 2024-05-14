import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.miapp.DAO.TurnoDAO;
import org.miapp.Clases.Turno;
import org.miapp.Servicios.GestionTurnoService;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class GestionTurnoServiceTest {
    @Mock
    private TurnoDAO turnoDAOMock;

    private GestionTurnoService gestionTurnoService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        gestionTurnoService = new GestionTurnoService();
        gestionTurnoService.setTurnoDAO(turnoDAOMock);
    }

    @Test
    public void testObtenerTodosLosTurnos() {
        // Simulamos el comportamiento de TurnoDAO para devolver una lista de turnos
        List<Turno> turnosMock = new ArrayList<>();
        turnosMock.add(new Turno(1, "Fecha 1", "Hora 1"));
        turnosMock.add(new Turno(2, "Fecha 2", "Hora 2"));
        when(turnoDAOMock.obtenerTodosLosTurnos()).thenReturn(turnosMock);

        // Llamamos al método de GestionTurnoService que debería llamar a obtenerTodosLosTurnos de TurnoDAO
        List<Turno> turnosObtenidos = gestionTurnoService.obtenerTodosLosTurnos();

        // Verificamos que la lista obtenida es la misma que la lista simulada
        Assert.assertEquals(turnosMock, turnosObtenidos);
    }

    // Agregar más pruebas para los otros métodos de GestionTurnoService según sea necesario
}
