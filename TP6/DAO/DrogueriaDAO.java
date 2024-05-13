package org.miapp.DAO;

import org.miapp.Clases.Drogueria;
import org.miapp.Clases.Medicamento;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DrogueriaDAO {
    private Drogueria drogueria;

    public DrogueriaDAO() {
        this.drogueria = new Drogueria();
    }

    public void crearStockMedicamento(Medicamento medicamento, int cantidad) {
        drogueria.getStockMedicamentos().ifPresentOrElse(
                stock -> stock.put(medicamento, cantidad),
                () -> System.out.println("El stock de medicamentos está vacío.")
        );
    }

    public int obtenerStockMedicamento(Medicamento medicamento) {
        return drogueria.getStockMedicamentos()
                .orElse(Collections.emptyMap())
                .getOrDefault(medicamento, 0);
    }

    public void actualizarStockMedicamento(Medicamento medicamento, int cantidad) {
        drogueria.getStockMedicamentos().ifPresentOrElse(
                stock -> stock.put(medicamento, cantidad),
                () -> System.out.println("El stock de medicamentos está vacío.")
        );
    }

    public void eliminarStockMedicamento(Medicamento medicamento) {
        drogueria.getStockMedicamentos().ifPresentOrElse(
                stock -> stock.remove(medicamento),
                () -> System.out.println("El stock de medicamentos está vacío.")
        );
    }
}