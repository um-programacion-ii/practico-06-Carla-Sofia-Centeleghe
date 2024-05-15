import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.miapp.Clases.Medicamento;
import org.miapp.Clases.Receta;
import org.miapp.DAO.FarmaciaDAO;
import org.miapp.DAO.RecetaDAO;
import org.miapp.Servicios.GestionFarmaciaService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class GestionFarmaciaServiceTest {

    @Mock
    private FarmaciaDAO farmaciaDAO;

    @Mock
    private RecetaDAO recetaDAO;

    private GestionFarmaciaService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new GestionFarmaciaService(farmaciaDAO, recetaDAO);
    }

    @Test
    public void testGenerarRecetaAleatoria() {
        // Preparar
        Receta receta = new Receta(); // Asume que Receta tiene un constructor vacío o inicializable
        when(recetaDAO.crearReceta(any(Receta.class))).thenAnswer(invocation -> {
            Receta arg = invocation.getArgument(0);
            // Simula la creación de una receta con información aleatoria
            arg.setId(Integer.parseInt("recetaID"));
            arg.setMedicamentos(List.of(new Medicamento("Paracetamol", 10)));
            return arg;
        });

        // Ejecutar
        service.generarRecetaAleatoria(receta);

        // Verificar
        verify(recetaDAO, times(1)).crearReceta(receta);
    }

    @Test
    public void testObtenerStockMedicamento() {
        Medicamento medicamento = new Medicamento("Paracetamol", 10);
        int stockEsperado = 100;

        when(farmaciaDAO.obtenerStockMedicamento(medicamento)).thenReturn(stockEsperado);

        int stockReal = service.obtenerStockMedicamento(medicamento);

        assertEquals(stockEsperado, stockReal);
        verify(farmaciaDAO).obtenerStockMedicamento(medicamento);
    }

    @Test
    public void testActualizarStockMedicamento() {
        Medicamento medicamento = new Medicamento("Ibuprofeno", 10);
        int cantidadActualizada = 200;

        service.actualizarStockMedicamento(medicamento, cantidadActualizada);

        verify(farmaciaDAO).actualizarStockMedicamento(medicamento, cantidadActualizada);
    }
}

