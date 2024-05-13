package org.miapp.Clases;

import java.util.Map;
import java.util.Optional;
import java.util.Collections;

public class Drogueria {
    private Map<Medicamento, Integer> stockMedicamentos;

    public Drogueria() {
        this.stockMedicamentos = Collections.emptyMap();
    }

    public Drogueria(Map<Medicamento, Integer> stockMedicamentos) {
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
}
