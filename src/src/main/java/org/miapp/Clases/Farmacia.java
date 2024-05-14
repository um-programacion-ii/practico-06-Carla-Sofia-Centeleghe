package org.miapp.Clases;

import java.util.Map;
import java.util.Optional;
import java.util.Collections;

public class Farmacia {
    private Map<Medicamento, Integer> stockMedicamentos;

    public Farmacia() {
        this.stockMedicamentos = Collections.emptyMap();
    }

    public Farmacia(Map<Medicamento, Integer> stockMedicamentos) {
        this.stockMedicamentos = Collections.unmodifiableMap(stockMedicamentos);
    }

    public Optional<Map<Medicamento, Integer>> getStockMedicamentos() {
        return Optional.ofNullable(stockMedicamentos);
    }

    public void setStockMedicamentos(Map<Medicamento, Integer> stockMedicamentos) {
        if (stockMedicamentos == null || stockMedicamentos.isEmpty()) {
            throw new IllegalArgumentException("El stock de medicamentos no puede ser nulo ni vacío.");
        }
        this.stockMedicamentos = Collections.unmodifiableMap(stockMedicamentos);
    }

    // Método para agregar un medicamento al stock
    public void addMedicamentoStock(Medicamento medicamento, int cantidad) {
        if (stockMedicamentos.containsKey(medicamento)) {
            stockMedicamentos.put(medicamento, stockMedicamentos.get(medicamento) + cantidad);
        } else {
            stockMedicamentos.put(medicamento, cantidad);
        }
    }

    // Método para obtener el stock de un medicamento
    public int getMedicamentoStock(Medicamento medicamento) {
        return stockMedicamentos.getOrDefault(medicamento, 0);
    }

    // Método para actualizar el stock de un medicamento
    public void updateMedicamentoStock(Medicamento medicamento, int cantidad) {
        if (stockMedicamentos.containsKey(medicamento)) {
            stockMedicamentos.put(medicamento, cantidad);
        } else {
            throw new IllegalArgumentException("El medicamento no existe en el stock.");
        }
    }

    // Método para eliminar un medicamento del stock
    public void removeMedicamentoStock(Medicamento medicamento) {
        if (stockMedicamentos.containsKey(medicamento)) {
            stockMedicamentos.remove(medicamento);
        } else {
            throw new IllegalArgumentException("El medicamento no existe en el stock.");
        }
    }
}
